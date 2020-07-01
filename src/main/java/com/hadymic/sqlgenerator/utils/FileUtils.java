package com.hadymic.sqlgenerator.utils;

import com.hadymic.sqlgenerator.config.FileConfig;
import com.hadymic.sqlgenerator.constant.FileType;
import com.hadymic.sqlgenerator.constant.InteractionType;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 文件保存工具
 *
 * @author Hadymic
 */
@Component
public class FileUtils {
    private static FileConfig fileConfig;

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36";

    private static final int TIMEOUT_SECONDS = 30;

    private static final int POOL_SIZE = 120;

    private static CloseableHttpClient httpclient;

    @Autowired
    public void setFileConfig(FileConfig fileConfig) {
        FileUtils.fileConfig = fileConfig;
    }


    static {
        initApacheHttpClient();
    }

    public static String saveFileFromInternet(String url, FileType fileType, Integer interactionType) {

        String path = fileType.getPath() + "/" + InteractionType.getPath(interactionType);
        //创建文件夹
        File file = new File(fileConfig.getFilePath() + "/" + path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //以最后一个/后的内容作为文件名
        String[] split = url.split("/");
        String fileName;
        if ("".equals(split[split.length - 1])) {
            fileName = split[split.length - 2];
        } else {
            fileName = split[split.length - 1];
        }
        String filePath = path + "/" + fileName;
        //添加文件后缀
        if (fileType == FileType.FILE_TYPE_IMAGE && !filePath.endsWith(".webp")) {
            filePath += ".webp";
        }
        if (fileType == FileType.FILE_TYPE_ICON && !filePath.endsWith(".image")) {
            filePath += ".image";
        }
        if (fileType == FileType.FILE_TYPE_APK && !filePath.endsWith(".apk")) {
            filePath = filePath.replaceAll("\\?", "");
            filePath += ".apk";
        }
        logger.info("save file: url: " + url + " filePath: " + fileConfig.getFilePath() + "/" + filePath);
        try {
            fetchContent(url, fileConfig.getFilePath() + "/" + filePath);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return "";
        }
        return filePath;
    }

    public static void initApacheHttpClient() {
        // Create global request configuration
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000)
                .setConnectTimeout(TIMEOUT_SECONDS * 1000).setConnectionRequestTimeout(TIMEOUT_SECONDS * 1000).build();

        // Create an HttpClient with the given custom dependencies and
        // configuration.
        httpclient = HttpClients.custom().setUserAgent(USER_AGENT).setMaxConnTotal(POOL_SIZE)
                .setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(defaultRequestConfig).build();
    }

    public static void destroyApacheHttpClient() {
        try {
            httpclient.close();
        } catch (IOException e) {
            logger.error("httpclient close fail", e);
        }
    }

    public static void fetchContent(String url, String filePath) throws IOException {

        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Referer", "http://www.google.com");

        logger.info("executing request " + httpget.getURI());
        CloseableHttpResponse response = httpclient.execute(httpget);

        try {
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() >= 400) {
                throw new IOException("Got bad response, error code = " + response.getStatusLine().getStatusCode()
                        + " url: " + url);
            }
            if (entity != null) {
                InputStream input = entity.getContent();
                OutputStream output = new FileOutputStream(new File(filePath));
                IOUtils.copy(input, output);
                output.flush();
            }
        } finally {
            response.close();
        }
    }
}
