package com.abc.application;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;


/**
 * 异常捕获
 * @author zhushuai 2017-12-18
 *
 */
@RestControllerAdvice
public class GlobExceptionHandler {
	
	protected static Logger _log = LoggerFactory.getLogger(GlobExceptionHandler.class);
	
	/**
	 * 全局异常捕获
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
    public ModelAndView handle(Exception e, HttpServletRequest request) {
		//访问地址
		String url = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
		ModelAndView modelAndView = new ModelAndView("500");
		modelAndView.addObject("url", url);
		modelAndView.addObject("reason", e.toString());
		if(e.getStackTrace()!=null && e.getStackTrace().length > 0){
			StackTraceElement element = e.getStackTrace()[0];
			modelAndView.addObject("stackTrace", element);
		}
		
		_log.error("系统捕获到异常：", e);
		
		return modelAndView;
	}
}
