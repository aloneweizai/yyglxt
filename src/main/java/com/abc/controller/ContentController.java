package com.abc.controller;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.BusinessException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.*;
import com.abc.dto.cms.column.*;
import com.abc.dto.cms.content.*;
import com.abc.dto.cms.knowledge.KnowledgeTagList;
import com.abc.dto.cms.site.ContentSaveBo;
import com.abc.dto.cms.site.SiteManageDto;
import com.abc.dto.cms.site.SiteManageListDto;
import com.abc.dto.cms.topic.TopicDto;
import com.abc.dto.cms.topic.TopicListWrapper;
import com.abc.dto.cms.tpl.TemplateBo;
import com.abc.service.StaticService;
import com.abc.service.TplManager;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.BasePaginationCriteria;
import com.abc.soa.request.system.ContentCriteria;
import com.abc.soa.response.cms.bo.ModelListBO;
import com.abc.soa.response.cms.site.PublishSiteContentListResponse;
import com.abc.soa.response.system.bo.DictBO;
import com.abc.soa.response.system.bo.DictListBO;
import com.abc.soa.response.system.bo.UserBO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by relic5 on 2017/6/6.
 */
@Controller
public class ContentController {

    protected static Logger _log = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private TplManager fileTplManagerImpl;

    public TplManager getFileTplManagerImpl() {
        return fileTplManagerImpl;
    }

    public void setFileTplManagerImpl(TplManager fileTplManagerImpl) {
        this.fileTplManagerImpl = fileTplManagerImpl;
    }

    @Autowired
    private StaticService staticServiceImpl;

    @GetMapping("/content/content/list.php")
    public String getListPage(HttpServletRequest request, HttpSession session, Model model, ContentCriteria contentCriteria) {

        contentCriteria.setSize(15);
        ContentListDto contentList = SoaConnectionFactory.get(request, ConstantsUri.CMS_CONTENT,contentCriteria,ContentListDto.class);
        DictListBO dictList= SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "contentType");
        DictListBO contentStatusWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_content_status");
        ModelListBO modelList = SoaConnectionFactory.get(request,ConstantsUri.CMS_MODEL,new BasePaginationCriteria(0,0),ModelListBO.class);

        SiteManageListDto siteListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0,0), SiteManageListDto.class);
        if(!StringUtils.isEmpty(contentCriteria.getChannelId())) {
            ColumnQueryDto queryColumn = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, contentCriteria.getChannelId());
            model.addAttribute("channelName", queryColumn.getData().getChannel().getChannelName());
        }

        contentCriteria.setTotalItems(Long.parseLong(contentList.getTotal()));

        contentCriteria.setTotalPage((int) Math.ceil((double) contentCriteria.getTotalItems() / (double) contentCriteria.getSize()));

        Map<String, String> typeMap = new HashMap<>();
//        Map<String, String> modelMap = new HashMap<>();
        if(dictList.getDataList()!=null) {
            for (DictBO dict : dictList.getDataList()) {
                typeMap.put(dict.getFieldValue(), dict.getFieldKey());
            }
        }
//        if(modelList.getDataList()!=null) {
//            for (ModelBO modelBo : modelList.getDataList()) {
//                if (1 == modelBo.getIsDisabled().intValue()) {
//                    continue;
//                }
//                modelMap.put(modelBo.getModelId(), modelBo.getModelName());
//            }
//        }

        model.addAttribute("siteList",siteListDto.getDataList());
        model.addAttribute("contentList", contentList.getDataList());
        model.addAttribute("typeMap", typeMap);
        model.addAttribute("contentStatusList",contentStatusWrapper.getDataList());
//        model.addAttribute("modelMap", modelMap);
        model.addAttribute("pagination", contentCriteria);
        model.addAttribute("staticHtmlUrl",SpringCtxHolder.getProperty("STATIC_HTML_ORIGN"));
        return "cms/content/list";

    }

    @GetMapping("/content/content/preAddPage.php")
    public String preAddPage(HttpServletRequest request, Model model, String pre){

        model.addAttribute("pre",pre);
        return "cms/content/blank";
    }

    @GetMapping("/content/content/addPage.php")
    public String addPage(HttpServletRequest request, Model model, String modelId, String contentId, String channelId,String view) {

        Map<String, Object> params = new HashMap<>();
        List<SiteManageDto> siteList = null;
        ContentWrapperDto contentWrapper = null;
        ContentInsertOrUpdateDto queryDto = null;
        List<TemplateBo> templateList = null;
        ContentInitDto result = null;
        String siteId = null;
        String currentSiteDomain = null;
        boolean showFirstNodeOfSite = true;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0, 0), SiteManageListDto.class);

        //修改加载
        if (!StringUtils.isEmpty(contentId)) {
            contentWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_CONTENT_RESTFUL, null, ContentWrapperDto.class, contentId);
            queryDto = contentWrapper.getData();

//            try {
//                if (queryDto.getContentExt().getReleaseDate() != null) {
//                    queryDto.getContentExt().setReleaseDate(sdf.format(new Date(Long.parseLong(queryDto.getContentExt().getReleaseDate()))));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                queryDto.getContentExt().setReleaseDate("");
//            }

            if (queryDto != null) {
                channelId = queryDto.getContent().getChannelId();
                String tplType = "";
                if(queryDto.getContentAttrList()!=null && queryDto.getContentAttrList().size()>0) {
                    for (ContentAttrDto cad : queryDto.getContentAttrList()) {
                        if ("tplType".equals(cad.getAttrName())) {
                            tplType = cad.getAttrValue();
                        }
                    }
                }
                if (!StringUtils.isEmpty(channelId)) {
                    try {
                        ColumnQueryDto columnQueryDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, queryDto.getContent().getChannelId());
                        templateList = fileTplManagerImpl.getAllTpl_Speci_tplProperty_siteId(request, columnQueryDto.getData().getChannel().getModelId(),tplType, columnQueryDto.getData().getChannel().getSiteId());
                        model.addAttribute("templateList", templateList);
                        model.addAttribute("channelId", channelId);
                        model.addAttribute("channelName", columnQueryDto.getData().getChannel().getChannelName());
                    }catch (Exception e){
                        return "404";
                    }
                }
                modelId = queryDto.getContent().getModelId();
                params.put("modelId", modelId);
            }else{
                return "404";
            }

            siteList = new ArrayList<>();
            if(siteManageListDto.getDataList()!=null) {
                siteId = queryDto.getContent().getSiteId();
                for (SiteManageDto siteManageDto : siteManageListDto.getDataList()) {
                    if (siteManageDto.getSiteId().equals(siteId)) {
                        siteList.add(siteManageDto);
                        currentSiteDomain = siteManageDto.getDomain();
                    }
                }
            }
            showFirstNodeOfSite = false;
        } else {
            queryDto = new ContentInsertOrUpdateDto();
            queryDto.setContent(new ContentDto());
            queryDto.setContentExt(new ContentExtDto());
            queryDto.setContentTxt(new ContentTxtDto());
            queryDto.setFileList(new ArrayList<>());
            queryDto.setContentPictureList(new ArrayList<>());
            queryDto.setGroupList(new ArrayList<>());

            if(!StringUtils.isEmpty(channelId)){
                ColumnQueryDto columnQueryDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, channelId);
                try {
                    modelId = columnQueryDto.getData().getChannel().getModelId();
                    siteId = columnQueryDto.getData().getChannel().getSiteId();
                    templateList = fileTplManagerImpl.getAllTpl_Speci_tplProperty_siteId(request, modelId, "", siteId);
                    model.addAttribute("templateList", templateList);
                    model.addAttribute("channelId", channelId);
                    model.addAttribute("channelName", columnQueryDto.getData().getChannel().getChannelName());
                }catch (Exception e){
                    e.printStackTrace();
                    _log.error(e.getMessage());
                    return "404";
                }

                siteList = new ArrayList<>();
                if(siteManageListDto.getDataList()!=null) {
                    for (SiteManageDto siteManageDto : siteManageListDto.getDataList()) {
                        if (siteManageDto.getSiteId().equals(siteId)) {
                            siteList.add(siteManageDto);
                        }
                    }
                }
                modelId = columnQueryDto.getData().getChannel().getModelId();
                params.put("modelId", modelId);
                showFirstNodeOfSite = false;
            }else{
                siteList = siteManageListDto.getDataList();
                params.put("modelId", modelId);
                showFirstNodeOfSite = true;

            }
        }

        result = SoaConnectionFactory.get(request, ConstantsUri.CMS_CONTENT_INIT, params, ContentInitDto.class);
        DictListBO dictList = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "contentType");
//        DictListBO contentLabelList = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_content_label");

        Map<String, Object> map = new HashMap<>();
        map.put("page","0");
        map.put("size","0");
        map.put("status","true");
        map.put("tagType", Constant.XTHF_CSW);
        KnowledgeTagList knowledgeTagList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, map, KnowledgeTagList.class);
//        model.addAttribute("tagList", knowledgeTagList);

        model.addAttribute("showFirstNodeOfSite",showFirstNodeOfSite);
        model.addAttribute("currentSiteDomain",currentSiteDomain);
        model.addAttribute("siteList", siteList);
        model.addAttribute("contentDto", queryDto);
        model.addAttribute("modelItemList", result.getData().getModelItems());
        model.addAttribute("contentTypeList", dictList.getDataList());
        model.addAttribute("contentLabelList", knowledgeTagList.getDataList());
        model.addAttribute("contentId", contentId);
        model.addAttribute("modelId", modelId);
        //设置是否是在编辑页面中查看，如果是查看，页面中会设置不可编辑
        if("view".equals(view)){
            model.addAttribute("view", "view");
        }
        return "cms/content/add";
    }

    @PostMapping("/content/content/insertOrUpdate.php")
    @ResponseBody
    public Map<String, String> insertOrUpdate(
            HttpServletRequest request, HttpServletResponse response,
            ContentDto contentDto,
            LocalContentExtDto localContentExtDto,
            ContentTxtDto contentTxtDto,
            String contentTagStr,
            String contentAttrStr,
            String contentPictureStr,
            String contentFileStr,
            @RequestParam(value = "viewGroupIds", required = false) String viewGroupIds) {

        Map<String, String> result = new HashMap<>();

        List<ContentAttrDto> contentAttrList = new ArrayList<>();
        List<ContentPictureDto> contentPictureList = new ArrayList<>();
        List<ContentFileDto> contentFileList = new ArrayList<>();
        List<ContentGroupViewDto> contentGroupViewList = new ArrayList<>();
        List<ContentTagDto> tagList = null;
        String contentId = null;

        if (contentDto != null && !StringUtils.isEmpty(contentDto.getContentId())) {
            contentId = contentDto.getContentId();
        }

        UserBO userBO = (UserBO)request.getSession().getAttribute("currentUser");
        contentDto.setUserid(userBO.getId());
        contentDto.setUsername(userBO.getUsername());

        try {
            JSONArray attrArray = new JSONArray(contentAttrStr);
            JSONArray extArray = new JSONArray(contentPictureStr);
            JSONArray fileArray = new JSONArray(contentFileStr);

            JSONObject jsonObj = null;
            ContentAttrDto tmpContentAttr = null;
            ContentPictureDto tmpPicDto = null;
            ContentFileDto tmpFileDto = null;
            ContentGroupViewDto tmpContentGroupView = null;
            for (int i = 0; i < attrArray.length(); i++) {
                jsonObj = (JSONObject) attrArray.get(i);
                tmpContentAttr = new ContentAttrDto();
                tmpContentAttr.setAttrName((String) jsonObj.get("attrName"));
                tmpContentAttr.setAttrValue((String) jsonObj.get("attrValue"));
                tmpContentAttr.setContentId(contentId);
                contentAttrList.add(tmpContentAttr);
            }
            for (int i = 0; i < extArray.length(); i++) {
                jsonObj = (JSONObject) extArray.get(i);
                tmpPicDto = new ContentPictureDto();
                tmpPicDto.setDescription((String) jsonObj.get("description"));
                tmpPicDto.setImgPath((String) jsonObj.get("imgPath"));
                tmpPicDto.setPriority((String) jsonObj.get("priority"));
                tmpPicDto.setContentId(contentId);
                contentPictureList.add(tmpPicDto);

                tmpContentAttr = new ContentAttrDto();
                tmpContentAttr.setAttrName(jsonObj.getString("fieldName"));
                tmpContentAttr.setAttrValue(jsonObj.getString("imgPath"));
                tmpContentAttr.setContentId(contentId);
                contentAttrList.add(tmpContentAttr);
            }
            for (int i = 0; i < fileArray.length(); i++) {
                jsonObj = (JSONObject) fileArray.get(i);
                if(jsonObj.has("filePath") && jsonObj.getString("filePath").length()>0) {
                    tmpFileDto = new ContentFileDto();
                    tmpFileDto.setFileName((String) jsonObj.get("fileName"));
                    tmpFileDto.setFileIsvalid((String) jsonObj.get("fileIsvalid"));
                    tmpFileDto.setFilePath((String) jsonObj.get("filePath"));
                    tmpFileDto.setContentId(contentId);
                    contentFileList.add(tmpFileDto);
                }

                if(jsonObj.has("fieldName") && jsonObj.has("filePath")) {
                    tmpContentAttr = new ContentAttrDto();
                    tmpContentAttr.setAttrName(jsonObj.getString("fieldName"));
                    tmpContentAttr.setAttrValue(jsonObj.getString("filePath"));
                    tmpContentAttr.setContentId(contentId);
                    contentAttrList.add(tmpContentAttr);
                }
            }
            if (!StringUtils.isEmpty(viewGroupIds)) {
                String[] viewGroupIdsStr = viewGroupIds.split(";");
                for (int i = 0; i < viewGroupIdsStr.length; i++) {
                    tmpContentGroupView = new ContentGroupViewDto();
                    tmpContentGroupView.setGroupId(viewGroupIdsStr[i]);
                    tmpContentGroupView.setContentId(contentId);
                    contentGroupViewList.add(tmpContentGroupView);
                }
            }

            contentDto.setContentType(contentTagStr);
            if(!StringUtils.isEmpty(contentTagStr)){
                String[] tags = contentTagStr.split(";");
                tagList = new ArrayList<ContentTagDto>(tags.length);
                for (int i = 0; i < tags.length; i++) {
                    tagList.add(new ContentTagDto(contentId, tags[i], i));
                }
            }

        } catch (Exception e) {
            _log.error("解析自定义属性失败");
            result.put("code", "-1");
            result.put("msg", "解析自定义属性失败");
            return result;
        }

        //contentExtDto.setReleaseDate(new Date());
        ContentExtDto contentExtDto= setContentExtDto(localContentExtDto);
        ContentInsertOrUpdateDto reqParam = new ContentInsertOrUpdateDto();
        reqParam.setTagList(tagList);
        reqParam.setContent(contentDto);
        reqParam.setContentExt(contentExtDto);
        reqParam.setContentTxt(contentTxtDto);
        reqParam.setContentAttrList(contentAttrList);
        reqParam.setContentPictureList(contentPictureList);
        reqParam.setFileList(contentFileList);
        reqParam.setGroupList(contentGroupViewList);

        ContentWrapperDto resp = null;
        if (StringUtils.isEmpty(contentId)) {
            resp = SoaConnectionFactory.post(request, ConstantsUri.CMS_CONTENT, reqParam, ContentWrapperDto.class);
        } else {
            resp = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_CONTENT_RESTFUL, reqParam, ContentWrapperDto.class, contentId);
        }


        result.put("code", resp.getCode());
        result.put("msg", resp.getMessage());
        if(resp.isSuccess() && resp.getData()!=null && resp.getData().getContent()!=null) {
            result.put("contentId", resp.getData().getContent().getContentId());
        }

        return result;
    }

    private ContentExtDto setContentExtDto(LocalContentExtDto localContentExtDto){
        ContentExtDto contentExtDto = new ContentExtDto();
        contentExtDto.setAuthor(localContentExtDto.getAuthor());
        contentExtDto.setContentId(localContentExtDto.getContentId());
        contentExtDto.setContentImg(localContentExtDto.getContentImg());
        contentExtDto.setDescription(localContentExtDto.getDescription());
        contentExtDto.setLink(localContentExtDto.getLink());
        contentExtDto.setMediaPath(localContentExtDto.getMediaPath());
        contentExtDto.setMediaType(localContentExtDto.getMediaType());
        contentExtDto.setOrigin(localContentExtDto.getOrigin());
        Calendar c = Calendar.getInstance();
        String rDate = localContentExtDto.getReleaseDate();
        if(rDate !=null) {
            String[] mDate = rDate.split("-");
            c.set(Integer.valueOf(mDate[0]), Integer.valueOf(mDate[1])-1, Integer.valueOf(mDate[2]));
        }
        Date releaseDate = c.getTime();
        contentExtDto.setReleaseDate(releaseDate);
        contentExtDto.setTitle(localContentExtDto.getTitle());
        contentExtDto.setShortTitle(localContentExtDto.getShortTitle());
        contentExtDto.setTitleColor(localContentExtDto.getTitleColor());
        contentExtDto.setTplContent(localContentExtDto.getTplContent());
        contentExtDto.setTitleImg(localContentExtDto.getTitleImg());
        return contentExtDto;
    }


    @PostMapping("/content/content/fileUpload.php")
    @ResponseBody
    public Map<String, String> fileUpload(MultipartHttpServletRequest multipartRequest,String subsystem,String fileTypeTag) {

        Map<String, String> result = new HashMap<>();

        FileType tarFileType = null;

        for(FileType fileType: FileType.values()){
            if(fileType.tag.equals(fileTypeTag)){
                tarFileType = fileType;
            }
        }

        List<Map<String,Object>> fileResult = null;
        try {

            fileResult = FileOperateUtil.upload(multipartRequest,subsystem,tarFileType);
        }catch (BusinessException e){
            result.put("code", "-1");
            result.put("msg", e.getMessage());
            return result;
        }catch (Exception e){
            result.put("code", "-1");
            result.put("msg", "上传失败");
            return result;
        }

        if (fileResult!=null && fileResult.size() > 0) {
            result.put("code", "2000");
            result.put("msg", "上传成功");
            result.put("fileName", (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
            result.put("filePath", (String)fileResult.get(0).get(FileOperateUtil.FILETPATH));
        } else {
            result.put("code", "-1");
            result.put("msg", "上传失败");
        }

        return result;

    }

    @PostMapping("/content/content/editorFileUpload.php")
    @ResponseBody
    public Map<String, Object> editorFileUpload(MultipartHttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        FileType tarFileType = null;

        String subsystem = request.getParameter("subsystem");
        String fileTypeTag = request.getParameter("fileTypeTag");
        String domain = request.getParameter("siteDomain");

        if(StringUtils.isEmpty(subsystem)||StringUtils.isEmpty(fileTypeTag)||StringUtils.isEmpty(domain)){
            result.put("errno", "-2");
            return result;
        }

        for(FileType fileType: FileType.values()){
            if(fileType.tag.equals(fileTypeTag)){
                tarFileType = fileType;
            }
        }

        if(StringUtils.isEmpty(subsystem)||tarFileType==null){
            result.put("errno", "-2");
            return result;
        }


        List<Map<String,Object>> fileResult = null;
        try {
            fileResult = FileOperateUtil.upload(request,subsystem,tarFileType);
        }catch (BusinessException e){
            result.put("errno", e.getCode());
            result.put("errMsg", e.getMessage());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("errno", "-1");
            return result;
        }

        if (fileResult!=null && fileResult.size() > 0) {
            result.put("errno", "0");

            List<String> data = new ArrayList<>();
            for (int i = 0; i < fileResult.size(); i++) {
                data.add("http://" + domain + (String) fileResult.get(i).get(FileOperateUtil.FILETPATH) + "/" + (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
            }
            result.put("data", data);
        }else{
            result.put("errno", "-1");
        }

        return result;
    }

    @GetMapping("/content/content/delete/{contentId}")
    @ResponseBody
    public BaseResponse delete(HttpServletRequest request,@PathVariable String contentId) {
        BaseResponse result = null;

        if (StringUtils.isEmpty(contentId)) {
            result = new BaseResponse("-1", "请选择内容");
            return result;
        }
        result = SoaConnectionFactory.deleteRestful(request, ConstantsUri.CMS_CONTENT_RESTFUL, null, BaseResponse.class, contentId);
        return result;
    }

    @GetMapping("/content/content/issue/{contentId}")
    @ResponseBody
    public BaseResponse issue(HttpServletRequest request,@PathVariable String contentId) {
        BaseResponse result = null;
        result = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_CONTENT_ISSUE, null, BaseResponse.class, contentId);
        return result;
    }

    @GetMapping("/content/content/deleteBatch.php")
    @ResponseBody
    public BaseResponse deleteBatch(HttpServletRequest request, @RequestParam("ids[]") String[] ids) {

        BaseResponse baseResponse = null;
        if (ids == null || ids.length <= 0) {
            baseResponse = new BaseResponse("-1", "请选择删除内容");
            return baseResponse;
        }
        Map<String, Object> reqParam = new HashMap<>();
        reqParam.put("ids", ids);

        baseResponse = SoaConnectionFactory.post(request, ConstantsUri.CMS_CONTENT_DELETE_BATCH, reqParam, BaseResponse.class);
        return baseResponse;
    }

    @GetMapping("/content/content/updateBatch.php")
    @ResponseBody
    public BaseResponse updateBatch(HttpServletRequest request, @RequestParam("keyValList") String keyValList) {
        BaseResponse baseResponse = null;
        Map<String, Object> reqParam = new HashMap<>();

        ContentDto tmpContentDto = null;
        List<ContentDto> contentBoList = new ArrayList<>();
        String[] tmpField;
        if (StringUtils.isEmpty(keyValList)) {
            baseResponse = new BaseResponse("-1", "请选择更新内容项");
            return baseResponse;
        }
        for (String id : keyValList.split(";")) {
            tmpField = id.split(":");
            tmpContentDto = new ContentDto();
            tmpContentDto.setContentId(tmpField[0]);
            tmpContentDto.setTopLevel(tmpField[1]);
            contentBoList.add(tmpContentDto);
        }
        reqParam.put("contentBoList", contentBoList);
        if (contentBoList.size() == 0) {
            baseResponse = new BaseResponse("-1", "请选择更新内容项");
            return baseResponse;
        }
        baseResponse = SoaConnectionFactory.put(request, ConstantsUri.CMS_CONTENT_UPDATE_BATCH, reqParam, BaseResponse.class);
        return baseResponse;
    }

    @GetMapping("/content/content/sendBackBatch.php")
    @ResponseBody
    public BaseResponse sendBackBatch(HttpServletRequest request, @RequestParam("ids[]") String[] ids) {
        BaseResponse baseResponse = null;
        if (ids == null || ids.length <= 0) {
            baseResponse = new BaseResponse("-1", "请选择退回内容");
            return baseResponse;
        }
        Map<String, Object> reqParam = new HashMap<>();
        reqParam.put("ids", ids);

        //删除静态页
        PublishSiteContentListResponse contentList = SoaConnectionFactory.post(request, ConstantsUri.CMS_CONTENT_SEND_BACK, reqParam, PublishSiteContentListResponse.class);
        int i = 0;
        try{
            for(ContentSaveBo csb:  contentList.getDataList()){
                staticServiceImpl.delStaticContentPage(csb.getContent().getSitePath(),csb.getContentExt().getStaticLink());
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
            _log.error(e.getMessage());
        }
        return new BaseResponse("2000", "删除了" + i + "个静态页面");
    }

    @GetMapping("/content/content/topicList.php")
    public String getTopicList(HttpServletRequest request, Model model, String siteId) {
        List<TopicDto> result = new ArrayList<>();
        TopicListWrapper topicListWrapper = SoaConnectionFactory.get(request, ConstantsUri.CMS_TOPIC, null, TopicListWrapper.class);
        if(topicListWrapper.getDataList()!=null){
            for(TopicDto topicDto:topicListWrapper.getDataList()){
                if(topicDto.getSiteId().equals(siteId)){
                    result.add(topicDto);
                }
            }
        }
        model.addAttribute("topicList", result);
        return "cms/content/content_topic_tmpl";
    }

    @PostMapping("/content/content/pushTopic.php")
    @ResponseBody
    public BaseResponse pushTopic(HttpServletRequest request, @RequestParam("ids[]") String[] ids, String topicId) {
        BaseResponse baseResponse = null;

        List<ContentTopicDto> contentTopicList = new ArrayList<>();
        ContentTopicDto tmpContentTopic = null;
        for (String id : ids) {
            tmpContentTopic = new ContentTopicDto();
            tmpContentTopic.setTopicId(topicId);
            tmpContentTopic.setContentId(id);
            contentTopicList.add(tmpContentTopic);
        }

        Map<String, Object> reqParam = new HashMap<>();
        reqParam.put("topicBoList", contentTopicList);
        baseResponse = SoaConnectionFactory.put(request, ConstantsUri.CMS_CONTENT_PUSH_TOPIC, reqParam, BaseResponse.class);

        return baseResponse;
    }

    @GetMapping("/content/content/openCommonSite/{contentId}")
    public String openCommonSite(HttpServletRequest request,Model model,@PathVariable String contentId){
        if(StringUtils.isEmpty(contentId)){
            return "403";
        }
        ContentWrapperDto contentWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_CONTENT_RESTFUL, null, ContentWrapperDto.class, contentId);
        String tpl = null;
        if(contentWrapper.getData()!=null && !StringUtils.isEmpty(contentWrapper.getData().getContentExt().getTplContent())){
            tpl = contentWrapper.getData().getContentExt().getTplContent();
            tpl = "t/cms_sys_defined"+tpl;
            if(tpl.endsWith(".ftl")){
                tpl = tpl.substring(0,tpl.indexOf(".ftl"));
            }
        }
        model.addAttribute("csb", contentWrapper.getData());
        return tpl;
    }
}
