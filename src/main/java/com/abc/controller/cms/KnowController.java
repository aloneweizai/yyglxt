package com.abc.controller.cms;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.DateUtil;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.dto.cms.knowledge.KnowledgeTagList;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.bangbang.SourceBo;
import com.abc.soa.request.system.KnowledgeBaseCriteria;
import com.abc.soa.response.KnowledgeTagRel;
import com.abc.soa.response.cms.knowledge.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/8/8 0008.
 */
@Controller
public class KnowController extends BaseController {

    /**
     * 知识列表
     * @return
     */
    @RequiresPermissions("consumerManager:know")
    @GetMapping("/cms/know/list")
    public String list(HttpServletRequest request, Model model){
        StringBuilder initPageLink = new StringBuilder(request.getContextPath()).append("/cms/know/page.php");
        if(!StringUtils.isEmpty(request.getQueryString())){
            initPageLink.append("?").append(request.getQueryString()).toString();
        }
        model.addAttribute("initPageLink", initPageLink);
        return "cms/knowledge/list";
    }

    @RequiresPermissions("consumerManager:know")
    @GetMapping("/cms/know/page.php")
    public String page(HttpServletRequest request, PagerSpec pagerSpec, KnowledgeBaseCriteria criteria, Model model){
        String link = pagerSpec.getLink();
        String currLink = new StringBuilder(request.getContextPath()).append("/cms/know/list").append(link.substring(link.indexOf("?"))).toString();

        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        KnowledgeListRs rs = SoaConnectionFactory.get(request, ConstantsUri.KNOW_KNOWLEDGEBASE_LIST, criteria, KnowledgeListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);

        KnowledgeCategoryListRs cateResponse = SoaConnectionFactory.get(request, ConstantsUri.KNOW_CATE_LIST, null, KnowledgeCategoryListRs.class);
        model.addAttribute("cateList", cateResponse.getDataList());
        model.addAttribute("knowBaseList", rs.getDataList());
        model.addAttribute("viewDomain", BaseObject.getConfig("HELP_VIEW_DOMAIN"));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", currLink.replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        return "cms/knowledge/search";
    }

    /**
     * 跳转添加知识 页面
     * @return
     */
    @RequiresPermissions("consumerManager:know")
    @GetMapping("/cms/know/edit")
    public String edit(HttpServletRequest request, Model model,
                       @RequestParam(value = "knowId", defaultValue = "")String knowId,
                       @RequestParam(value = "categoryCode", defaultValue = "")String categoryCode,
                       @RequestParam(value = "categoryName", defaultValue = "")String categoryName,
                       @RequestParam(value = "currLink", defaultValue = "")String currLink){
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/cms/know/list.php" : currLink;
        model.addAttribute("currLink",currLink);
        model.addAttribute("imgUrl", SpringCtxHolder.getProperty("picdomain"));
        if(StringUtils.isEmpty(knowId)){
            Knowledge know = new Knowledge();
            know.setCategoryCode(categoryCode);
            know.setCategoryName(categoryName);
            model.addAttribute("know",know);
        }else{
            KnowledgeRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.KNOW_VIEW, null, KnowledgeRs.class, knowId);
            model.addAttribute("know", rs.getData());
            Map map = new HashMap<>();
            map.put("num","0");
            KnowledgeListRs knowList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_CON, map, KnowledgeListRs.class, knowId);
            model.addAttribute("refKnows", knowList.getDataList());
            KnowledgeTagListRs tagResponse = SoaConnectionFactory.getRestful(request, ConstantsUri.KNOW_CON_LABEL, null, KnowledgeTagListRs.class, knowId);
            model.addAttribute("tags", tagResponse.getDataList());
        }
        return "cms/knowledge/add";
    }


    /**
     * 查询采集来源
     * @return
     */
    @GetMapping("/cms/know/cjly")
    public ModelAndView cjly(HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        SourceBo br = SoaConnectionFactory.get(request, ConstantsUri.CMS_KNOW_CJLY, null, SourceBo.class);
        StringBuffer sb=new StringBuffer();
        if(br.getData()!=null&&br.getData().size()>0){
            for (int i=0;i<br.getData().size();i++) {
                if(i!=0){
                    sb.append(",");
                }
                sb.append(br.getData().get(i).trim());
            }
        }
        mav.addObject("cjly",sb.toString());
        return mav;
    }

    /**
     * 查询采集来源
     * @return
     */
    @GetMapping("/cms/know/titlelist")
    public ModelAndView titlelist( @RequestParam(value = "subject", defaultValue = "")String subject,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/cms/knowledge/addtitle");
        Map map=new HashMap();
        map.put("subject",subject);
        KnowledgeListRs knowledgeListRs=SoaConnectionFactory.get(request,ConstantsUri.SELECT_BY_SUBJECT,map,KnowledgeListRs.class);
        mav.addObject("titlelist",knowledgeListRs.getDataList());
        mav.addObject("viewDomain", BaseObject.getConfig("HELP_VIEW_DOMAIN"));
        return mav;
    }


    /**
     * 批量修改分类
     * @return
     */
    @RequiresPermissions("consumerManager:know")
    @PostMapping("/cms/know/modify_code")
    public ModelAndView modify_code(
            @RequestParam(value = "ids", defaultValue = "")String ids,
            @RequestParam(value = "code", defaultValue = "")String code,
                                   HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        String[] id = ids.split(",");
        List<Knowledge> list=new ArrayList<Knowledge>();
        for(int i=0;i<id.length;i++){
            Knowledge knowledge=new Knowledge();
            knowledge.setId(id[i]);
            knowledge.setCategoryCode(code);
            list.add(knowledge);
        }
        BaseResponse br = SoaConnectionFactory.put(request, ConstantsUri.MODIFY_CATEGORY_CODE, list, BaseResponse.class);
        mav.addObject("data",br);
        return mav;
    }

    /**
     * 批量修改标签
     * @return
     */
    @RequiresPermissions("consumerManager:know")
    @PostMapping("/cms/know/modify_tag")
    public ModelAndView modify_tag(
            @RequestParam(value = "ids", defaultValue = "")String ids,
            @RequestParam(value = "tags", defaultValue = "")String tags,
            HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        String[] id = ids.split(",");
        String[] tag = tags.split(",");
        List<KnowledgeTagRel> list=new ArrayList<KnowledgeTagRel>();
        for(int i=0;i<id.length;i++){
            for(int j=0;j<tag.length;j++){
                KnowledgeTagRel knowledgeTagRel=new KnowledgeTagRel();
                knowledgeTagRel.setKnowledgeId(id[i]);
                knowledgeTagRel.setTagId(tag[j]);
                list.add(knowledgeTagRel);
            }
        }
        BaseResponse br = SoaConnectionFactory.put(request, ConstantsUri.MODIFY_TAG, list, BaseResponse.class);
        mav.addObject("data",br);
        return mav;
    }

    /**
     * 查询采集来源
     * @return
     */
    @GetMapping("/cms/know/label_list")
    public ModelAndView label_list(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/cms/knowledge/label_list");
        Map<String, Object> map = new HashMap<>();
        map.put("page","0");
        map.put("size","0");
        map.put("status","true");
        map.put("tagType", "xthf_knowledge");
        KnowledgeTagListRs knowledgeTagList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, map, KnowledgeTagListRs.class);
        mav.addObject("tagList", knowledgeTagList.getDataList());
        return mav;
    }





    /**
     * 新增知识
     * @param
     * @return
     */
    @RequiresPermissions(value = {"cms_know:add","cms_know:edit"},logical = Logical.OR)
    @PostMapping("/cms/know/save")
    public @ResponseBody BaseResponse save(HttpServletRequest request, @RequestBody KnowledgeBO knowBO) {
        if(knowBO == null || knowBO.getKnowledgeBase()== null){
            return new BaseResponse(false,"新增失败");
        }else{
            Knowledge know = knowBO.getKnowledgeBase();
            if(StringUtils.isEmpty(know.getId())){
                return SoaConnectionFactory.post(request, ConstantsUri.KNOW_ADD, knowBO, KnowledgeBORs.class);
            }else{
                return SoaConnectionFactory.put(request, ConstantsUri.KNOW_MODIFY, knowBO, KnowledgeBORs.class);
            }
        }
    }

    /**
     * 批量删除知识
     * @param
     * @return
     */
    @RequiresPermissions("cms_know:delete")
    @PostMapping("/cms/know/delete")
    @ResponseBody
    public BaseResponse delete(HttpServletRequest request,  String[] knowId){
        Map<String, List<String>> parameter = new HashMap<>();
        if(knowId != null) {
            parameter.put("ids", Arrays.asList(knowId));
        }
        BaseResponse resp = SoaConnectionFactory.delete(request, ConstantsUri.KNOW_DELLETE, parameter, BaseResponse.class);
        return resp;
    }

    /**
     * 批量禁用 启用
     * @param knowId 知识id
     * @param flag  ：启用 -  禁用
     * @return
     */
    @RequiresPermissions("cms_know:edit")
    @PostMapping("/cms/know/status")
    @ResponseBody
    public BaseResponse updateStatus(HttpServletRequest request,  String[] knowId, boolean flag){
        Map<String, Object> parameter = new HashMap<>();
        if(knowId != null) {
            parameter.put("ids", Arrays.asList(knowId));
            parameter.put("status", flag);
        }
        BaseResponse resp = SoaConnectionFactory.put(request, ConstantsUri.KNOW_UPDATE_STATUS, parameter, BaseResponse.class);
        return resp;
    }
    /**
     * 添加关联分类
     * 根据分类查询内容
     * @return
     */
    @GetMapping("/cms/know/conSearchAjaxPage")
    @ResponseBody
    public HashMap<String, Object> conSearchAjaxPage(HttpServletRequest request, KnowledgeBaseCriteria criteria, PagerSpec pagerSpec){

        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        KnowledgeListRs rs = SoaConnectionFactory.get(request, ConstantsUri.KNOW_KNOWLEDGEBASE_LIST, criteria, KnowledgeListRs.class);

        pagerSpec.setTotalItems(rs.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);

        HashMap result = new HashMap();
        StringBuilder bodyHtml = new StringBuilder();
        String seleKnowIds = criteria.getSeleKnowIds();
        for(Knowledge know : rs.getDataList()){
            bodyHtml.append("<tr style=\"cursor:pointer\" class=\"js_select_relKnow_btn\" data-id='").append(know.getId()).append("'>");
            bodyHtml.append("<td  style='width: 40%; overflow:hidden; line-height: 20px;'>").append(know.getSubject()).append("</td>");
            bodyHtml.append("<td>").append(DateUtil.formatDateTime(know.getUpdateTime(), "yyyy-MM-dd")).append("</td>");
            if(know.getStatus()){
                bodyHtml.append("<td><div class=\"btn btn-success btn-xs btn_nocursor\">启用</div></td>");
            }else{
                bodyHtml.append("<td><div class=\"btn btn-danger btn-xs btn_nocursor\">停用</div></td>");
            }
            bodyHtml.append("<td>").append((know.getActiveTime() !=null)?DateUtil.formatDateTime(know.getActiveTime(), "yyyy-MM-dd"):"永久").append("</td>");
            bodyHtml.append("</tr>");
        }
        result.put("bodyHtml", bodyHtml.toString());
        result.put("pageHtml", PagerUtil.pager(pagerSpec, true));

        return result;
    }

    /**
     * 跳转添加关联问题 页面
     * @return
     */
    @RequiresPermissions("consumerManager:know")
    @GetMapping("/cms/know/toaddanswer")
    public String toAddAnswer(HttpServletRequest request){
        return "cms/knowledge/addanswer";
    }

}
