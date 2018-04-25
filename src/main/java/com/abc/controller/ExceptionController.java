package com.abc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 500错误页面
 * @author zhushuai 2017-12-19
 *
 */
@Controller
public class ExceptionController {

	@GetMapping("/goerror.php")
	public String goErrorPage(@RequestParam String reason,
			@RequestParam String className, @RequestParam String methodName,
			@RequestParam Integer lineNumber, HttpServletRequest request) {
		StackTraceElement element = new StackTraceElement(className,
				methodName, className, lineNumber);
		request.setAttribute("reason", reason);
		request.setAttribute("stackTrace", element);
		return "500";
	}
}
