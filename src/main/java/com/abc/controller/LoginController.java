package com.abc.controller;

import com.abc.common.util.CommonUtils;
import com.abc.common.util.MD5;
import com.abc.dto.system.MenuType;
import com.abc.dto.system.MenuNode;
import com.abc.soa.response.system.Menu;
import com.abc.soa.response.system.bo.UserBO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @Author liuqi
 * @Date 2017/5/11 16:04
 */

@Controller
public class LoginController{

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = {"/login","/"})
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open ('"+request.getContextPath()+"/logon','_top')");
        out.println("</script>");
        out.println("</html>");
    }

    @GetMapping("/logon")
    public String logon(){
        return "login";
    }

    @GetMapping("/403")
    public String noPermissions() {
        return "403";
    }




    @PostMapping("/login.php")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpSession session, Model model) throws Exception{//MD5.md5(password)
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5.md5(password));
        try {
            //验证是否登录成功
            Subject currentUser = SecurityUtils.getSubject();
            //强行设置用户超时
            SecurityUtils.getSubject().getSession().setTimeout(1800000);
            
            currentUser.login(token);

            UserBO userBO = (UserBO) currentUser.getPrincipal();
            if("abc@12366".equals(password)){
            	userBO.setIsInitPassword(true);
            }
            session.setAttribute("currentUser", userBO);//没多大用
            logger.info("用户：【" + username + "】 登陆成功 ， 用户token:" + userBO.getLoginInfo().getToken());
            session.setAttribute("userToken", userBO.getLoginInfo().getToken());
            if(userBO == null){
                return "login";
            }
            List<Menu> userMenus = getUserMenus(userBO.getMenuMap());
            model.addAttribute("menus", userMenus);
            model.addAttribute("menuType", MenuType.values());
            model.addAttribute("menuNodes", getMenuParentNodes(userMenus));
            return "redirect:/index";
        } catch (Exception e) {
            if (e instanceof IncorrectCredentialsException) {
                model.addAttribute("erro", "用户名或密码错误");
            } else {
                model.addAttribute("erro", "系统异常");
            }
            token.clear();
            return "login";
        }
    }

    @RequestMapping("/index")
    public String index(Model model, UserBO userBO){
        if(userBO == null){
            return "login";
        }
        List<Menu> userMenus = getUserMenus(userBO.getMenuMap());
        model.addAttribute("menus", userMenus);
        model.addAttribute("menuType", MenuType.values());
        model.addAttribute("menuNodes", getMenuParentNodes(userMenus));
        return "index";
    }

    @GetMapping("/login/out.php")
    public String out() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

    private List<Menu> getUserMenus(Map<String, List<Menu>> roleMenusMap) {
        List<Menu> allMenu = new ArrayList<>();
        List<Menu> userMenus = new ArrayList<>();
        for (String roleId : roleMenusMap.keySet()) {
            List<Menu> menus = roleMenusMap.get(roleId);
            allMenu.addAll(menus);
        }
        Map<String, Menu> menuMap = new HashMap<>();
        for (Menu menu : allMenu) {
            if(menu.isStatus()) {
                menuMap.put(menu.getMenuId(), menu);
            }
        }
        for (String menuId : menuMap.keySet()) {
            userMenus.add(menuMap.get(menuId));
        }
        return userMenus;
    }

    private List<MenuNode> getMenuParentNodes(List<Menu> list) {
        List<MenuNode> parentNodes = new ArrayList<>();
        for (Menu menu : list) {
            if (CommonUtils.nullOrBlank(menu.getParentId()) && !MenuType.BUTTON.name().equals(menu.getType())) {
                MenuNode parentNode = new MenuNode();
                parentNode.setMenu(menu);
                setChildNode(parentNode, list);
                parentNodes.add(parentNode);
            }
        }
        if(!parentNodes.isEmpty()){
            sortMenuNode(parentNodes);
        }
        return parentNodes;
    }

    /* node表示本节点，list 代表所有菜单 */
    private void setChildNode(MenuNode node, List<Menu> list) {
        List<MenuNode> childNode = new ArrayList<>();
        for (Menu m : list) {
            if ((node.getMenu().getMenuId()).equals(m.getParentId())) {
                MenuNode n = new MenuNode();
                n.setMenu(m);
                setChildNode(n, list);
                childNode.add(n);
            }
        }
        if(!childNode.isEmpty()){
            sortMenuNode(childNode);
        }
        node.setChildNode(childNode);
    }

    private void sortMenuNode(List<MenuNode> list){
        Collections.sort(list, new Comparator<MenuNode>() {
            public int compare(MenuNode n1, MenuNode n2) {
                int sort1 = n1.getMenu().getSort();
                int sort2 = n2.getMenu().getSort();
                if (sort1 > sort2) {
                    return 1;
                } else if (sort1 == sort2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }
}
