package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.dto.system.OrgType;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.OrgAjaxCriteria;
import com.abc.soa.request.system.OrgCriteria;
import com.abc.soa.response.system.Organization;
import com.abc.soa.response.system.bo.OrgListBO;
import com.abc.soa.response.system.bo.OrgRs;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 部门管理
 * @Author liuqi
 * @Date 2017/5/18 15:03
*/
@Controller
public class OrgController  extends BaseController {

    @RequiresPermissions("system:org")
    @GetMapping("/system/org/list.php")
    public String list(HttpServletRequest request, Model model){
        StringBuilder initPageLink = new StringBuilder(request.getContextPath()).append("/system/org/page.php");
        if(!StringUtils.isEmpty(request.getQueryString())){
            initPageLink.append("?").append(request.getQueryString()).toString();
        }
        model.addAttribute("initPageLink", initPageLink);
        Map map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        OrgListBO orgAll = SoaConnectionFactory.get(request, ConstantsUri.ORG, map, OrgListBO.class);
        model.addAttribute("orgAll",orgAll.getDataList());
        model.addAttribute("orgTypes", OrgType.values());
        return "system/org/list";
    }
    /* 部门列表 */
    @RequiresPermissions("system:org")
    @GetMapping("/system/org/page.php")
    public String list(OrgCriteria criteria, OrgAjaxCriteria ajaxCriteria, PagerSpec pagerSpec,HttpServletRequest request,Model model){
        criteria.withPagerSpec(pagerSpec);
        ajaxCriteria.withPagerSpec(pagerSpec);
        Object param = null;
        if(!StringUtils.isEmpty(ajaxCriteria.getParentId())){
            param = ajaxCriteria;
        }else{
            param = criteria;
        }
        String link = pagerSpec.getLink();
        String currLink = new StringBuilder(request.getContextPath()).append("/system/org/list.php").append(link.substring(link.indexOf("?"))).toString();

        OrgListBO orgs = SoaConnectionFactory.get(request, ConstantsUri.ORG, param, OrgListBO.class);
        model.addAttribute("orgTypes", OrgType.values());
        model.addAttribute("orgs",orgs.getDataList());

        pagerSpec.setTotalItems(orgs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", currLink.replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        return "system/org/list_page";
    }

    /* 新增-修改 GET */
    @RequiresPermissions("system:org")
    @GetMapping("/system/org/edit.php")
    public String edit(@RequestParam(value = "currLink", defaultValue = "")String currLink,
           @RequestParam(value = "id", defaultValue = "")String id,
           @RequestParam(value = "parentId", defaultValue = "")String parentId,//新增的时候需要把页面选择的上级机构带过来
           @RequestParam(value = "parentName", defaultValue = "")String parentName,
                       HttpServletRequest request,Model model){
        String defaultCurLink = new StringBuilder(request.getContextPath()).append("/system/org/list.php").toString();
        model.addAttribute("currLink",("".equals(currLink)) ? defaultCurLink : currLink);
        if(!CommonUtils.nullOrBlank(id)){
            OrgRs orgRs = SoaConnectionFactory.getRestful(request, ConstantsUri.ORG_ID, null, OrgRs.class, id);
            model.addAttribute("org", orgRs.getData());
        }
        model.addAttribute("orgTypes", OrgType.values());

        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page", "0");
        map.put("size", "0");
        OrgListBO orgAll = SoaConnectionFactory.get(request, ConstantsUri.ORG, map, OrgListBO.class);
        model.addAttribute("orgAll",orgAll.getDataList());
        if(!CommonUtils.nullOrBlank(parentId)){
            model.addAttribute("parentId",parentId);
            model.addAttribute("parentName",parentName);
        }
        return "system/org/form";
    }

    /* 新增-修改 POST */
    @RequiresPermissions("system:org")
    @PostMapping(value = "/system/org/save.php")
    public @ResponseBody BaseResponse save(HttpServletRequest request, @RequestBody Organization organization) throws IOException {
        BaseResponse rs;
        if(CommonUtils.nullOrBlank(organization.getId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.ORG, organization, Organization.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.ORG_ID, organization, Organization.class, organization.getId());
        }
        return rs;
    }

    @RequiresPermissions("system:org")
    @PostMapping("/system/org/enable.php")
    public @ResponseBody BaseResponse del(@RequestParam(value = "id") String id,@RequestParam(value = "enable", defaultValue = "false") boolean enable, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("status",enable);
        BaseResponse rs = SoaConnectionFactory.put(request, ConstantsUri.ORG_ENABLE, map, BaseResponse.class);
        return rs;
    }
}
