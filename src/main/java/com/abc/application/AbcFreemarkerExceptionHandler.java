package com.abc.application;

import java.io.IOException;
import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
/**
 * ftl异常捕获
 * @author zhushuai 2017-12-19
 *
 */
public class AbcFreemarkerExceptionHandler implements TemplateExceptionHandler {
	public void handleTemplateException(TemplateException templateexception,
			Environment environment, Writer writer) throws TemplateException {
		try {
			writer.write("<script>location.href='"+environment.getMainNamespace().get("ctx")+"/goerror.php?reason=freemarker.core.InvalidReferenceException&className="
					+ templateexception.getTemplateSourceName()
					+ "&methodName="
					+ templateexception.getBlamedExpressionString()
					+ "&lineNumber="
					+ templateexception.getEndLineNumber()
					+ "'</script>");
		} catch (IOException e) {
			throw templateexception;
		}
	}

}
