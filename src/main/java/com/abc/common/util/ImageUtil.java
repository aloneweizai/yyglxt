package com.abc.common.util;


import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 15-5-20.
 */
public class ImageUtil {

    protected static Logger _log = LoggerFactory.getLogger(ImageUtil.class);

    public static void replaceImageColor(String file, Color srcColor, Color targetColor) throws IOException {
        URL http;
        if (file.trim().startsWith("https")) {
            http = new URL(file);
            HttpsURLConnection conn = (HttpsURLConnection) http.openConnection();
            conn.setRequestMethod("GET");
        } else if (file.trim().startsWith("http")) {
            http = new URL(file);
            HttpURLConnection conn = (HttpURLConnection) http.openConnection();
            conn.setRequestMethod("GET");
        } else {
            http = new File(file).toURI().toURL();
        }
        BufferedImage bi = ImageIO.read(http.openStream());

        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                System.out.println(bi.getRGB(i, j));
                if (srcColor.getRGB() == bi.getRGB(i, j)) {
                    System.out.println(i + "," + j + "  from:" + srcColor.getRGB() + "to" + targetColor.getRGB());
                    bi.setRGB(i, j, targetColor.getRGB());
                }
            }
        }
        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = it.next();
        File f = new File("c://test02.png");
        ImageOutputStream ios = null;
        try {
            ios = ImageIO.createImageOutputStream(f);
            writer.setOutput(ios);
            writer.write(bi);
//            bi.flush();
//            ios.flush();
//            ios.close();
        } catch (Exception e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (bi != null) {
                bi.flush();
            }
            if (ios != null) {
                ios.flush();
                ios.close();
            }
        }
    }

    public static void createImage(int width, int height) throws IOException {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphic = bi.createGraphics();
        graphic.setColor(new Color(0.2f, 0.3f, 0.4f, 0.4f));
        graphic.fillRect(0, 0, width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //result[i][j] = bi.getRGB(i, j) & 0xFFFFFF;
                System.out.println(bi.getRGB(i, j));
                // bi.setRGB(i, j, 0xFFFFFF);
            }
        }

        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = it.next();
        File f = new File("c://test02.png");
        ImageOutputStream ios = null;
        try {
            ios = ImageIO.createImageOutputStream(f);
            writer.setOutput(ios);

            writer.write(bi);
        } catch (Exception e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (bi != null) {
                bi.flush();
            }
            if (ios != null) {
                ios.flush();
                ios.close();
            }
        }
    }

    public static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight) {
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    /**
     * 压缩图片，不同尺寸的图片采用不同的压缩比
     */
    public static byte[] compressedPicByteArray(InputStream inputStream, String picType) throws Exception {
        BufferedImage bi = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //对图片进行压缩
        try {
            if (inputStream != null) {
                bi = Thumbnails.of(inputStream).scale(1f).asBufferedImage();

                int width = Integer.parseInt(BaseObject.getConfig("PIC_WIDTH_COMPRESS"));

                if (bi.getWidth() < width) {
                    bi = Thumbnails.of(bi).scale(1f).outputQuality(1f).asBufferedImage();
                } else if (width <= bi.getWidth()) {  //图片宽度超过设置值，就将图片尺寸等比例缩小到一半
                    bi = Thumbnails.of(bi).scale(0.5f).outputQuality(1f).asBufferedImage();
                }
            }
            ImageIO.write(bi, picType, out);
            return out.toByteArray();

        } catch (IOException e) {
            _log.error("图片压缩出错");
            e.printStackTrace();
            throw e;
        } finally {
            try {
                inputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据文件后缀名，判断是否属于指定的几种图片格式
     */
    public static boolean isImage(String fileExt) {
        String[] picTypes = {"png", "jpg", "jpeg", "bmp"};
        for (String picType : picTypes) {
            if (picType.equals(fileExt.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为图片，然后根据图片宽度判断是否对图片文件进行压缩，最后将文件转为List<Byte>
     *
     * @param file
     * @param filePureExt
     * @return
     * @throws Exception
     */
    public static List<Byte> fileBytesToList(MultipartFile file, String filePureExt) throws Exception {
        List<Byte> content = null;
        if (ImageUtil.isImage(filePureExt)) {
            try {
                byte[] compressedByteArray = ImageUtil.compressedPicByteArray(file.getInputStream(), filePureExt);
                content = FileOperateUtil.fileBytesToList(compressedByteArray);
            } catch (Exception compressErro) {
                compressErro.printStackTrace();
                content = FileOperateUtil.fileBytesToList(file.getBytes());
            }
        } else {
            content = FileOperateUtil.fileBytesToList(file.getBytes());
        }

//        content = FileOperateUtil.fileBytesToList(file.getBytes());
        return content;
    }
}
