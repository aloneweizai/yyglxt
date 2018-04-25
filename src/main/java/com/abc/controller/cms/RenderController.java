package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.BaseObject;
import com.abc.common.util.Constant;
import com.abc.dto.cms.column.ColumnListDto;
import com.abc.dto.cms.content.ContentWrapperDto;
import com.abc.dto.cms.knowledge.KnowledgeTagList;
import com.abc.dto.cms.site.ContentSaveBo;
import com.abc.dto.cms.site.ContenttagidBo;
import com.abc.dto.cms.site.SiteManageQueryWrapper;
import com.abc.service.FileTplManagerImpl;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.knowledge.KnowledgeTag;
import com.abc.soa.response.cms.questionnaire.QuestionnaireBOListRs;
import com.abc.soa.response.cms.site.ContenttagidBoListResponse;
import com.abc.soa.response.cms.site.PublishSiteColumnContentListResponse;
import com.abc.soa.response.system.bo.DictListBO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 渲染模板
 * Created by zhouzhi on 2017-07-18.
 */

@Controller
public class RenderController {
    /**
     * 一级栏目首页
     * @param request
     * @param columnName 一级栏目名称
     * @return
     */
//    @GetMapping("/cms/render/renderFirstStageColumn.php")
//    @ResponseBody
//    public ModelAndView renderFirstStageColumn(HttpServletRequest request, String columnName){
//
//
//        ModelAndView mav = new ModelAndView("");
//        Map map = new HashMap<String,String>();
//        map.put("channelName",columnName);
//        ColumnListDto channelDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, map, ColumnListDto.class);
//
//        HashMap<String, String> getContentParam = new HashMap<String, String>();
//        getContentParam.put("channelId", channelDto.getDataList().get(0).getChannelId());
////            getContentParam.put("startTime", startTime);
//        PublishSiteColumnContentListResponse psColumnContentList = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE_PUBLISH_COLUMN_CONTENT, getContentParam, PublishSiteColumnContentListResponse.class);
//        mav.addObject("contentBriefList", psColumnContentList.getDataList());
//        return mav;
//    }

//    @GetMapping("/cms/render/renderSecondStageColumn.php")
//    @ResponseBody
//    public ModelAndView renderSecondStageColumn(HttpServletRequest request, String columnName){
//
//
//        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
//        Map map = new HashMap<String,String>();
//        map.put("channelName",columnName);
//        ColumnListDto channelDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, map, ColumnListDto.class);
//
//        HashMap<String, String> getContentParam = new HashMap<String, String>();
//        getContentParam.put("channelId", channelDto.getDataList().get(0).getChannelId());
////            getContentParam.put("startTime", startTime);
//        PublishSiteColumnContentListResponse psColumnContentList = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE_PUBLISH_COLUMN_CONTENT, getContentParam, PublishSiteColumnContentListResponse.class);
//        mav.addObject("contentBriefList", psColumnContentList.getDataList());
//        return mav;
//    }

    /**
     * 生成内容预览页
     * @param request
     * @param response
     * @param contentId  内容id
     */
    @GetMapping("/cms/render/renderContent.php")
    @ResponseBody
    public void renderContent(HttpServletRequest request, HttpServletResponse response, String contentId){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        ContentWrapperDto contentWrapper =  SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_CONTENT_RESTFUL, null, ContentWrapperDto.class, contentId);

        String siteId = contentWrapper.getData().getContent().getSiteId();
        SiteManageQueryWrapper siteManageQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, siteId);

        //查询所有内容标签字典项
//        DictListBO contentLabelList = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_content_label");
//        ContenttagidBoListResponse contentLabelList = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTTAGS, null, ContenttagidBoListResponse.class);
        Map<String, Object> map = new HashMap<>();
        map.put("page","0");
        map.put("size","0");
        map.put("status","true");
        map.put("tagType", Constant.XTHF_CSW);
        KnowledgeTagList contentLabelList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, map, KnowledgeTagList.class);

        List<ContenttagidBo> contenttagidBoList = new ArrayList<ContenttagidBo>();
        ContenttagidBo ctb = null;
        for(KnowledgeTag kt: contentLabelList.getDataList()){
            ctb = new ContenttagidBo();
            ctb.setTagId(kt.getId());
            ctb.setTagName(kt.getName());
            contenttagidBoList.add(ctb);
        }
        Configuration config=new Configuration(Configuration.VERSION_2_3_24);
        List<String> generatedContentIdArray = new ArrayList<String>();

        String tplPathName = contentWrapper.getData().getContentExt().getTplContent();
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
        Map<String,Object> root;//数据模型


        root=new HashMap<>();
        root.put("csb",contentWrapper.getData());
        root.put("siteInfo",siteManageQueryWrapper.getData());
        root.put("contentLabelList", contenttagidBoList);

        root.put("cswUrl",BaseObject.getConfig("cswdomain"));
        root.put("snsUrl",BaseObject.getConfig("snsdomain"));
        root.put("ucUrl",BaseObject.getConfig("ucdomain"));
        root.put("picUrl",BaseObject.getConfig("picdomain"));

        Writer out = null;
        try {
            out = response.getWriter();
            Template temple = config.getTemplate(tplName);
//            File dictory = ResourceUtils.getFile(BaseObject.getConfig("STATIC_HTML_PATH") + "/"+ contentWrapper.getData().getContent().getSitePath() + "/"+ contentWrapper.getData().getContent().getChannelPath());
//            if(!dictory.exists() &&!dictory.isFile()){ //判断文件是否存在
//                dictory.mkdirs();
//            }else if(dictory.exists() && dictory.isFile()){
//                System.out.println("不是文件夹");
//            }
//            out = new OutputStreamWriter(new FileOutputStream(BaseObject.getConfig("STATIC_HTML_PATH") + "/"+ csb.getContent().getSitePath() + "/"+ csb.getContent().getChannelPath()  +"/"+csb.getContent().getContentId() +".html"),"UTF-8");//生成最终页面并写到文件
            temple.process(root, out);//处理
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
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
}
