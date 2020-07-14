package com.dewen.project.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件处理工具类
 *
 * @author harlin
 */
public class FileUtils {

    private static final String UTF_8 = "UTF-8";

    /**
     * 上传单个文件到指定目录
     *
     * @param multipartFile 单个文件
     * @param folder        文件目录名称（/目录名称/）
     * @return 返回文件的相对路径
     * @throws IOException
     */
    public static String uploadFile(MultipartFile multipartFile, final String folder, HttpServletRequest request) throws IOException {
        // 组装要上传文件的文件名
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = null;
        if (StringUtils.isEmpty(originalFilename) || originalFilename.indexOf(".") < 0) {
            suffix = "";
        } else {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        // 新的文件名（UUID+后缀）
        String fileName = NumberUtils.getUUID() + suffix;
        // 获取绝对路径地址
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        String date = TimeUtils.getDate();
        String relativePath = folder + date + "/" + fileName;
        File file = new File(realPath + relativePath);
        // 文件夹及文件不存在则先创建
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        InputStream inputStream = multipartFile.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(file);
        long count = IOUtils.copyLarge(inputStream, outputStream);
        System.out.println("文件写入的大小是：" + count);
        inputStream.close();
        outputStream.flush();
        outputStream.close();
        // 返回相对路径，数据库中存储
        return relativePath;
    }

    /**
     * 获取上下文路径上的文件类容（只限文本文件）
     *
     * @param servletContext
     * @param path
     * @return
     */
    public static String getWebResource(ServletContext servletContext, final String path) {
        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;
        try {
            InputStream inputStream = servletContext.getResourceAsStream(path);
            inputReader = new InputStreamReader(inputStream, UTF_8);
            bufferReader = new BufferedReader(inputReader);

            String line = null;
            StringBuffer strBuffer = new StringBuffer();
            while ((line = bufferReader.readLine()) != null) {
                strBuffer.append(line);
            }
            return strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (bufferReader != null) {
                    bufferReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 获取文件后缀
     *
     * @param orglName
     * @return
     */
    public static String getExt(final String orglName) {
        if (StringUtils.isEmpty(orglName) || orglName.lastIndexOf(".") < 0) {
            return "";
        } else {
            return orglName.substring(orglName.lastIndexOf(".") + 1, orglName.length());
        }
    }

    /**
     * 给定文件后缀，创建一个新的文件在临时文件目录
     *
     * @param ext
     * @return
     * @throws Exception
     */
    public static File getTempFile(String ext) throws Exception {
        String folder = System.getProperty("java.io.tmpdir");
        String filePath = folder + "/" + NumberUtils.getUUID() + ext;
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }

    /**
     * 通过文件后缀获取文件类型
     *
     * @param suffix
     * @return
     */
    public static String contentType(final String suffix) {
        if (suffix.equals(".BMP") || suffix.equals(".bmp")) {
            return "image/bmp";
        }
        if (suffix.equals(".GIF") || suffix.equals(".gif")) {
            return "image/gif";
        }
        if (suffix.equals(".JPEG") || suffix.equals(".jpeg") || suffix.equals(".JPG") || suffix.equals(".jpg")
                || suffix.equals(".PNG") || suffix.equals(".png")) {
            return "image/jpeg";
        }
        if (suffix.equals(".HTML") || suffix.equals(".html")) {
            return "text/html";
        }
        if (suffix.equals(".TXT") || suffix.equals(".txt")) {
            return "text/plain";
        }
        if (suffix.equals(".VSD") || suffix.equals(".vsd")) {
            return "application/vnd.visio";
        }
        if (suffix.equals(".PPTX") || suffix.equals(".pptx") || suffix.equals(".PPT") || suffix.equals(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (suffix.equals(".DOCX") || suffix.equals(".docx") || suffix.equals(".DOC") || suffix.equals(".doc")) {
            return "application/msword";
        }
        if (suffix.equals(".XML") || suffix.equals(".xml")) {
            return "text/xml";
        }
        if (suffix.equals(".apk") || suffix.equals(".APK")) {
            return "application/octet-stream";
        }
        return "text/html";
    }

    /**
     * 获取网络文件资源
     *
     * @param path
     * @return
     */
    public byte[] getResourceDataByURL(final String path) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        InputStream inputs;
        try {
            URL url = new URL(path);
            URLConnection conn = url.openConnection();
            inputs = conn.getInputStream();
            return readInputStream(inputs);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


}