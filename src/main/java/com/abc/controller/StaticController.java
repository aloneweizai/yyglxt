package com.abc.controller;

import com.abc.common.exception.BusinessException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.directive.ContentFromChannelDirective;
import com.abc.directive.KnowledgeWithTagDirective;
import com.abc.directive.TopicDirective;
import com.abc.dto.cms.column.ColumnDto;
import com.abc.dto.cms.column.ColumnListDto;
import com.abc.dto.cms.column.ColumnQueryDto;
import com.abc.dto.cms.pub.AskBOList;
import com.abc.dto.cms.site.ContentSaveBo;
import com.abc.dto.cms.site.ContentsListBo;
import com.abc.dto.cms.site.SiteManageQueryWrapper;
import com.abc.service.FileTplManagerImpl;
import com.abc.service.StaticService;
import com.abc.service.TplManager;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.pub.AdPageResponse;
import com.abc.soa.response.cms.pub.CurriculumListsyResponse;
import com.abc.soa.response.cms.pub.KnowledgeBaseListResponse;
import com.abc.soa.response.cms.pub.NoticesListResponse;
import com.abc.soa.response.cms.site.ContenttagidBoListResponse;
import com.abc.soa.response.cms.site.PublishSiteColumnContentListResponse;
import com.abc.soa.response.cms.site.PublishSiteContentListResponse;
import com.abc.soa.response.system.bo.FriendlinkListBo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 静态化 Controller
 * Created by zhouzhi on 2017-06-23.
 */

@Controller
public class  StaticController {
    protected static Logger _log = LoggerFactory.getLogger(StaticController.class);

    @GetMapping("/content/sysmaintain/toStaticContext.php")
    public ModelAndView toStaticContent(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("cms/pagestatic/contentstatic");
        return mav;
    }

    @GetMapping("/content/sysmaintain/toStaticColumn.php")
    public ModelAndView toStaticColumn(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("cms/pagestatic/columnstatic");
        return mav;
    }

    @GetMapping("/content/sysmaintain/toStaticIndex.php")
    public ModelAndView toStaticIndex(){
        ModelAndView mav = new ModelAndView("cms/pagestatic/indexStatic");
        return mav;
    }

    @GetMapping("/content/sysmaintain/staticIndex.php")
    public ModelAndView staticIndex(HttpServletRequest request){
        String resultStr = "";
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        String siteId = "3ef33a7ece264f859a4c4af37ba458c9";
        SiteManageQueryWrapper siteManageQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, siteId);

        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("parentId", "0");
        parameter.put("isDisplay", "1");
        parameter.put("siteId", "3ef33a7ece264f859a4c4af37ba458c9");


        //查找顶级栏目，类似于:资讯
        ColumnListDto topColumnList = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, parameter, ColumnListDto.class);

        parameter.clear();
//        parameter.put("page","1");
//        parameter.put("size","3");
        //查找首页广告轮播图
        AdPageResponse adPageResponse = SoaConnectionFactory.get(request, ConstantsUri.ADPAGES_LIST, null, AdPageResponse.class);

        //查询所有内容标签字典项
//        DictListBO contentLabelList = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_content_label");
        Map<String, Object> requestTagsMap = new HashMap<>();
        requestTagsMap.put("siteId", "3ef33a7ece264f859a4c4af37ba458c9");
        ContenttagidBoListResponse contentLabelList = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTTAGS, null, ContenttagidBoListResponse.class);

        for(ColumnDto topColumn : topColumnList.getDataList()) {
            parameter.clear();
            //查找一级栏目下的二级栏目列表
            parameter.put("parentId", topColumn.getChannelId());
            parameter.put("isDisplay", "1");
            ColumnListDto allColumnList = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, parameter, ColumnListDto.class);

            //需要显示的栏目
            List<ColumnDto> showColumnList = builderShowColumn(allColumnList);
            if(showColumnList.size()<=0){
                continue;
            }


            //得到通知公告栏目 用于获取通知公告栏目内容
            ColumnDto tzggColumn = getTZGG(allColumnList);
            PublishSiteColumnContentListResponse gztgContent = null;
            if (tzggColumn != null) {
                parameter.clear();
                parameter.put("page", "0");
                parameter.put("size", "6");
                parameter.put("channelId", tzggColumn.getChannelId());
                gztgContent = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE_PUBLISH_COLUMN_CONTENT, parameter, PublishSiteColumnContentListResponse.class);
            }

            //查询各个列表的文章
            HashMap<String, List<ContentsListBo>> multiSecColContentListMap= new HashMap<>();
            HashMap<String, ColumnDto> multiSecColMap = new HashMap<>();

            for(ColumnDto cdto: showColumnList){
                parameter.clear();
                parameter.put("channelId", cdto.getChannelId());
                parameter.put("page","1");
                parameter.put("size","10");
                PublishSiteColumnContentListResponse aColumnContent = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE_PUBLISH_COLUMN_CONTENT, parameter, PublishSiteColumnContentListResponse.class);
                multiSecColContentListMap.put(cdto.getChannelName(), aColumnContent.getDataList());
                multiSecColMap.put(cdto.getChannelName(), cdto);
            }

            //热点问题
            HashMap<String,String> queryMap = new HashMap<String,String>();
            queryMap.put("KnowledgePageSize","8");
            queryMap.put("KnowledgeType","QA");
            queryMap.put("KnowledgeRecommend","hot");
            KnowledgeBaseListResponse hotQAListResponse = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTQUESTION, queryMap, KnowledgeBaseListResponse.class);

            //热点知识
            queryMap.clear();
            queryMap.put("KnowledgePageSize","10");
            queryMap.put("KnowledgeType","K");
            queryMap.put("KnowledgeRecommend","hot");
            KnowledgeBaseListResponse hotKListResponse = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTQUESTION, queryMap, KnowledgeBaseListResponse.class);

            //帮友热议
            AskBOList askBOList = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTDISCUSSION, null, AskBOList.class);

            //通知公告
            NoticesListResponse noticesList = SoaConnectionFactory.get(request, ConstantsUri.PUB_NOTICES, null, NoticesListResponse.class);

            //最新课程
            CurriculumListsyResponse curriculumnList = SoaConnectionFactory.get(request, ConstantsUri.PUB_NEWESTCURRICULUM, null, CurriculumListsyResponse.class);

            //友情链接
            queryMap.clear();
            queryMap.put("isEnabled","1");
            FriendlinkListBo links = SoaConnectionFactory.get(request, ConstantsUri.FRIENDLINK, queryMap, FriendlinkListBo.class);

            try {
                 generateIndex(request, contentLabelList, gztgContent, multiSecColMap, multiSecColContentListMap, topColumn, showColumnList,adPageResponse, hotQAListResponse, hotKListResponse, askBOList, noticesList, curriculumnList,siteManageQueryWrapper, links);
                resultStr += siteManageQueryWrapper.getData().getSiteName()+"生成成功;";
            } catch (BusinessException ee){
                resultStr+=ee.getMessage()+"生成失败;";
                ee.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                resultStr +=topColumn.getChannelName()+"生成失败;";
            }
        }
        mav.addObject("result", new BaseResponse("2000", resultStr));
        return mav;
    }

    /**
     * 剔除不显示的栏目
     * @param topColumnList
     * @return
     */
    private List<ColumnDto> builderShowColumn(ColumnListDto topColumnList){
        List<ColumnDto> dataList = new ArrayList<ColumnDto>();
        for(ColumnDto column :topColumnList.getDataList()){
            if(!"通知公告".equals(column.getChannelName())){
                dataList.add(column);
            }
        }
        return dataList;
    }

    /**
     * 获取通知公告栏目 根据名字匹配
     * @param topColumnList
     * @return
     */
    private ColumnDto getTZGG(ColumnListDto topColumnList){
        for(ColumnDto column :topColumnList.getDataList()){
            if("通知公告".equals(column.getChannelName())){
                return column;
            }
        }
        return null;
    }

    @GetMapping("/content/sysmaintain/static_content.php/{channelId}")
    public ModelAndView staticContent(HttpServletRequest request,@PathVariable String channelId, String startTime){

        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);

        //当前栏目信息
        ColumnQueryDto columnDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, channelId);

        String siteId = columnDto.getData().getChannel().getSiteId();
        SiteManageQueryWrapper siteManageQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, siteId);

        HashMap<String, String> getContentParam = new HashMap<String, String>();
        getContentParam.put("channelId", channelId);
        getContentParam.put("startTime", startTime);
        getContentParam.put("status","2");  //只查出已发布状态的内容

        //获取本栏目下内容列表
//        PublishSiteContentListResponse pscList = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE_PUBLISH_COLUMN_CONTENT, getContentParam, PublishSiteContentListResponse.class);
        PublishSiteContentListResponse pscList = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE_PUBLISH_CONTENT, getContentParam, PublishSiteContentListResponse.class);



        //获取子栏目信息
//        HashMap<String, String> parameter = new HashMap<String, String>();
//        parameter.put("parentId", channelId);
//        parameter.put("isDisplay", "1");
//        ColumnListDto childrenColumnList = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, parameter, ColumnListDto.class);
        //查询所有内容标签字典项
//        DictListBO contentLabelList = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_content_label");
        Map<String, Object> requestTagsMap = new HashMap<>();
        requestTagsMap.put("siteId", "3ef33a7ece264f859a4c4af37ba458c9");
        ContenttagidBoListResponse contentLabelList = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTTAGS, null, ContenttagidBoListResponse.class);

        HashMap<String,String> parameter = new HashMap<String, String>();
        parameter.put("isEnabled","1");
        FriendlinkListBo links = SoaConnectionFactory.get(request, ConstantsUri.FRIENDLINK, parameter, FriendlinkListBo.class);

        //生成本栏目下内容静态页
        List<String> generatedContentIdList = new ArrayList<String>();
        try {
            generatedContentIdList.addAll(generateContent(request, contentLabelList, pscList, siteManageQueryWrapper,links));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //更新数据库中 内容项 已生成静态页标志位
        HashMap<String, String[]> putGeneratedContentIdsMap = new HashMap<String, String[]>();
        if(generatedContentIdList!=null) {
            String[] generatedContentIdArray = new String[generatedContentIdList.size()];
            generatedContentIdList.toArray(generatedContentIdArray);
            putGeneratedContentIdsMap.put("ids", generatedContentIdArray);
            SoaConnectionFactory.post(request, ConstantsUri.CMS_SITE_PUBLISH_CONTENT_GENERATED, putGeneratedContentIdsMap, BaseResponse.class);
        }

        mav.addObject("result", new BaseResponse("2000", "生成" + generatedContentIdList.size() + "个静态页面"));
        return mav;
    }

    /**
     * 根据contentIds生成内容静态页
     * @param request
     * @param contentIds
     * @return
     */
    @GetMapping("/content/sysmaintain/static_some_content.php")
    @ResponseBody
    public BaseResponse staticSomeContent(HttpServletRequest request,@RequestParam("ids[]") String[] contentIds){


        List<String> generatedContentIdList = new ArrayList<String>();
        try {
            generatedContentIdList = staticServiceImpl.staticSomeContent(request, contentIds);
        } catch (BusinessException ee) {
            ee.printStackTrace();
            _log.error(ee.getMessage());
            return new BaseResponse("4000", ee.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            _log.error(e.getMessage());
            return new BaseResponse("4000","生成静态页面失败");
        }


        //更新数据库中 内容项 已生成静态页标志位
        HashMap<String, String[]> putGeneratedContentIdsMap = new HashMap<String, String[]>();
        if(generatedContentIdList!=null && generatedContentIdList.size() > 0 ) {
            String[] generatedContentIdArray = new String[generatedContentIdList.size()];
            generatedContentIdList.toArray(generatedContentIdArray);
            putGeneratedContentIdsMap.put("ids", generatedContentIdArray);
            SoaConnectionFactory.post(request, ConstantsUri.CMS_SITE_PUBLISH_CONTENT_GENERATED, putGeneratedContentIdsMap, BaseResponse.class);
        }

//        mav.addObject("result", new BaseResponse("2000", "生成" + generatedContentIdList.size() + "个静态页面"));
//        return mav;

        BaseResponse result = null;
        if(generatedContentIdList.size()>0){
            result = new BaseResponse("2000","生成"+generatedContentIdList.size()+"个静态页面");
        }else{
            result = new BaseResponse("2001","静态页未生成，请重新生成静态页");
        }

        return result;
    }

    @GetMapping("/content/sysmaintain/static_column.php/{channelId}")
    public ModelAndView staticColumn(HttpServletRequest request,@PathVariable String channelId, String startTime){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);

        //当前栏目信息
        ColumnQueryDto columnDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, channelId);

        String siteId = columnDto.getData().getChannel().getSiteId();

        //获取site信息
        SiteManageQueryWrapper siteManageQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, siteId);

        //查询子栏目信息
        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("parentId", channelId);
        parameter.put("isDisplay", "1");
        ColumnListDto childrenColumnList = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, parameter, ColumnListDto.class);
        parameter.clear();
        //查询顶级栏目信息
        parameter.put("parentId", "0");
        ColumnListDto topColumnList = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, parameter, ColumnListDto.class);

        //分页 List<List<ContentsListBo>>>
        Map<String, List<List<ContentsListBo>>> maps = new HashMap<String, List<List<ContentsListBo>>>();

        parameter.clear();
        parameter.put("page", "0");
        parameter.put("size", "10");
        parameter.put("channelId",channelId);
        parameter.put("exceptChannelId","0f55xhdj48v0c"); //剔除专题栏目文章
        //查询最新的文章列表
        PublishSiteColumnContentListResponse newContentList = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE_PUBLISH_COLUMN_CONTENT, parameter, PublishSiteColumnContentListResponse.class);
        parameter.clear();
        for(ColumnDto column : childrenColumnList.getDataList()){
            parameter.put("channelId", column.getChannelId());
            parameter.put("page", "0");
            parameter.put("size", "0");
            PublishSiteColumnContentListResponse contentListResponse = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE_PUBLISH_COLUMN_CONTENT, parameter, PublishSiteColumnContentListResponse.class);
            if(!"2000".equals(contentListResponse.getCode())){
                mav.addObject("result", contentListResponse);
                return mav;
            }
            maps.put(column.getChannelId(), cutList(contentListResponse.getDataList()));
        }
        //查询所有内容标签字典项
//        DictListBO contentLabelList = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_content_label");
        Map<String, Object> requestTagsMap = new HashMap<>();
        requestTagsMap.put("siteId", "3ef33a7ece264f859a4c4af37ba458c9");
        ContenttagidBoListResponse contentLabelList = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTTAGS, null, ContenttagidBoListResponse.class);

        parameter.clear();
        parameter.put("isEnabled","1");
        FriendlinkListBo links = SoaConnectionFactory.get(request, ConstantsUri.FRIENDLINK, parameter, FriendlinkListBo.class);

        StringBuffer resultReport = new StringBuffer();
        boolean generatedIndexFlag = false;
        try {
            generatedIndexFlag = generateColumnIndex_static(request,contentLabelList, columnDto, newContentList.getDataList(), childrenColumnList.getDataList(),siteManageQueryWrapper,links);
            if(!generatedIndexFlag){
                resultReport.append("生成栏目首页失败；");
            }else{
                resultReport.append("生成1个栏目首页；");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultReport.append("生成栏目首页失败；");
        }
        int generatedPageNum = 0;
        try {
            generatedPageNum = generateColumn_static_new(maps, columnDto, topColumnList, childrenColumnList.getDataList(), contentLabelList, request,siteManageQueryWrapper, links);
            resultReport.append("生成" + generatedPageNum + "个栏目列表页。");
        } catch (Exception e) {
            resultReport.append("生成子栏目列表页失败。");
            e.printStackTrace();
        }

        if(generatedIndexFlag==true){
            mav.addObject("result",new BaseResponse("2000",resultReport.toString()));
        }else {
            mav.addObject("result", new BaseResponse("4000", resultReport.toString()));
        }
        return mav;
    }

    /**
     *
     * @param maps 分页对象
     * @param columnDto 顶级栏目
     * @param childrenColumnList 子级栏目
     * @param request
     * @return
     * @throws Exception
     */
    private int generateColumn_static_new(Map<String, List<List<ContentsListBo>>> maps, ColumnQueryDto columnDto, ColumnListDto topColumnDto, List<ColumnDto> childrenColumnList,ContenttagidBoListResponse contentLabelList, HttpServletRequest request,SiteManageQueryWrapper siteManageQueryWrapper, FriendlinkListBo links) throws Exception{


        String tplPathName = columnDto.getData().getChannelExt().getTplContent();
        String tplPath = FileTplManagerImpl.addRootToPath(tplPathName.substring(0, tplPathName.lastIndexOf("/")));
        String tplName = tplPathName.substring(tplPathName.lastIndexOf("/")+1);

        Configuration config = new Configuration(Configuration.VERSION_2_3_24);
        try {
            File file =  ResourceUtils.getFile(tplPath);
            config.setDirectoryForTemplateLoading(file);//设置模板路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setDefaultEncoding("UTF-8");//编码
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String,Object> root = new HashMap<>();

        root.put("contentLabelList",contentLabelList.getDataList());
        root.put("pColumn", columnDto);
        root.put("childrenColumnList", childrenColumnList);
        root.put("siteInfo",siteManageQueryWrapper.getData());
        root.put("links",links.getDataList());

        root.put("cswUrl",BaseObject.getConfig("cswdomain"));
        root.put("snsUrl",BaseObject.getConfig("snsdomain"));
        root.put("ucUrl",BaseObject.getConfig("ucdomain"));
        root.put("picUrl",BaseObject.getConfig("picdomain"));

        String path = BaseObject.getConfig("STATIC_HTML_PATH");
        String sitePath = columnDto.getData().getChannel().getSitePath();


        int generatedPageNum = 0;
        for(String key : maps.keySet()){
            List<List<ContentsListBo>> allList = maps.get(key);
            String htmlPrefix = "/index_"+key+"_";
            ColumnDto col = getColumnDto(childrenColumnList, key);
            String htmlFilePath = path +"/"+ sitePath +"/"+col.getChannelPath();
            File file = new File(htmlFilePath);
            if(!file.exists()) {
                file.mkdirs();
            }
            File[] files = file.listFiles();
            if(files.length >0){
                    for(int i=0;i<files.length;i++){
                    if(files[i].isFile()){
                        if(files[i].getName().contains("index_")){
                            files[i].delete();
                        }
                    }
                }
            }
            int pageNo = 0;
            root.put("prefix", htmlPrefix);
            root.put("totalPage", allList.size());
            root.put("cColumn", col);
            if(allList==null || allList.isEmpty()){
                root.remove("page");
                root.remove("pageNo");
                Writer out = null;
                try {
                    Template temple = config.getTemplate(tplName);
                    _log.debug("生成静态页：--------------");
                    _log.debug(htmlFilePath + "/index_"+key+"_"+pageNo + ".html");
                    out = new OutputStreamWriter(new FileOutputStream(htmlFilePath + "/index_"+key+"_"+pageNo + ".html"),"UTF-8");//生成最终页面并写到文件
                    temple.process(root, out);//处理
                    pageNo ++;
                    out.flush();
                    generatedPageNum++;
                } catch (Exception e) {
                    e.printStackTrace();
                    return generatedPageNum;
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                for(List<ContentsListBo> page : allList){
                    root.put("pageNo", pageNo);
                    root.put("page", page);
                    Writer out = null;
                    try {
                        Template temple = config.getTemplate(tplName);
                        out = new OutputStreamWriter(new FileOutputStream(htmlFilePath + "/index_"+key+"_"+pageNo + ".html"),"UTF-8");//生成最终页面并写到文件
                        temple.process(root, out);//处理
                        pageNo ++;
                        out.flush();
                        generatedPageNum++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return generatedPageNum;
                    } finally {
                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return generatedPageNum;
    }

    private ColumnDto getColumnDto(List<ColumnDto> columnList, String channelId){
        for(ColumnDto col : columnList){
            if(channelId.equals(col.getChannelId())){
                return col;
            }
        }
        return null;
    }
    //按大小切割list 用于分页
    private List<List<ContentsListBo>> cutList(List<ContentsListBo> list){
        List<List<ContentsListBo>> resultList = new ArrayList<>();
        int pageNo = list.size();
        List<ContentsListBo> tempList = new ArrayList<>();
        for(int i = 0; i < list.size() ; i++){
            tempList.add(list.get(i));
            if((i+1)%10 ==0 && i != 0){
                resultList.add(tempList);
                tempList = new ArrayList<>();
            }
        }
        if(!tempList.isEmpty()){
            resultList.add(tempList);
        }
        return resultList;
    }


    private List<String> generateContent(HttpServletRequest request, ContenttagidBoListResponse contentLabelList, PublishSiteContentListResponse pscList, SiteManageQueryWrapper siteManageQueryWrapper, FriendlinkListBo links) throws Exception{
        Configuration config=new Configuration(Configuration.VERSION_2_3_24);
        List<String> generatedContentIdArray = new ArrayList<String>();

        Map<String,String> parameter = new HashMap<String, String>();
        parameter.put("size","6");
        parameter.put("page", "0");
        PublishSiteColumnContentListResponse linkContent = null;
        for(ContentSaveBo csb : pscList.getDataList()){
            if(!StringUtils.isEmpty(csb.getContent().getContentType())) {
                parameter.put("tagId", csb.getContent().getContentType().split(";")[0]);
                parameter.put("exceptChannelId","0f55xhdj48v0c");
                parameter.put("channelId","0f55xhd");
                linkContent = SoaConnectionFactory.get(request, ConstantsUri.CMS_SELECT_LIST_BY_CONTENTTYPE, parameter, PublishSiteColumnContentListResponse.class);
            }
            String tplPathName = csb.getContentExt().getTplContent();
            String tplPathNameWithRoot = FileTplManagerImpl.addRootToPath(tplPathName);
            String tplPath = tplPathNameWithRoot.substring(0, tplPathNameWithRoot.lastIndexOf("/"));
            String tplName = tplPathNameWithRoot.substring(tplPathNameWithRoot.lastIndexOf("/")+1);

            try {
                File file =  ResourceUtils.getFile(tplPath);
                config.setDirectoryForTemplateLoading(file);//设置模板路径
            } catch (IOException e) {
                e.printStackTrace();
            }
            config.setDefaultEncoding("UTF-8");//编码
            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            config.setSharedVariable("topicLabel", new TopicDirective());
            config.setSharedVariable("knowledgeTagLabel", new KnowledgeWithTagDirective());
            Map<String,Object> root = new HashMap<>();
            if(linkContent != null){
                root.put("linkContentList",linkContent.getDataList());
            }


            root.put("csb",csb);
            root.put("contentLabelList", contentLabelList.getDataList());
            root.put("siteInfo",siteManageQueryWrapper.getData());
            root.put("accessToken", request.getSession().getServletContext().getAttribute("accessToken"));
            root.put("userToken",request.getSession().getAttribute("userToken"));
            root.put("links",links.getDataList());

            root.put("cswUrl",BaseObject.getConfig("cswdomain"));
            root.put("snsUrl",BaseObject.getConfig("snsdomain"));
            root.put("ucUrl",BaseObject.getConfig("ucdomain"));
            root.put("picUrl",BaseObject.getConfig("picdomain"));
//            root.put("pColumn", columnDto);
//            root.put("childrenColumnList", childrenColumnList);
            Writer out = null;
            try {
                Template temple = config.getTemplate(tplName);
                File dictory = ResourceUtils.getFile(BaseObject.getConfig("STATIC_HTML_PATH") + "/"+ csb.getContent().getSitePath() + "/"+ csb.getContent().getChannelPath());
                if(!dictory.exists() &&!dictory.isFile()){ //判断文件是否存在
                    dictory.mkdirs();
                }else if(dictory.exists() && dictory.isFile()){
                    System.out.println("不是文件夹");
                }
                out = new OutputStreamWriter(new FileOutputStream(BaseObject.getConfig("STATIC_HTML_PATH") + "/"+ csb.getContent().getSitePath() + "/"+ csb.getContent().getChannelPath()  +"/"+csb.getContent().getContentId() +".html"),"UTF-8");//生成最终页面并写到文件
                temple.process(root, out);//处理
                out.flush();
                generatedContentIdArray.add(csb.getContent().getContentId());
            } catch (Exception e) {
                e.printStackTrace();
                return generatedContentIdArray;
            }finally
            {
                if(out!=null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return generatedContentIdArray;
    }

    private boolean generateColumnIndex_static(HttpServletRequest request,ContenttagidBoListResponse contentLabelList, ColumnQueryDto columnQueryDto, List<ContentsListBo> newContentList, List<ColumnDto> childerColumnList, SiteManageQueryWrapper siteManageQueryWrapper, FriendlinkListBo links) throws Exception{

        Configuration config=new Configuration(Configuration.VERSION_2_3_24);
        String tplPathName = columnQueryDto.getData().getChannelExt().getTplChannel();
        String tplPathNameWithRoot = FileTplManagerImpl.addRootToPath(tplPathName);
        String tplPath = tplPathNameWithRoot.substring(0, tplPathNameWithRoot.lastIndexOf("/"));
        String tplName = tplPathNameWithRoot.substring(tplPathNameWithRoot.lastIndexOf("/")+1);

        try {
            File file =  ResourceUtils.getFile(tplPath);
            config.setDirectoryForTemplateLoading(file);//设置模板路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setDefaultEncoding("UTF-8");//编码
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setSharedVariable("topicLabel", new TopicDirective());
        Map<String,Object> root = new HashMap<String,Object>();//数据模型
        root.put("columnDto", columnQueryDto);
        root.put("newContentList", newContentList);
        root.put("contentLabelList", contentLabelList.getDataList());
        root.put("childerColumnList",childerColumnList);
        root.put("siteInfo",siteManageQueryWrapper.getData());
        root.put("accessToken", request.getSession().getServletContext().getAttribute("accessToken"));
        root.put("userToken",request.getSession().getAttribute("userToken"));
        root.put("links",links.getDataList());

        root.put("cswUrl",BaseObject.getConfig("cswdomain"));
        root.put("snsUrl",BaseObject.getConfig("snsdomain"));
        root.put("ucUrl",BaseObject.getConfig("ucdomain"));
        root.put("picUrl",BaseObject.getConfig("picdomain"));

        Writer out = null;
        try {
            Template temple = config.getTemplate(tplName);

            //TODO: 要用的代码
            File dictory = ResourceUtils.getFile(BaseObject.getConfig("STATIC_HTML_PATH") + "/" + columnQueryDto.getData().getChannel().getSitePath() + "/" + columnQueryDto.getData().getChannel().getChannelPath());
            if(!dictory.exists() &&!dictory.isFile()){ //判断文件是否存在
                dictory.mkdirs();
            }else if(dictory.exists() && dictory.isFile()){
                System.out.println("不是文件夹");
            }

//                out = new OutputStreamWriter(new FileOutputStream(BaseObject.getConfig("STATIC_HTML_PATH") + "/"+ csb.getContent().getSitePath() + "/"+ csb.getContent().getChannelPath()  +"/"+csb.getContent().getContentId() +".html"));//生成最终页面并写到文件

            //TODO: 要用的代码
            out = new OutputStreamWriter(new FileOutputStream(BaseObject.getConfig("STATIC_HTML_PATH") + "/"+ columnQueryDto.getData().getChannel().getSitePath() + "/"+ columnQueryDto.getData().getChannel().getChannelPath()  +"/"+"index.html"),"UTF-8");//生成最终页面并写到文件
            temple.process(root, out);//处理
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally
        {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 生成静态首页模块
     * @param request
     * @param gztgContent 通知公告
     * @param firstColumnContent 现实的额第一个文章列表
     * @param topColumn 顶级栏目 用于获取生成路径
     * @param allColumnList 顶级栏目的下一级栏目
     * @param adPageResponse 首页广告轮播图地址
     * @param hotQAListResponse 热点问题
     * @param hotKListResponse 热点知识
     * @param askBOList 帮友热议
     * @param noticesList 通知公告
     * @param curriculumnList 最新课程
     * @return
     * @throws Exception
     */
    private void generateIndex(HttpServletRequest request,ContenttagidBoListResponse contentLabelList, PublishSiteColumnContentListResponse gztgContent,
                               HashMap<String, ColumnDto> multiSecColMap, HashMap<String, List<ContentsListBo>> multiSecColContentListMap,
                                 ColumnDto topColumn, List<ColumnDto> allColumnList,
                               AdPageResponse adPageResponse,
                               KnowledgeBaseListResponse hotQAListResponse,
                               KnowledgeBaseListResponse hotKListResponse,
                               AskBOList askBOList,
                               NoticesListResponse noticesList,
                               CurriculumListsyResponse curriculumnList,
                               SiteManageQueryWrapper siteManageQueryWrapper,
                               FriendlinkListBo links) throws BusinessException{
        Configuration config=new Configuration(Configuration.VERSION_2_3_24);
        Map<String,Object> root = new HashMap<>();

        root.put("contentLabelList", contentLabelList);
        root.put("gztgContent", gztgContent);
//        root.put("firstColumnContent", firstColumnContent);
        root.put("multiSecColMap", multiSecColMap);
        root.put("multiSecColContentListMap", multiSecColContentListMap);
        root.put("topColumn", topColumn);
        root.put("allColumnList", allColumnList);
        root.put("adPageList",adPageResponse.getDataList());

        root.put("hotQAList",hotQAListResponse.getDataList());
        root.put("hotKList",hotKListResponse.getDataList());
        root.put("askBOList",askBOList.getDataList());
        root.put("noticesList",noticesList.getDataList());
        root.put("curriculumnList",curriculumnList.getDataList());
        root.put("siteInfo",siteManageQueryWrapper.getData());
        root.put("links",links.getDataList());

        root.put("cswUrl",BaseObject.getConfig("cswdomain"));
        root.put("snsUrl",BaseObject.getConfig("snsdomain"));
        root.put("ucUrl",BaseObject.getConfig("ucdomain"));
        root.put("picUrl",BaseObject.getConfig("picdomain"));

        root.put("accessToken", request.getSession().getServletContext().getAttribute("accessToken"));
        root.put("userToken",request.getSession().getAttribute("userToken"));

        String tplName = "home_temple.html";//模板名称
        String tplRoot = FileTplManagerImpl.addRootToPath(topColumn.getSitePath())+"/template/";//模板路径文件夹
        String outHtmlPath = BaseObject.getConfig("STATIC_HTML_PATH")+"/"+topColumn.getSitePath()+"/";  //生成静态文件的文件夹

        try {
            File file =  ResourceUtils.getFile(tplRoot);
            config.setDirectoryForTemplateLoading(file);//设置模板路径
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(topColumn.getChannelName()+"模板路径异常");
        }

        config.setDefaultEncoding("UTF-8");//编码
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setSharedVariable("contentFromChannel", new ContentFromChannelDirective());

        Writer out = null;
        try {
            Template temple = config.getTemplate(tplName);
            File dictory = ResourceUtils.getFile(outHtmlPath);
            if(!dictory.exists() &&!dictory.isFile()){ //判断文件是否存在
                dictory.mkdirs();
            }else if(dictory.exists() && dictory.isFile()){throw new BusinessException(topColumn.getChannelName()+"设置输出生成文件路径错误!");
            }
            out = new OutputStreamWriter(new FileOutputStream(outHtmlPath+"/index.html"),"UTF-8");//生成最终页面并写到文件
            temple.process(root, out);//处理
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(topColumn.getChannelName()+"生成异常");

        }finally
        {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Autowired
    private TplManager tplManager;
    @Autowired
    private StaticService staticServiceImpl;
}