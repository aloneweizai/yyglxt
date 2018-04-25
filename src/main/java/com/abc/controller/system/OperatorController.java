package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.MD5;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.OperatorCriteria;
import com.abc.soa.response.system.Menu;
import com.abc.soa.response.system.bo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuqi
 * @Date 2017/5/12 9:55
 * 运营员管理
 */
@Controller
public class OperatorController  extends BaseController {

    @RequiresPermissions("system:operator")
    @GetMapping("/system/operator/list.php")
    public String list(HttpServletRequest request, Model model){
        StringBuilder initPageLink = new StringBuilder(request.getContextPath()).append("/system/operator/page.php");
        if(!StringUtils.isEmpty(request.getQueryString())){
            initPageLink.append("?").append(request.getQueryString()).toString();
        }
        model.addAttribute("initPageLink", initPageLink);
        Map map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        OrgListBO orgAll = SoaConnectionFactory.get(request, ConstantsUri.ORG, map, OrgListBO.class);
        model.addAttribute("orgAll",orgAll.getDataList());
        return "system/operator/list";
    }

    /* 运营员列表 */
    @RequiresPermissions("system:operator")
    @GetMapping("/system/operator/page.php")
    public String list(OperatorCriteria criteria,PagerSpec pagerSpec,HttpServletRequest request, Model model,HttpSession session){
        String link = pagerSpec.getLink();
        String currLink = new StringBuilder(request.getContextPath()).append("/system/operator/list.php").append(link.substring(link.indexOf("?"))).toString();

        criteria.withPagerSpec(pagerSpec);
        UserListBO users = SoaConnectionFactory.get(request, ConstantsUri.OPERATOR, criteria, UserListBO.class);
        model.addAttribute("users",users.getDataList());

        pagerSpec.setTotalItems(users.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", currLink.replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));

        session.removeAttribute("LogisticsRq");
        session.removeAttribute("InvoiceLyRq");
        session.removeAttribute("InvoiceRepoRq");
        return "system/operator/list_page";
    }

    /* 新增-更新get */
    @RequiresPermissions("system:operator")
    @GetMapping("/system/operator/edit.php")
    public String edit(@RequestParam(value = "currLink", defaultValue = "")String currLink,
                       @RequestParam(value = "id", defaultValue = "")String id, HttpServletRequest request, Model model){
        String defaultCurLink = new StringBuilder(request.getContextPath()).append("/system/operator/list.php").toString();
        model.addAttribute("currLink",("".equals(currLink)) ? defaultCurLink : currLink);
        if(!CommonUtils.nullOrBlank(id)){
            UserRs userRs = SoaConnectionFactory.getRestful(request, ConstantsUri.OPERATOR_ID, null, UserRs.class, id);
            model.addAttribute("user",userRs.getData());

            Subject currentUser = SecurityUtils.getSubject();
            UserBO currUser = (UserBO) currentUser.getPrincipal();
            Map<String, List<Menu>> roleMenusMap = currUser.getMenuMap();
            if(roleMenusMap != null){
                outer:
                for (String roleId : roleMenusMap.keySet()) {
                    List<Menu> menus = roleMenusMap.get(roleId);
                    if(menus != null){
                        for (Menu menu : menus){
                            if(menu.isStatus() && "/system/operator/resetPassword/{id}.php".equals(menu.getMenuUrl())){
                                model.addAttribute("resetPassword", true);
                                break outer;
                            }
                        }
                    }
                }
            }
        }
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        OrgListBO orgAll = SoaConnectionFactory.get(request, ConstantsUri.ORG, map, OrgListBO.class);
        model.addAttribute("orgAll",orgAll.getDataList());
        RoleListBO allRole = SoaConnectionFactory.get(request, ConstantsUri.ROLE, map, RoleListBO.class);
        model.addAttribute("allRole",allRole.getDataList());
        return "system/operator/form";
    }

    /* 新增POST */
    @RequiresPermissions("system:operator")
    @PostMapping("/system/operator/save.php")
    public @ResponseBody BaseResponse add(@RequestBody UserBO userBO, HttpServletRequest request) throws Exception {
        BaseResponse rs;
        if(CommonUtils.nullOrBlank(userBO.getId())){
            userBO.setPassword(MD5.md5(userBO.getPassword()));
            rs = SoaConnectionFactory.post(request, ConstantsUri.OPERATOR, userBO, BaseResponse.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.OPERATOR_ID, userBO, BaseResponse.class, userBO.getId());
        }
        return rs;
    }

    @RequiresPermissions("system:operator")
    @PostMapping("/system/operator/enable.php")
    public @ResponseBody BaseResponse del(@RequestParam(value = "id") String id,
                                          @RequestParam(value = "enable", defaultValue = "false") boolean enable,
                                          UserBO userBO,
                                          HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("status",enable);
        if(id.equals(userBO.getId())){
            return new BaseResponse(false,"不能停用自己的账号");
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.OPERATOR_ENABLE, map, BaseResponse.class);
        }
    }

    @RequiresPermissions("system:operator")
    @PostMapping("/system/operator/password.php")
    public @ResponseBody BaseResponse password(@RequestParam(value = "oldPw") String oldPw,
                                               @RequestParam(value = "newPw") String newPw, HttpServletRequest request) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        UserBO userBO = (UserBO)currentUser.getPrincipal();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id",userBO.getId());
        map.put("password",MD5.md5(oldPw));
        map.put("newPassword",MD5.md5(newPw));
        
        BaseResponse response=SoaConnectionFactory.put(request, ConstantsUri.OPERATOR_PASSWORD, map, BaseResponse.class);

        if("2000".equals(response.getCode())){
        	userBO.setIsInitPassword(false);
        	request.getSession().setAttribute("currentUser", userBO);
        }
        return response;
    }

    @RequiresPermissions("system:operator")
    @PostMapping("/system/operator/resetPassword/{id}.php")
    public @ResponseBody BaseResponse resetPassword(@PathVariable(value = "id") String id, HttpServletRequest request) throws Exception {
        return SoaConnectionFactory.put(request, ConstantsUri.OPERATOR_PASSWORD_RESET, null, BaseResponse.class, id);
    }
}
