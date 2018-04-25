package com.abc.argResolver;

import com.abc.soa.response.system.bo.UserBO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author liuqi
 * @Date 2017/6/30 16:08
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == UserBO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nwr, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        UserBO userBO = (UserBO) currentUser.getPrincipal();
        return userBO;
    }
}
