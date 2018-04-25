package com.abc.application.redis;

import com.abc.application.SpringCtxHolder;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;
import java.util.*;

/**
 * 多节点redis
 * @Author liuQi
 * @Date 2017/9/27 17:36
 */
public class ShareRedisManager extends SpringCtxHolder implements RedisManager{
    private String host = "127.0.0.1";
    private int port = 6379;
    private int expire = 0;
    private int timeout = 0;
    private String password = "";
    private static JedisCluster jedisCluster = null;

    public void init() {
        if(jedisCluster == null){
        	String[] hostPorts = getProperty("abc.redis.hostName").split(";");
            Set<HostAndPort> nodes = new HashSet<HostAndPort>();
            for (String hostPort: hostPorts){
                nodes.add(new HostAndPort(hostPort.split(":")[0], Integer.valueOf(hostPort.split(":")[1])));
            }
            jedisCluster = new JedisCluster(nodes, new GenericObjectPoolConfig());
        }
    }

    public byte[] get(byte[] key) {
        Object value = null;
        byte[] value1;
        try {
            value1 = jedisCluster.get(key);
        } finally {
        }
        return value1;
    }

    public byte[] set(byte[] key, byte[] value) {
        try {
            jedisCluster.set(key, value);
            if(this.expire != 0) {
                jedisCluster.expire(key, this.expire);
            }
        } finally {
        }

        return value;
    }

    public byte[] set(byte[] key, byte[] value, int expire) {
        try {
            jedisCluster.set(key, value);
            if(expire != 0) {
                jedisCluster.expire(key, expire);
            }
        } finally {
        }

        return value;
    }

    public void del(byte[] key) {
        try {
            jedisCluster.del(key);
        } finally {
        }
    }

    public void flushDB() {
        try {
            jedisCluster.flushDB();
        } finally {
        }

    }

    public Long dbSize() {
        Long dbSize = Long.valueOf(0L);
        try {
            dbSize = jedisCluster.dbSize();
        } finally {
        }

        return dbSize;
    }

    public Set<byte[]> keys(String pattern) {
        TreeSet<byte[]> keys = new TreeSet<>();
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for(String k : clusterNodes.keySet()){
            JedisPool jp = clusterNodes.get(k);
            Jedis connection = jp.getResource();
            try {
                Set<String> selectKeysStr = connection.keys(pattern);
                Set<byte[]> selectKeysByte = new TreeSet<>();
                for (String s: selectKeysStr){
                    selectKeysByte.add(s.getBytes());
                }
                keys.addAll(selectKeysByte);
            } catch(Exception e){
            } finally{
                connection.close();//用完一定要close这个链接！！！
            }
        }
        return keys;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getExpire() {
        return this.expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
