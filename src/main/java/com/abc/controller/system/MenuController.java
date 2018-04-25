package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.dto.system.MenuType;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.MenuAjaxCriteria;
import com.abc.soa.request.system.MenuCriteria;
import com.abc.soa.response.system.Menu;
import com.abc.soa.response.system.bo.MenuListBO;
import com.abc.soa.response.system.bo.MenuRs;
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
 * @Author liuQi
 * @Date 2017/5/15 14:27
 * 菜单管理模块
 */
@Controller
public class MenuController extends BaseController {

    /* 列表查询 */
    @RequiresPermissions("system:menu")
    @GetMapping("/system/menu/list.php")
    public String list(HttpServletRequest request, Model model){
        StringBuilder initPageLink = new StringBuilder(request.getContextPath()).append("/system/menu/page.php");
        if(!StringUtils.isEmpty(request.getQueryString())){
            initPageLink.append("?").append(request.getQueryString()).toString();
        }
        model.addAttribute("initPageLink", initPageLink);
        Map map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        MenuListBO allMenu = SoaConnectionFactory.get(request, ConstantsUri.SYS_MENU, map, MenuListBO.class);
        model.addAttribute("allMenu",allMenu.getDataList());
        return "system/menu/list";
    }

    /* 列表查询 */
    @RequiresPermissions("system:menu")
    @GetMapping("/system/menu/page.php")
    public String page(PagerSpec pagerSpec, HttpServletRequest request,Model model, @RequestParam(value = "parentId", required = false) String parentId){
        String link = pagerSpec.getLink();
        String currLink = new StringBuilder(request.getContextPath()).append("/system/menu/list.php").append(link.substring(link.indexOf("?"))).toString();
        Object criteria;
        if(parentId != null){
            criteria =  new MenuAjaxCriteria().withParentId(parentId).withPagerSpec(pagerSpec);
        }else{
            criteria =  new MenuCriteria().withPagerSpec(pagerSpec);
        }
        MenuListBO menus = SoaConnectionFactory.get(request, ConstantsUri.SYS_MENU, criteria, MenuListBO.class);
        model.addAttribute("menus",menus.getDataList());

        pagerSpec.setTotalItems(menus.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", currLink.replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));

        Map map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        MenuListBO allMenu = SoaConnectionFactory.get(request, ConstantsUri.SYS_MENU, map, MenuListBO.class);
        model.addAttribute("allMenu",allMenu.getDataList());
        return "system/menu/list_page";
    }


    /* 新增-修改 GET */
    @RequiresPermissions("system:menu")
    @GetMapping("/system/menu/edit.php")
    public String edit(@RequestParam(value = "currLink", defaultValue = "")String currLink,
                       @RequestParam(value = "id", defaultValue = "") String id, HttpServletRequest request,Model model){
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/system/menu/list.php" : currLink;
        model.addAttribute("currLink",currLink);
        if(!CommonUtils.nullOrBlank(id)){
            MenuRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_MENU_ID, null, MenuRs.class, id);
            model.addAttribute("menu",rs.getData());
        }
        Map map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        MenuListBO menus = SoaConnectionFactory.get(request, ConstantsUri.SYS_MENU, map, MenuListBO.class);
        model.addAttribute("menus",menus.getDataList());
        model.addAttribute("menuType", MenuType.values());
        return "system/menu/form";
    }

    /* 新增-修改 POST */
    @RequiresPermissions("system:menu")
    @PostMapping(value = "/system/menu/save.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody Menu menu) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(menu.getMenuId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.SYS_MENU, menu, Menu.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.SYS_MENU_ID, menu, Menu.class, menu.getMenuId());
        }
        return rs;
    }

    /* 启用, 禁用 */
    @RequiresPermissions("system:menu")
    @PostMapping("/system/menu/enable.php")
    public @ResponseBody BaseResponse enable(@RequestParam(value = "id") String id,
                                          @RequestParam(value = "enable", defaultValue = "false") boolean enable, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("menuId",id);
        map.put("status",enable);
        return SoaConnectionFactory.put(request, ConstantsUri.SYS_MENU_ENABLE, map, BaseResponse.class);
    }

    /* 删除 */
    @RequiresPermissions("system:menu")
    @PostMapping("/system/menu/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String id, HttpServletRequest request){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.SYS_MENU_ID, null, BaseResponse.class, id);
    }
}
