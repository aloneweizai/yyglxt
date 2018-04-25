package com.abc.application.redis;


import java.util.Set;

/**
 * @Author liuQi
 * @Date 2017/9/27 22:09
 */
public interface RedisManager {

    void init();

    byte[] get(byte[] key);

    byte[] set(byte[] key, byte[] value);

    byte[] set(byte[] key, byte[] value, int expire);

    void del(byte[] key);

    void flushDB();

    Long dbSize();

    Set<byte[]> keys(String pattern);

    int getExpire();

    void setExpire(int expire);

    String getHost();

    void setHost(String host);

    int getPort();

    void setPort(int port);

    int getTimeout();

    void setTimeout(int timeout);

    String getPassword();

    void setPassword(String password);


}
