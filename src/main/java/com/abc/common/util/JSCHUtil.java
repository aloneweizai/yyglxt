package com.abc.common.util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author liuQi
 * @Date 2017/9/23 18:35
 */
public class JSCHUtil {

    private static JSCHUtil instance;

    public static JSCHUtil getInstance() {
        if (instance == null) {
            instance = new JSCHUtil();
        }
        return instance;
    }

    private JSCHUtil() {

    }

    private Session getSession(String host, int port, String ueseName)
            throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(ueseName, host, port);
        return session;
    }

    public Session connect(String host, int port, String ueseName,
                           String password) throws Exception {
        Session session = getSession(host, port, ueseName);
        session.setPassword(password);
        Properties config = new Properties();
        config.setProperty("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        return session;
    }

    public String execCmd(Session session, String command) throws Exception {
        if (session == null) {
            throw new RuntimeException("Session is null!");
        }
        ChannelExec exec = (ChannelExec) session.openChannel("exec");
        InputStream in = exec.getInputStream();
        byte[] b = new byte[1024];

        exec.setCommand(command);
        exec.connect();
        StringBuffer buffer = new StringBuffer();
        while (in.read(b) > 0) {
            buffer.append(new String(b));
        }
        exec.disconnect();

        return buffer.toString();
    }

    public void clear(Session session) {
        if (session != null && session.isConnected()) {
            session.disconnect();
            session = null;
        }
    }

    public static void main(String[] args) throws Exception {
        Session session = JSCHUtil.getInstance().connect("118.118.116.202", 22, "root", "hngs_123");
        String cmd = "cd /abc12366/images/course" + ";" + "ls;unzip 201709121515432070031704653.zip";
//        String cmd = "unzip /abc12366/images/course/2017091215144719582438194841.zip";// + ";" + "ls";
//        String cmd = "ls /";
        String result = JSCHUtil.getInstance().execCmd(session, cmd);// 多条命令之间以;分隔
        System.out.println(result);
//        System.exit(0);
    }

}
