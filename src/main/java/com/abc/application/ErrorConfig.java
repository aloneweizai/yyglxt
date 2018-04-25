package com.abc.application;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Author liuqi
 * @Date 2017/6/17 18:20
 * 异常页面
 */

@Configuration
public class ErrorConfig {

  /*  @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (ConfigurableEmbeddedServletContainer container) -> {
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/403"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/403"));
        };
    }*/


}
