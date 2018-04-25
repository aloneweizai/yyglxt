package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.system.UserRole;
import com.abc.soa.response.system.bo.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * 角色管理
 * @Author liuqi
 * @Date 2017/5/18 16:42
 */
@Controller
public class RoleController extends BaseController {

    /* 列表 GET */
    @RequiresPermissions("system:role")
    @GetMapping("/system/role/list.php")
    public String list(PagerSpec pagerSpec, HttpServletRequest request,Model model){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        RoleListBO roles = SoaConnectionFactory.get(request, ConstantsUri.ROLE, map, RoleListBO.class);
        model.addAttribute("roles",roles.getDataList());
        pagerSpec.setTotalItems(roles.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "system/role/list";
    }

    /* 新增 GET */
    @RequiresPermissions("system:role")
    @GetMapping("/system/role/add.php")
    public String add(HttpServletRequest request,Model model){
        model.addAttribute("currLink",request.getContextPath()+"/system/role/list.php");
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        MenuListBO menus = SoaConnectionFactory.get(request, ConstantsUri.SYS_MENU, map, MenuListBO.class);
        model.addAttribute("menus",menus.getDataList());
        OrgListBO orgAll = SoaConnectionFactory.get(request, ConstantsUri.ORG, map, OrgListBO.class);
        model.addAttribute("orgAll",orgAll.getDataList());
        UserListBO users = SoaConnectionFactory.get(request, ConstantsUri.OPERATOR, map, UserListBO.class);
        model.addAttribute("users",users.getDataList());
        return "system/role/form";
    }

    /* 修改 GET */
    @RequiresPermissions("system:role")
    @GetMapping("/system/role/edit/{id}.php")
    public String edit(@RequestParam(value = "currLink", defaultValue = "")String currLink,
                       @PathVariable("id") String roleId,
                       HttpServletRequest request,Model model){
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/system/role/list.php" : currLink;
        model.addAttribute("currLink",currLink);
        RoleRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.ROLE_ID, null, RoleRs.class, roleId);
        model.addAttribute("role", rs.getData());
        RoleRs roleUserRs = SoaConnectionFactory.getRestful(request, ConstantsUri.ROLE_USER_ID, null, RoleRs.class, roleId);
        model.addAttribute("roleUser", roleUserRs.getData());

        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        MenuListBO menus = SoaConnectionFactory.get(request, ConstantsUri.SYS_MENU, map, MenuListBO.class);
        model.addAttribute("menus",menus.getDataList());
        OrgListBO orgAll = SoaConnectionFactory.get(request, ConstantsUri.ORG, map, OrgListBO.class);
        model.addAttribute("orgAll",orgAll.getDataList());
        UserListBO users = SoaConnectionFactory.get(request, ConstantsUri.OPERATOR, map, UserListBO.class);
        model.addAttribute("users",users.getDataList());
        return "system/role/form";
    }

    /* 查询单个角色 GET */
    @RequiresPermissions("system:role")
    @GetMapping("/system/role/view/{id}.php")
    public String view(@RequestParam(value = "currLink", defaultValue = "")String currLink,
                       @PathVariable(value = "id") String roleId,
                          HttpServletRequest request,Model model) throws IOException{
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/system/role/list.php" : currLink;
        model.addAttribute("currLink",currLink);
        RoleRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.ROLE_ID, null, RoleRs.class, roleId);
        model.addAttribute("role", rs.getData());
        model.addAttribute("menus",rs.getData().getMenuList());
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        OrgListBO orgAll = SoaConnectionFactory.get(request, ConstantsUri.ORG, map, OrgListBO.class);
        model.addAttribute("orgAll",orgAll.getDataList());
        RoleRs roleUserRs = SoaConnectionFactory.getRestful(request, ConstantsUri.ROLE_USER_ID, null, RoleRs.class, roleId);
        model.addAttribute("roleUser", roleUserRs.getData());
        UserListBO users = SoaConnectionFactory.get(request, ConstantsUri.OPERATOR, map, UserListBO.class);
        model.addAttribute("users",users.getDataList());
        return "system/role/view";
    }

    /* 新增修改某个角色-权限关系 */
    @RequiresPermissions("system:role")
    @PostMapping(value = "/system/role/menu/save.php")
    public @ResponseBody BaseResponse saveMenu(@RequestBody RoleBO roleBO, HttpServletRequest request) throws IOException {
        RoleBORs rs;
        roleBO.setStatus(true);
        if(CommonUtils.nullOrBlank(roleBO.getId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.ROLE, roleBO, RoleBORs.class);
        }else{
            roleBO.setId(roleBO.getId());
            rs = SoaConnectionFactory.put(request, ConstantsUri.ROLE_ID, roleBO, RoleBORs.class, roleBO.getId());
        }
        return rs;
    }

    /* 新增修改某个角色-用户关系 */
    @RequiresPermissions("system:role")
    @PostMapping(value = "/system/role/operator/save.php")
    public @ResponseBody UserRoleRs saveOperator(@RequestBody UserRole userRole, HttpServletRequest request) throws IOException {
            return SoaConnectionFactory.put(request, ConstantsUri.ROLE_USER, userRole, UserRoleRs.class);
    }

    /* 停用，启用 */
    @RequiresPermissions("system:role")
    @PostMapping(value = "/system/role/status/{id}/{status}.php")
    public @ResponseBody BaseResponse updateStatus(@PathVariable(value = "id") String roleId,
                                                   @PathVariable(value = "status") boolean status,
                                                   HttpServletRequest request) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",roleId);
        map.put("status",status);
        return SoaConnectionFactory.put(request, ConstantsUri.ROLE_ENABLE, map, BaseResponse.class);
    }

    /* 删除 */
    @RequiresPermissions("system:role")
    @DeleteMapping(value = "/system/role/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String roleId,HttpServletRequest request) throws IOException {
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.ROLE_ID, null, BaseResponse.class, roleId);
    }
}
