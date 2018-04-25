package com.abc.application.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author liuQi
 * @Date 2017/9/28 01:38
 */

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800)
public class SessionConfig {}
