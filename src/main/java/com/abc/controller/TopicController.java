package com.abc.controller;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.column.ColumnQueryDto;
import com.abc.dto.cms.content.ContentDto;
import com.abc.dto.cms.site.SiteManageDto;
import com.abc.dto.cms.site.SiteManageListDto;
import com.abc.dto.cms.site.SiteManageQueryWrapper;
import com.abc.dto.cms.topic.TopicDto;
import com.abc.dto.cms.topic.TopicListWrapper;
import com.abc.dto.cms.topic.TopicQueryWrapper;
import com.abc.dto.cms.tpl.TemplateBo;
import com.abc.service.Tpl;
import com.abc.service.TplManager;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.BasePaginationCriteria;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by relic5 on 2017/6/17.
 */
@Controller
public class TopicController {

    @Autowired
    private TplManager fileTplManagerImpl;

    protected static Logger _log = LoggerFactory.getLogger(TopicController.class);

    @GetMapping("/cms/topic/list.php")
    public String listPage(HttpServletRequest request,BasePaginationCriteria paginationCriteria,Model model){

        TopicListWrapper topicListWrapper = SoaConnectionFactory.get(request, ConstantsUri.CMS_TOPIC,paginationCriteria,TopicListWrapper.class);

        paginationCriteria.setTotalItems(topicListWrapper.getTotal().longValue());
        paginationCriteria.setTotalPage((int) Math.ceil((double) paginationCriteria.getTotalItems() / (double) paginationCriteria.getSize()));

        model.addAttribute("pagination",paginationCriteria);
        if(topicListWrapper.isSuccess()) {
            model.addAttribute("topicList", topicListWrapper.getDataList());
            model.addAttribute("total", topicListWrapper.getTotal());
            return "cms/topic/list";
        }else{
            model.addAttribute("topicList", new ArrayList<>());
            model.addAttribute("total", 0);
            return "cms/topic/list";
        }
    }

    @GetMapping("/cms/topic/addPage.php")
    public String addPage(HttpServletRequest request,Model model,@RequestParam(value = "topicId",required = false) String topicId){

        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0,0), SiteManageListDto.class);
        List<TemplateBo> tmplList = null;

        if(!StringUtils.isEmpty(topicId)) {
            TopicQueryWrapper topicQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_TOPIC_RESTFULE, null, TopicQueryWrapper.class, topicId);
            model.addAttribute("topicDto",topicQueryWrapper.getData());

            String siteId = topicQueryWrapper.getData().getSiteId();
//            String modelId = null;
//            if(topicQueryWrapper.getData()!=null&&!StringUtils.isEmpty(topicQueryWrapper.getData().getChannelId())) {
//                ColumnQueryDto columnQueryDto = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_COLUMN_RESTFUL, null, ColumnQueryDto.class, topicQueryWrapper.getData().getChannelId());
//                model.addAttribute("channelName", columnQueryDto.getData().getChannel().getChannelName());
//                model.addAttribute("modelId", columnQueryDto.getData().getChannel().getModelId());
//                modelId = columnQueryDto.getData().getChannel().getModelId();
//            }
            if(!StringUtils.isEmpty(siteId)){
                SiteManageQueryWrapper siteManageQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, siteId);
                SiteManageDto siteManageDto = siteManageQueryWrapper.getData();
                model.addAttribute("siteDomain","http://" + siteManageDto.getDomain());
            }
            if(!StringUtils.isEmpty(siteId)){
                tmplList = fileTplManagerImpl.getAllTpl_Speci_tplProperty_siteId(request,"","", siteId);
            }

        }

        model.addAttribute("fileTplList",tmplList==null?new ArrayList<>():tmplList);
        model.addAttribute("siteList",siteManageListDto.getDataList()==null?new ArrayList<>():siteManageListDto.getDataList());
        return "cms/topic/add";
    }

    @PostMapping("/cms/topic/insertOrUpdata.php")
    @ResponseBody
    public BaseResponse insertOrUpdate(HttpServletRequest request,TopicDto topicDto){
        BaseResponse baseResponse = null;
        String topicId = topicDto.getTopicId();

        if(StringUtils.isEmpty(topicId)){
            baseResponse = SoaConnectionFactory.post(request,ConstantsUri.CMS_TOPIC,topicDto,BaseResponse.class);
        }else{
            baseResponse = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_TOPIC_RESTFULE, topicDto, BaseResponse.class, topicDto);
        }

        return baseResponse;
    }

    @GetMapping("/cms/topic/delete.php")
    @ResponseBody
    public BaseResponse delete(HttpServletRequest request,Model model,@RequestParam("topicId")String topicId){
        BaseResponse baseResponse = null;
        if(StringUtils.isEmpty(topicId)){
            baseResponse = new BaseResponse("-1","请选择删除的专题项");
            return baseResponse;
        }
        baseResponse = SoaConnectionFactory.delete(request,ConstantsUri.CMS_TOPIC_RESTFULE,null,BaseResponse.class,topicId);
        return baseResponse;

    }

    @GetMapping("/cms/topic/deleteBatch.php")
    @ResponseBody
    public BaseResponse deleteBatch(HttpServletRequest request,Model model,@RequestParam("ids[]")String[] ids){
        BaseResponse baseResponse = null;

        if(ids==null || ids.length<=0){
            baseResponse = new BaseResponse("-1","请选择删除的专题项");
            return baseResponse;
        }
        Map<String,Object> reqParam = new HashMap<>();
        reqParam.put("ids", ids);

        baseResponse = SoaConnectionFactory.post(request, ConstantsUri.CMS_TOPIC_DELETE_BATCH, reqParam, BaseResponse.class);
        return baseResponse;
    }

    @GetMapping("/cms/topic/updateBatch.php")
    @ResponseBody
    public BaseResponse updateBatch(HttpServletRequest request,Model model,@RequestParam("keyValList")String keyValList){
        BaseResponse baseResponse = null;
        Map<String,Object> reqParam = new HashMap<>();

        TopicDto tmpTopicDto = null;
        List<TopicDto> topicList = new ArrayList<>();
        String[] tmpField;
        if(StringUtils.isEmpty(keyValList)){
            baseResponse = new BaseResponse("-1","请选择更新的专题项");
            return baseResponse;
        }
        for(String id : keyValList.split(";")){
            tmpField = id.split(":");
            tmpTopicDto = new TopicDto();
            tmpTopicDto.setTopicId(tmpField[0]);
            tmpTopicDto.setPriority(tmpField[1]);
            topicList.add(tmpTopicDto);
        }
        reqParam.put("topicBoList",topicList);
        if(topicList.size()==0){
            baseResponse = new BaseResponse("-1","请选择更新的专题项");
            return baseResponse;
        }
        baseResponse = SoaConnectionFactory.put(request, ConstantsUri.CMS_TOPIC_UPDATE_BATCH, reqParam, BaseResponse.class);
        return baseResponse;
    }

    @GetMapping("/cms/topic/render.php")
    public String render(HttpServletRequest request,Model model,String topicId){
        return  "redirect:/content_render.php?topicId="+topicId;
    }

}
