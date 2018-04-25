package com.abc.controller;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.column.*;
import com.abc.dto.cms.content.ContentAttrDto;
import com.abc.dto.cms.content.ContentFileDto;
import com.abc.dto.cms.content.ContentGroupViewDto;
import com.abc.dto.cms.content.ContentPictureDto;
import com.abc.dto.cms.site.SiteManageDto;
import com.abc.dto.cms.site.SiteManageListDto;
import com.abc.dto.cms.tpl.TemplateBo;
import com.abc.service.Tpl;
import com.abc.service.TplManager;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.BasePaginationCriteria;
import com.abc.soa.response.cms.bo.ModelBO;
import com.abc.soa.response.cms.bo.ModelListBO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by relic5 on 2017/6/6.
 */
@Controller
public class ColumnController extends BaseController{


    protected static Logger _log = LoggerFactory.getLogger(ColumnController.class);

    @Autowired
    private TplManager fileTplManagerImpl;

    @GetMapping("/content/column/list.php")
    public String getListPage(HttpServletRequest request,Model model){

        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0,0), SiteManageListDto.class);
        ColumnListDto channelDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, null, ColumnListDto.class);
        ModelListBO modelList = SoaConnectionFactory.get(request, ConstantsUri.CMS_MODEL, new BasePaginationCriteria(0,0), ModelListBO.class);

        List<ModelBO> validModelList = new ArrayList<>();
        for(ModelBO modelBo:modelList.getDataList()){
            if(1==modelBo.getIsDisabled()){
                continue;
            }else{
                validModelList.add(modelBo);
            }
        }

        model.addAttribute("siteList",siteManageListDto.getDataList());
        model.addAttribute("columnList", channelDto.getDataList());
        model.addAttribute("modelList",validModelList);
        model.addAttribute("type","page");
        return "cms/column/list";
    }

    @GetMapping("/content/column/addPage.php")
    public String addPage(HttpServletRequest request,Model model,String modelId,String channelId,String parentId){

        Map<String,Object> params = new HashMap<>();

        List<SiteManageDto> siteList = null;
        String currentSiteDomain = null;
        params.put("modelId", modelId);

        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0,0), SiteManageListDto.class);
        ColumnInitDto result = SoaConnectionFactory.get(request,ConstantsUri.CMS_COLUMN_INIT,params,ColumnInitDto.class);
        ModelListBO modelList = SoaConnectionFactory.get(request, ConstantsUri.CMS_MODEL, new BasePaginationCriteria(0,0), ModelListBO.class);
        ColumnListDto channelDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, null, ColumnListDto.class);

        ChannelInsertDto queryColumn = null;
        List<TemplateBo> templateList = null;
        if(!StringUtils.isEmpty(channelId)){
            ColumnQueryDto queryDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, channelId);
            model.addAttribute("queryColumn",queryDto.getData());

            templateList = fileTplManagerImpl.getAllTpl_Speci_tplProperty_siteId(request, queryDto.getData().getChannel().getModelId(), "", queryDto.getData().getChannel().getSiteId());
            if(templateList==null){
                templateList = new ArrayList<>();
            }

            ColumnQueryDto parentChannelDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, queryDto.getData().getChannel().getParentId());
            if(parentChannelDto!=null&&parentChannelDto.getData()!=null&&parentChannelDto.getData().getChannel()!=null) {
                model.addAttribute("parentChannelName", parentChannelDto.getData().getChannel().getChannelName());
                model.addAttribute("parentChannelPath", parentChannelDto.getData().getChannel().getChannelPath());
            }
            siteList = siteManageListDto.getDataList();
            for(SiteManageDto siteManageDto : siteManageListDto.getDataList()){
                if(queryDto.getData().getChannel().getSiteId().equals(siteManageDto.getSiteId())){
                    currentSiteDomain = siteManageDto.getDomain();
                }
            }

        }else{
            queryColumn = new ChannelInsertDto();
            queryColumn.setGroupList(new ArrayList<>());
            queryColumn.setChannelAttrList(new ArrayList<>());
            queryColumn.setChannelExt(new ColumnExtDto());
            model.addAttribute("queryColumn", queryColumn);

            if(!StringUtils.isEmpty(parentId)){

                ColumnQueryDto parentChannelDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, parentId);
                model.addAttribute("parentChannelName", parentChannelDto.getData().getChannel().getChannelName());
                model.addAttribute("parentChannelPath", parentChannelDto.getData().getChannel().getChannelPath());
                templateList = fileTplManagerImpl.getAllTpl_Speci_tplProperty_siteId(request, parentChannelDto.getData().getChannel().getModelId(), "", parentChannelDto.getData().getChannel().getSiteId());
                siteList = new ArrayList<>();
                for(SiteManageDto siteManageDto : siteManageListDto.getDataList()){
                    if(parentChannelDto.getData().getChannel().getSiteId().equals(siteManageDto.getSiteId())){
                        siteList.add(siteManageDto);
                        currentSiteDomain = siteManageDto.getDomain();
                    }
                }

            }else{
                parentId = "0";
                siteList = siteManageListDto.getDataList();
            }

        }

        model.addAttribute("currentSiteDomain",currentSiteDomain);
        model.addAttribute("templateList",templateList);
        model.addAttribute("tmplType",result.getData().getTplPrefix());
        model.addAttribute("siteList", siteList);
        model.addAttribute("channelId",channelId);
        model.addAttribute("parentId",parentId);
        model.addAttribute("modelId",modelId);
        model.addAttribute("modelItemList",result.getData().getModelItems());
        model.addAttribute("modelList",modelList.getDataList());
        model.addAttribute("columnList",channelDto.getDataList());
        return "cms/column/add";
    }

    @GetMapping("/content/column/ajaxColTree.php")
    public String colTree(HttpServletRequest request,Model model,String channelId, String siteId){

        List<SiteManageDto> siteList = null;

        ColumnListDto channelDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_COLUMN, null, ColumnListDto.class);

        List<ColumnDto> columnList = channelDto.getDataList();
        int index = -1;
        if(!StringUtils.isEmpty(channelId)) {
            for (int i = 0; i < columnList.size(); i++) {
                if (columnList.get(i).getChannelId().equals(channelId)) {
                    index = i;
                    break;
                }
            }
            if(index!=-1)
                columnList.remove(index);
        }

        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0,0), SiteManageListDto.class);

        if(!StringUtils.isEmpty(siteId)){
            for(SiteManageDto siteManageDto : siteManageListDto.getDataList()){
                if(siteId.equals(siteManageDto.getSiteId())){
                    siteList = new ArrayList<>();
                    siteList.add(siteManageDto);
                    break;
                }
            }
        }else{
            siteList = siteManageListDto.getDataList();
        }

        ModelListBO modelList = SoaConnectionFactory.get(request, ConstantsUri.CMS_MODEL, new BasePaginationCriteria(0,0), ModelListBO.class);
        List<ModelBO> validModelList = new ArrayList<>();
        for(ModelBO modelBo:modelList.getDataList()){
            if(1==modelBo.getIsDisabled()){
                continue;
            }else{
                validModelList.add(modelBo);
            }
        }

        model.addAttribute("siteList",siteList);
        model.addAttribute("channelId",channelId);
        model.addAttribute("modelList",validModelList);
        model.addAttribute("columnList", channelDto.getDataList());
        model.addAttribute("type","layer");
        return "cms/column/list_tmpl";
    }

    @PostMapping("/content/column/insertOrUpdate.php")
    @ResponseBody
    public Map<String, String> insertOrUpdate(
            HttpServletRequest request, HttpServletResponse response,
            Model model, ColumnDto columnDto, ColumnExtDto columnExtDto,String columnAttrStr,
            String columnPictureStr,String columnFileStr,@RequestParam("operateType") String operateType, @RequestParam(value="viewGroupIds",required = false) String viewGroupIds) {
        List<ColumnAttrDto> columnAttrDtoList = new ArrayList<>();
        List<ColumnGroupViewDto> columnGroupViewList = new ArrayList<>();
        Map<String,String> result = new HashMap<>();


        String channelId = null;
        if(columnDto!=null){
            channelId = columnDto.getChannelId();
        }

        try {
            JSONArray jsonArray = new JSONArray(columnAttrStr);
            JSONArray extArray = new JSONArray(columnPictureStr);
            JSONArray fileArray = new JSONArray(columnFileStr);


            JSONObject jsonObj = null;
            ColumnAttrDto tmpColumnAttr = null;
            ColumnGroupViewDto tmpColumnGroupView = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = (JSONObject) jsonArray.get(i);
                tmpColumnAttr = new ColumnAttrDto();
                tmpColumnAttr.setChannelId(channelId);
                tmpColumnAttr.setAttrName((String) jsonObj.get("attrName"));
                tmpColumnAttr.setAttrValue((String) jsonObj.get("attrValue"));
                columnAttrDtoList.add(tmpColumnAttr);
            }

            for (int i = 0; i < extArray.length(); i++) {
                jsonObj = (JSONObject) extArray.get(i);
                tmpColumnAttr = new ColumnAttrDto();
                tmpColumnAttr.setChannelId(channelId);
                tmpColumnAttr.setAttrName((String) jsonObj.get("fieldName"));
                tmpColumnAttr.setAttrValue((String) jsonObj.get("imgPath"));
                columnAttrDtoList.add(tmpColumnAttr);
            }
            for (int i = 0; i < fileArray.length(); i++) {
                jsonObj = (JSONObject) fileArray.get(i);
                tmpColumnAttr = new ColumnAttrDto();
                tmpColumnAttr.setChannelId(channelId);
                tmpColumnAttr.setAttrName(jsonObj.getString("fieldName"));
                tmpColumnAttr.setAttrValue(jsonObj.getString("filePath"));
                columnAttrDtoList.add(tmpColumnAttr);
            }
            if(!StringUtils.isEmpty(viewGroupIds)) {
                String[] viewGroupIdsStr = viewGroupIds.split(";");
                for (int i = 0; i < viewGroupIdsStr.length; i++) {
                    tmpColumnGroupView = new ColumnGroupViewDto();
                    tmpColumnGroupView.setChannelId(channelId);
                    tmpColumnGroupView.setGroupId(viewGroupIdsStr[i]);
                    columnGroupViewList.add(tmpColumnGroupView);
                }
            }
        }catch (Exception e){
            _log.error("解析自定义属性失败");
            result.put("code","-1");
            result.put("msg","解析自定义属性失败");
            return result;
        }

        ChannelInsertDto reqParam = new ChannelInsertDto();
        reqParam.setChannel(columnDto);
        reqParam.setChannelExt(columnExtDto);
        reqParam.setChannelAttrList(columnAttrDtoList);
        reqParam.setOperateType(operateType);
        reqParam.setGroupList(columnGroupViewList);

        ChannelInsertDto resp = null;

        if(StringUtils.isEmpty(columnDto.getChannelId())) {
            resp = SoaConnectionFactory.post(request, ConstantsUri.CMS_COLUMN, reqParam, ChannelInsertDto.class);
        }else{
            resp = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, reqParam, ChannelInsertDto.class,columnDto.getChannelId());
        }

        result.put("code", resp.getCode());
        result.put("message", resp.getMessage());
        return result;
    }

    @GetMapping("/content/column/move.php")
    @ResponseBody
    public Map<String, String> move(HttpServletRequest request,Model model,String srcChannelId,String targetChannelId){

        Map<String,String> result = new HashMap<>();

        if(StringUtils.isEmpty(srcChannelId) || StringUtils.isEmpty(targetChannelId)){
            result.put("code","-1");
            result.put("msg","操作失败");
            return result;
        }

        ColumnQueryDto queryColumn = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, srcChannelId);
        ChannelInsertDto insertDto = queryColumn.getData();
        insertDto.getChannel().setParentId(targetChannelId);
        insertDto.setOperateType("1");
        ChannelInsertDto resp = null;
        try {
            resp = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, insertDto, ChannelInsertDto.class, srcChannelId);
        }catch (Exception e){
            _log.error(e.getMessage());
            result.put("code","-1");
            result.put("msg","移动栏目失败");
            return result;
        }

        result.put("code","000");
        result.put("msg", "操作成功");
        return result;
    }

    @GetMapping("/content/column/delete.php")
    @ResponseBody
    public BaseResponse delete(HttpServletRequest request,@RequestParam("columnId") String columnId){
        BaseResponse baseResponse = null;
        if(StringUtils.isEmpty(columnId)){
            baseResponse = new BaseResponse("-1","请选择删除的栏目项");
            return baseResponse;
        }
        baseResponse = SoaConnectionFactory.delete(request,ConstantsUri.CMS_COLUMN_RESTFUL,null,BaseResponse.class,columnId);
        return baseResponse;
    }

    @GetMapping("/content/column/openContentList.php")
    public String openCommonSite(HttpServletRequest request,Model model,String channelId){
        if(StringUtils.isEmpty(channelId)){
            return "403";
        }
        return "redirect:/content/content/list.php?channelId="+channelId;
    }

    @GetMapping("/content/column/queryColumnTemplate.php")
    @ResponseBody
    public List<TemplateBo> queryColumnTemplate(HttpServletRequest request, @RequestParam("siteId") String siteId,@RequestParam("modelId") String modelId, @RequestParam(value = "tplType",required = false) String tplType) {
        List<TemplateBo> result = new ArrayList<>();

        if(StringUtils.isEmpty(siteId) ){
            return result;
        }

        result = fileTplManagerImpl.getAllTpl_Speci_tplProperty_siteId(request,modelId,tplType, siteId);

        if(result==null || result.size()==0){
            return new ArrayList<>();
        }

        return result;
    }
}
