package com.abc.service;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.BusinessException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.BaseObject;
import com.abc.common.util.Constant;
import com.abc.directive.KnowledgeWithTagDirective;
import com.abc.directive.TopicDirective;
import com.abc.dto.cms.column.ColumnDto;
import com.abc.dto.cms.column.ColumnQueryDto;
import com.abc.dto.cms.knowledge.KnowledgeTagList;
import com.abc.dto.cms.pub.KnowledgeBase;
import com.abc.dto.cms.site.ContentSaveBo;
import com.abc.dto.cms.site.ContentsListBo;
import com.abc.dto.cms.site.ContenttagidBo;
import com.abc.dto.cms.site.SiteManageQueryWrapper;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.knowledge.KnowledgeTag;
import com.abc.soa.response.cms.pub.KnowledgeBaseListResponse;
import com.abc.soa.response.cms.site.ContenttagidBoListResponse;
import com.abc.soa.response.cms.site.PublishSiteColumnContentListResponse;
import com.abc.soa.response.cms.site.PublishSiteContentListResponse;
import com.abc.soa.response.system.bo.DictListBO;
import com.abc.soa.response.system.bo.FriendlinkListBo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by zhouzhi on 2017-07-23.
 */
@Component
public class StaticServiceImpl implements StaticService{
    protected static Logger _log = LoggerFactory.getLogger(StaticServiceImpl.class);

    private RestTemplate restTemplate = new RestTemplate();;

    @Override
    public boolean delStaticContentPage(String sitePath, String staticLink) throws Exception{


        File file = ResourceUtils.getFile(BaseObject.getConfig("STATIC_HTML_PATH")+"/"+sitePath+staticLink);
        if(file.isFile() && file.exists()){ //判断文件是否存在
            file.delete();
        }else{
            return false;
        }

        return true;
    }

    @Override
    public List<String> staticSomeContent(HttpServletRequest request,String[] contentIds) throws BusinessException{
        List<String> generatedContentIdList = new ArrayList<String>();
        String contentIdsString = "";
        StringBuffer contentIdsStringBuf = new StringBuffer();
        for(String cId : contentIds){
            contentIdsStringBuf.append(cId + ",");
        }
        contentIdsString = contentIdsStringBuf.toString();
        contentIdsString = contentIdsString.substring(0,contentIdsString.length()-1);

        HashMap<String, String> reqParam = new HashMap<String, String>();
        reqParam.put("contentIds", contentIdsString);

        PublishSiteContentListResponse pscList = SoaConnectionFactory.get(request, ConstantsUri.CMS_CONTENT_PAGES, reqParam, PublishSiteContentListResponse.class);
        List<ContentSaveBo> dataList = new ArrayList<ContentSaveBo>();
        for(ContentSaveBo csb : pscList.getDataList()){
            if(csb.getContent().getStatus() == 0){
                throw new BusinessException("["+csb.getContentExt().getTitle()+"]文章处于草稿状态不能生成静态页");
            }
            ColumnQueryDto queryDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, csb.getContent().getChannelId());
            if(queryDto.getData().getChannel().getIsDisplay() == 0){
                //不显示的栏目
               throw new BusinessException("["+queryDto.getData().getChannel().getChannelName()+"]栏目已经被停用,相关栏目文章不能生成静态文件");
            }else{
                SiteManageQueryWrapper site = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, csb.getContent().getSiteId());
                if(site.getData().getSiteStatus().equals("0")){ //停用站点
                    throw new BusinessException("["+site.getData().getSiteName()+"]站点已经被停用,相关站点文章不能生成静态文件");
                }
            }
            dataList.add(csb);
        }

        //查询所有内容标签字典项
//        DictListBO contentLabelList = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_content_label");
//        Map<String, Object> requestTagsMap = new HashMap<>();
//        requestTagsMap.put("siteId", "3ef33a7ece264f859a4c4af37ba458c9");
//        ContenttagidBoListResponse contentLabelList = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTTAGS, null, ContenttagidBoListResponse.class);

        Map<String, Object> map = new HashMap<>();
        map.put("page","0");
        map.put("size","0");
        map.put("status","true");
        map.put("tagType", Constant.XTHF_CSW);
        KnowledgeTagList knowledgeTagList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, map, KnowledgeTagList.class);

        List<ContenttagidBo> contentLabelList = new ArrayList<ContenttagidBo>();
        ContenttagidBo ctb = null;
        for(KnowledgeTag kt: knowledgeTagList.getDataList()){
            ctb = new ContenttagidBo();
            ctb.setTagId(kt.getId());
            ctb.setTagName(kt.getName());
            contentLabelList.add(ctb);
        }

        HashMap<String,String> parameter = new HashMap<String,String>();
        parameter.put("isEnabled","1");
        FriendlinkListBo links = SoaConnectionFactory.get(request, ConstantsUri.FRIENDLINK, parameter, FriendlinkListBo.class);

        generatedContentIdList = generateContent(request, contentLabelList, dataList, links);

        return generatedContentIdList;
    }


    private List<String> generateContent(HttpServletRequest request, List<ContenttagidBo> contentLabelList, List<ContentSaveBo> dataList, FriendlinkListBo links) throws BusinessException{
        Configuration config=new Configuration(Configuration.VERSION_2_3_24);
        List<String> generatedContentIdArray = new ArrayList<String>();

        Map<String,String> parameter = new HashMap<String, String>();
        parameter.put("size","6");
        parameter.put("page", "0");
        PublishSiteColumnContentListResponse linkContent = null;
        for(ContentSaveBo csb : dataList){
            String siteId = csb.getContent().getSiteId();
            SiteManageQueryWrapper siteManageQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, siteId);

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
            root.put("contentLabelList", contentLabelList);
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

    @Override
    public PublishSiteColumnContentListResponse getTopicChannelContentList(String access_token, String admin_token, String tagName, String channelName, String size, String page) throws Exception {
        String url =SpringCtxHolder.getProperty("abc.soa-url")+ ConstantsUri.CMS_TAG_COLUMN_CONTENT +"?channelName="+channelName+"&tagName="+tagName+"&size="+size+"&page="+page;
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Version", "1");
        httpHeaders.add("Access-Token", access_token);
        httpHeaders.add("admin-token", admin_token);


        HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, httpHeaders);
        ResponseEntity<PublishSiteColumnContentListResponse> contentList = null;
        HttpEntity<PublishSiteColumnContentListResponse> contentListResponse = restTemplate.exchange(url, HttpMethod.GET, requestEntity, PublishSiteColumnContentListResponse.class);


        return contentListResponse.getBody();
    }

    @Override
    public KnowledgeBaseListResponse getKnowledgeWithTagContentList(String access_token, String admin_token, String tagName, String size) throws Exception {
        String url = SpringCtxHolder.getProperty("abc.soa-url")+ ConstantsUri.STATIC_KNOWLEDGE_TAG +"?keywords="+tagName+"&size="+size+"&type=QA";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Version", "1");
        httpHeaders.add("Access-Token", access_token);
        httpHeaders.add("admin-token", admin_token);



        HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, httpHeaders);
        HttpEntity<KnowledgeBaseListResponse> contentListResponse = restTemplate.exchange(url, HttpMethod.GET, requestEntity, KnowledgeBaseListResponse.class);

        //剔除问答内容中的html标签，便于做为摘要显示
        for(KnowledgeBase kb: contentListResponse.getBody().getDataList()){
            kb.setContent(kb.getContent().replaceAll("</?[^>]+>", ""));
        }


        return contentListResponse.getBody();
    }

    @Override
    public PublishSiteColumnContentListResponse getContentByChannelId(String access_token, String admin_token, String channelId, String typeId, String size, String page) throws Exception {
        //剔除了财税专题的文章
        String url =SpringCtxHolder.getProperty("abc.soa-url")+ ConstantsUri.CMS_SITE_PUBLISH_COLUMN_CONTENT +"?channelId="+channelId+"&typeId="+typeId+"&size="+size+"&page="+page+"&exceptChannelId=0f55xhdj48v0c";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Version", "1");
        httpHeaders.add("Access-Token", access_token);
        httpHeaders.add("admin-token", admin_token);


        HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, httpHeaders);
        ResponseEntity<PublishSiteColumnContentListResponse> contentList = null;
        HttpEntity<PublishSiteColumnContentListResponse> contentListResponse = restTemplate.exchange(url, HttpMethod.GET, requestEntity, PublishSiteColumnContentListResponse.class);


        return contentListResponse.getBody();
    }
}
