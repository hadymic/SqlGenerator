package com.hadymic.sqlgenerator;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpClient4 {
    public String doPost(String url, String json) {
        CloseableHttpClient httpClient;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/json");
        // 封装post请求参数
        httpPost.setEntity(new StringEntity(json, "utf-8"));
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void read2Buffer(String filePath, String url) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            int num = 0;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\\\+\"", "\"");//剔除例如\\"，这样的转义
                line = line.replaceAll("\\\\\\\\/", "/");//更改\\/为/
                line = line.replaceAll("\"\\{", "{");//大括号前有引号就去除
                line = line.replaceAll("}\"", "}");//大括号后有引号就去除
                //去除多余的[]
                line = line.replaceAll("\\[\\[\\{", "[{");
                line = line.replaceAll("}]]", "}]");
                line = line.replaceAll("}],\\[\\{", "},{");
                //将playable_style的""空字符串改{}
                line = line.replaceAll("\"playable_style\":\"\"", "\"playable_style\":{}");
                //将diff_data的""空字符串改{}
                line = line.replaceAll("\"diff_data\":\"\"", "\"diff_data\":{}");
                //将tpl_info中data的""空字符串改{}
                line = line.replaceAll("\"data\":\"\"", "\"data\":{}");

                System.out.println(++num + " : " + line);
                doPost(url, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        String filePath = "D:\\log-20200617.log";
        String url = "http://localhost:8080/save";
        read2Buffer(filePath, url);
    }
}
