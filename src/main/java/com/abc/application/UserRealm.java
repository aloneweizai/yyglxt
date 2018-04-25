package com.abc.application;

import com.abc.application.redis.RedisSessionDAO;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.CommonUtils;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.system.Menu;
import com.abc.soa.response.system.bo.OrgRs;
import com.abc.soa.response.system.bo.UserBO;
import com.abc.soa.response.system.bo.UserRs;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @Author liuqi
 * @Date 2017/6/14 16:59
 */
public class UserRealm extends AuthorizingRealm{

    /* 权限认证 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserBO userBO = (UserBO)principalCollection.getPrimaryPrincipal();
        List<Menu> userMenus = getUserMenus(userBO.getMenuMap());
        SimpleAuthorizationInfo info  = new SimpleAuthorizationInfo();
        /*TODO 测试*/
//        if("abcadmin".equals(userBO.getUsername())){
            info.addStringPermission("system_menu");
//        }
        for (Menu menu: userMenus){
            String perms = menu.getPerms();
            if(!CommonUtils.nullOrBlank(perms)){
                String[] permsArr = perms.split(";");
                for (String perm : permsArr){
                    if(!perm.equals("system_menu")){
                        info.addStringPermission(perm);
                    }
                }
            }
        }
        return info;
    }

    /* 账号密码认证 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("username", token.getUsername());
        map.put("password", new String(token.getPassword()));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserRs res = SoaConnectionFactory.post(request, ConstantsUri.USER_LOGIN, map, UserRs.class);
        if(res == null || !res.isSuccess()){
            throw new IncorrectCredentialsException();
        }
        UserBO user = res.getData();
        if(user == null || user.getStatus() == false || CommonUtils.nullOrBlank(user.getOrgId())){
            throw new IncorrectCredentialsException();
        }else{
            request.getSession().setAttribute("userToken", user.getLoginInfo().getToken());
        }
        OrgRs orgRs = SoaConnectionFactory.getRestful(request, ConstantsUri.ORG_ID, null, OrgRs.class, user.getOrgId());
        if(orgRs == null || !orgRs.isSuccess() || orgRs.getData() == null){
            throw new IncorrectCredentialsException();
        }
        if(orgRs.getData().getStatus() == Boolean.FALSE){
            throw new IncorrectCredentialsException();
        }
        Session session = getSessionByUsername(token.getUsername());
        if(session != null){
            kickOutSession(session);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token.getPassword(), getName());
        return info;
    }

    private  List<Menu> getUserMenus(Map<String, List<Menu>> roleMenusMap){
        List<Menu> allMenu = new ArrayList<>();
        List<Menu> userMenus = new ArrayList<>();
        for (String roleId: roleMenusMap.keySet()){
            List<Menu> menus = roleMenusMap.get(roleId);
            allMenu.addAll(menus);
        }
        Map<String, Menu> menuMap = new HashMap<>();
        for (Menu menu: allMenu){
            if(menu.isStatus()){
                menuMap.put(menu.getMenuId(),menu);
            }
        }
        for (String menuId: menuMap.keySet()){
            userMenus.add(menuMap.get(menuId));
        }
        return userMenus;
    }

    private static Session getSessionByUsername(String username){
        RedisSessionDAO sessionDAO = SpringCtxHolder.getBean(RedisSessionDAO.class);
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for(Session session : sessions){
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != session && obj !=null){
                SimplePrincipalCollection sp = (SimplePrincipalCollection)obj;
                Object pp = sp.getPrimaryPrincipal();
                if(pp !=null && pp instanceof UserBO){
                    UserBO user =  (UserBO)pp;
                    if(StringUtils.equals(user.getUsername(),username)){
                        return session;
                    }
                }

            }
        }
        return null;
    }

    /**
     * 用户登录系统，踢除缓存里面相同用户名的用户
     */
    private static void kickOutSession(Session session){
        Session currSession = SecurityUtils.getSubject().getSession();
        String currSessionId = null;
        if(currSession != null){
            currSessionId = currSession.getId().toString();
        }
        if(null != session && !StringUtils.equals(String.valueOf(session.getId()), currSessionId)){
            RedisSessionDAO sessionDAO = SpringCtxHolder.getBean(RedisSessionDAO.class);
            sessionDAO.delete(session);
        }
    }

}
