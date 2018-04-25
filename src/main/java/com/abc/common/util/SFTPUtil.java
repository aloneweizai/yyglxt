package com.abc.common.util;

import com.abc.application.SpringCtxHolder;
import com.abc.soa.response.system.Menu;
import com.jcraft.jsch.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author xieyanmao
 */
public class SFTPUtil {

    /**
     * 将上传的文件进行重命名
     *
     * @param name
     * @return
     * @author geloin
     * @date 2012-3-29 下午3:39:53
     */
    private static String rename(String name) {

        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()));
        Long random = (long) (Math.random() * now);
        String fileName = now + "" + random;

        if (name.indexOf(".") != -1) {
            fileName += name.substring(name.lastIndexOf("."));
        }

        return fileName;
    }

    /**
     * 数组转byte数组
     *
     * @param list 数组
     * @return byte[]
     */
    public static byte[] listToByteArray(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i).byteValue();
        }
        return bytes;
    }

    /**
     * 连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static ChannelSftp connect(String host, int port, String username,
                               String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            System.out.println("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + host + ".");
        } catch (Exception e) {

        }
        return sftp;
    }

    /**
     * 上传文件
     *
     * @param directory  上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp
     */
    public void upload(String directory, String uploadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param directory 上传的目录
     * @param content   要上传的文件
     * @param sftp
     */
    public Map<String, String> uploadByByte(String directory, List<Byte> content, String fileName, ChannelSftp sftp) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            sftp.cd("/abc12366/images");
            if (isDirExist(directory, sftp)) {
                sftp.cd(directory);
            } else {
                // 建立目录
                sftp.mkdir(directory);
                // 进入并设置为当前目录
                sftp.cd(directory);
            }
            String storeName = rename(fileName);
            String filePath = "/abc12366/images/" + directory + "/" + storeName;
            OutputStream outputStream = sftp.put(filePath);
            byte[] buffer = null;
            List<Byte> content1 = (List<Byte>) content;
            if (content1 != null) {
                buffer = SFTPUtil.listToByteArray(content1);
            }
            String filePath1 = "/" + directory + "/" + storeName;
            outputStream.write(buffer);
            map.put("fileName", fileName);
            map.put("storeName", storeName);
            map.put("filePath", filePath1);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @param sftp
     */
    public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取下载文件流
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param sftp
     */
    public InputStream getInputStream(String directory, String downloadFile, ChannelSftp sftp) {
        InputStream in = null;
        try {
            sftp.cd(directory);
            in = sftp.get(downloadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws com.jcraft.jsch.SftpException
     */
    public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 判断目录是否存在
     */
    public static boolean isDirExist(String directory, ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }


    /* 上传视频 */
    public static Map<String, String> uploadMedia(String directory, MultipartFile file) throws IOException {

        InputStream is = file.getInputStream();
        long size = is.available();

        String host = SpringCtxHolder.getProperty("ftp.host");
        int port = 22;
        String username = SpringCtxHolder.getProperty("ftp.username");;
        String password = SpringCtxHolder.getProperty("ftp.password");
        ChannelSftp sftp = connect(host, port, username, password);

        Map<String, String> map = new HashMap<String, String>();
        try {
            sftp.cd("/abc12366/images");
            if (isDirExist(directory, sftp)) {
                sftp.cd(directory);
            } else {
                // 建立目录
                sftp.mkdir(directory);
                // 进入并设置为当前目录
                sftp.cd(directory);
            }
            String storeName = rename(file.getOriginalFilename());
            String filePath = "/abc12366/images/" + directory + "/" + storeName;
            OutputStream os = sftp.put(filePath);

            int i = 0;
            //缓冲大小为512字节
            byte[] buffer = new byte[512];
            while(true) {
                if(is.available() < 512) {
                    while(i != -1) {
                        i = is.read();
                        os.write(i);
                    }
                    break;
                } else {
                    //当文件的大小大于512字节时
                    is.read(buffer);
                    os.write(buffer);
                }
            }
            is.close();
            os.close();

            map.put("storeName", storeName);
            map.put("filePath", "/images/" + directory + "/" + storeName);
            map.put("fileSize", String.valueOf(size/(1024*1024)));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
    }


    public static List<String> readM3u8(MultipartFile file) throws Exception {
        Charset gbk = Charset.forName("GBK");
        ZipInputStream zin = new ZipInputStream(file.getInputStream(), gbk);
        ZipEntry ze;
        List list = new ArrayList<String>();
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                System.out.println("--"+ze.getName());
            } else {
                System.out.println(ze.getName());
                if(ze.getName().endsWith(".m3u8")){
                    list.add(ze.getName());
                }
            }
        }
        zin.closeEntry();
        return list;
    }

    public static void execCmd(String command) throws JSchException {
        String host = "118.118.116.202";
        int port = 22;
        String username = "root";
        String password = "hngs_123";

        Channel channel = null;
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setPassword(password);

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("shell");
            channel.connect(1000);

            //获取输入流和输出流
            InputStream instream = channel.getInputStream();
            OutputStream outstream = channel.getOutputStream();

            //发送需要执行的SHELL命令，需要用\n结尾，表示回车
            String shellCommand = "ls \n";
            outstream.write(shellCommand.getBytes());
            outstream.flush();

            //获取命令执行的结果
            if (instream.available() > 0) {
                byte[] data = new byte[instream.available()];
                int nLen = instream.read(data);

                if (nLen < 0) {
                    throw new Exception("network error.");
                }
                //转换输出结果并打印出来
                String temp = new String(data, 0, nLen,"iso8859-1");
                System.out.println(temp);
            }
            outstream.close();
            instream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.disconnect();
            channel.disconnect();
        }
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
        Session session = JSCHUtil.getInstance().connect("118.118.116.202", 22,
                "root", "hngs_123");
        String cmd = "cd /abc12366" + ";" + "unzip 111.zip -d 111/";
        String result = JSCHUtil.getInstance().execCmd(session, cmd);// 多条命令之间以;分隔
        System.out.println(result);
        System.exit(0);
    }



    public static void unzip(String fileName, String path) throws Exception {
        String host = SpringCtxHolder.getProperty("ftp.host");
        String username = SpringCtxHolder.getProperty("ftp.username");;
        String password = SpringCtxHolder.getProperty("ftp.password");
        Session session = JSCHUtil.getInstance().connect(host, 22, username, password);
        String cmd = "cd /abc12366/images/"+path+";unzip "+fileName+".zip -d "+fileName+"/";
        String result = JSCHUtil.getInstance().execCmd(session, cmd);// 多条命令之间以;分隔
        System.out.println(result);
    }

//    public static void main(String[] args) throws Exception {
//        unzip();
//
////        Map<String, Menu> menuMap = new HashMap<>();
//////        menuMap.put("1",new Menu());
////
////        System.out.println(menuMap.values());
////
////        System.out.println(Arrays.asList(menuMap.values()).size());
//    }
}
