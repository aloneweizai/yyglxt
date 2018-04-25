package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.Knowledge.KnowledgeTagCriteria;
import com.abc.soa.request.system.OperatorCriteria;
import com.abc.soa.response.cms.knowledge.KnowledgeTag;
import com.abc.soa.response.cms.knowledge.KnowledgeTagListRs;
import com.abc.soa.response.cms.knowledge.KnowledgeTagRs;
import com.abc.soa.response.system.bo.DictListBO;
import com.abc.soa.response.system.bo.OrgListBO;
import com.abc.soa.response.system.bo.UserListBO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuqi
 * @Date 2017/8/15 11:17
 * 知识库标签管理
 */
@Controller
public class KnowTagController {
    /*初始化*/
    @GetMapping("/cms/knowTag/list.php")
    public String list(HttpServletRequest request, Model model){
        StringBuilder initPageLink = new StringBuilder(request.getContextPath()).append("/cms/knowTag/page.php");
        if(!StringUtils.isEmpty(request.getQueryString())){
            initPageLink.append("?").append(request.getQueryString()).toString();
        }
        model.addAttribute("initPageLink", initPageLink);
        DictListBO allTagType = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "xthf");
        model.addAttribute("allTagTypeList",allTagType.getDataList());
        return "cms/knowledge/tag/list";
    }

    /* 运营员列表 */
    @GetMapping("/cms/knowTag/page.php")
    public String page(HttpServletRequest request, PagerSpec pagerSpec, Model model,KnowledgeTagCriteria criteria){
        String link = pagerSpec.getLink();
        String currLink = new StringBuilder(request.getContextPath()).append("/cms/knowTag/list.php").append(link.substring(link.indexOf("?"))).toString();

        criteria.setSize(pagerSpec.getPerPageNum()).setPage(pagerSpec.getCurrentPage());
        KnowledgeTagListRs rs = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, criteria, KnowledgeTagListRs.class);

        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("tags", rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", currLink.replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        return "cms/knowledge/tag/list_page";
    }

    /* 标签新增 */
    @GetMapping("/cms/knowTag/edit.php")
    public String edit(HttpServletRequest request, Model model,
                       @RequestParam(value = "currLink", defaultValue = "")String currLink,
                       @RequestParam(required = false) String id){
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/cms/knowTag/list.php" : currLink;
        model.addAttribute("currLink",currLink);

        DictListBO allTagType = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "xthf");
        model.addAttribute("allTagTypeList",allTagType.getDataList());

        if(!CommonUtils.nullOrBlank(id)){
            KnowledgeTagRs rs = SoaConnectionFactory.getRestfulWithoutToken(request, ConstantsUri.KNOW_TAG, null, KnowledgeTagRs.class, id);

            model.addAttribute("tag", rs.getData());
            if(rs.getData().getTagType()!=null) {
                String[] selectedTagTypeArray = rs.getData().getTagType().split(";");
                model.addAttribute("selectedTagTypeArray", selectedTagTypeArray);
            }
        }
        return "cms/knowledge/tag/form";
    }

    @GetMapping("/cms/knowTag/selectedTagType/{id}.php")
    public @ResponseBody Map selectedTagTypeById(HttpServletRequest request,@PathVariable String id){

        DictListBO allTagType = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "xthf");
        HashMap map = new HashMap();
        map.put("allTagTypeList", allTagType.getDataList());

        KnowledgeTagRs rs = SoaConnectionFactory.getRestfulWithoutToken(request, ConstantsUri.KNOW_TAG, null, KnowledgeTagRs.class, id);
        if(rs.getData().getTagType()!=null) {
            String[] selectedTagTypeArray = rs.getData().getTagType().split(";");
            map.put("tagTypeArray", selectedTagTypeArray);
        }
        return map;
    }



    /* 标签新增 */
    @PostMapping("/cms/knowTag/save.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody KnowledgeTag tag){
        if(CommonUtils.nullOrBlank(tag.getId())){
            tag.setStatus(Boolean.TRUE);
            return SoaConnectionFactory.post(request, ConstantsUri.KNOW_TAG_ADD, tag, BaseResponse.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.KNOW_TAG_MODIFY, tag, BaseResponse.class);
        }
    }

    /* 标签删除 */
    @DeleteMapping("/cms/knowTag/delete/{id}.php")
    public @ResponseBody BaseResponse delete(HttpServletRequest request, @PathVariable String id){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.KNOW_TAG_DELETE, null, BaseResponse.class, id);
    }

    /* 标签批量删除 */
    @DeleteMapping("/cms/knowTag/batch/delete.php")
    public @ResponseBody BaseResponse batchDelete(HttpServletRequest request, @RequestBody List<String> ids){
        return SoaConnectionFactory.delete(request, ConstantsUri.KNOW_TAG_BATCH_DELETE, ids, BaseResponse.class);
    }

    /* 标签停用 */
    @PutMapping("/cms/knowTag/enable/{id}/{enable}.php")
    public @ResponseBody BaseResponse enable(@PathVariable(value = "id") String id,
                                             @PathVariable(value = "enable") boolean enable, HttpServletRequest request){
        return SoaConnectionFactory.put(request, ConstantsUri.KNOW_TAG_ENABLE, null, BaseResponse.class, id, enable);
    }


    @GetMapping("/cms/knowTag/label.php")
    public String label(HttpServletRequest request, Model model,
                               @RequestParam(value = "tagType")String tagType){
        Map<String, Object> map = new HashMap<>();
        map.put("page","0");
        map.put("size","0");
        map.put("status","true");
        map.put("tagType", tagType);
        KnowledgeTagListRs knowledgeTagList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, map, KnowledgeTagListRs.class);
        model.addAttribute("tagList", knowledgeTagList);
        return "cms/knowledge/label";
    }

    @GetMapping("/cms/knowTag/ajaxList.php")
    public @ResponseBody KnowledgeTagListRs tagAjaxList(HttpServletRequest request,
                                                        @RequestParam(name = "tagName",required = false)String tagName,
                                                        @RequestParam(name = "tagType")String tagType){
        Map<String, Object> map = new HashMap<>();
        map.put("page","0");
        map.put("size","0");
        map.put("status","true");
        map.put("tagType",tagType);
        if(!StringUtils.isEmpty(tagName)){
            map.put("keywords", tagName);
        }
        KnowledgeTagListRs knowledgeTagList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, map, KnowledgeTagListRs.class);
        return knowledgeTagList;
    }

    @PostMapping("/cms/knowTag/addByOtherChannel.php")
    @ResponseBody
    public KnowledgeTagListRs addByOtherChannel(HttpServletRequest request,
        @RequestParam(name = "tagType")String tagType, String labelStr){
        List<KnowledgeTag> tagList = new ArrayList<>();
        if(StringUtils.isNotEmpty(labelStr)){
            String labels[] = labelStr.split(",");
            for(String label: labels){
                tagList.add(new KnowledgeTag(label, label, true, tagType));
            }
        }
        KnowledgeTagListRs resp = SoaConnectionFactory.post(request, ConstantsUri.KNOW_TAG_BATCH_ADD_OTHER_CHANNEL, tagList, KnowledgeTagListRs.class);
        return resp;
    }

}
