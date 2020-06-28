package com.hadymic.sqlgenerator;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HttpClient4 {
    public static String doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
//            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
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

    public static void write2Log(String filePath, String json) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(json);
            writer.newLine();
            writer.flush();
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

    @Test
    public void test2() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        String filePath = "D:\\adSplashLog\\log-" + dateFormat.format(date) + ".log";
        String url = "https://api3-normal-c-hl.snssdk.com/api/ad/splash/news_article/v14/?_unused=0&carrier=%E4%B8%AD%E5%9B%BD%E8%81%94%E9%80%9A&mcc_mnc=46001&ad_area=1080x1848&sdk_version=19229&os_api=28&device_platform=android&os_version=9&display_density=1080x1920&dpi=480&device_brand=Xiaomi&device_type=MI+6&bh=368&display_dpi=480&density=3.0&ac=wifi&channel=xiaomi&aid=13&app_name=news_article&language=zh&mac_address=00%3AEC%3A0A%3AF6%3A98%3A3E&refresh_num=7&is_cold_start=0&latitude=26.078305&longitude=119.262438&iid=940834812870488&device_id=56051959156&version_code=778&version_name=7.7.8&ab_version=662176%2C1802839%2C1417596%2C662099%2C668774%2C1789430%2C1804366%2C1529249%2C668775%2C1789132%2C1469462%2C1469498%2C1576658%2C1190524%2C1789127%2C1797644%2C1157750%2C1593455%2C1484965%2C1419045%2C1413880%2C1809700%2C1419598%2C668779%2C660830&ab_feature=94563%2C102749&ssmix=a&openudid=7bcd62fb4aef81c6&manifest_version_code=7780&resolution=1080*1920&update_version_code=77811&_rticket=1592988339861&plugin=18506&pos=5r_-9Onkv6e_ejsSeCoDeCUfv7G_8fLz-vTp6Pn4v6esrKSzr6uvqa6lsb_x_On06ej5-L-nr6uzraqlrq2osb_88Pzt3vTp5L-nv3o7EngqA3glH7-xv_zw_O3R8vP69Ono-fi_p6yspLOvq6-prqWxv_zw_O3R_On06ej5-L-nr6uzraqlrq2o4A%3D%3D&host_abi=armeabi-v7a&tma_jssdk_version=1.68.0.4&rom_version=miui_v12_20.6.18&cdid=b50e94de-46e5-4ea5-b001-8d745eedd208&oaid=be407f8d85394a15";
        String json = doGet(url);
        write2Log(filePath, json);
        System.out.println(json);
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String filePath = "D:\\adSplashLog\\log-" + dateFormat.format(date) + ".log";
            String url = "https://api3-normal-c-hl.snssdk.com/api/ad/splash/news_article/v14/?_unused=0&carrier=%E4%B8%AD%E5%9B%BD%E8%81%94%E9%80%9A&mcc_mnc=46001&ad_area=1080x1848&sdk_version=19229&os_api=28&device_platform=android&os_version=9&display_density=1080x1920&dpi=480&device_brand=Xiaomi&device_type=MI+6&bh=368&display_dpi=480&density=3.0&ac=wifi&channel=xiaomi&aid=13&app_name=news_article&language=zh&mac_address=00%3AEC%3A0A%3AF6%3A98%3A3E&refresh_num=7&is_cold_start=0&latitude=26.078305&longitude=119.262438&iid=940834812870488&device_id=56051959156&version_code=778&version_name=7.7.8&ab_version=662176%2C1802839%2C1417596%2C662099%2C668774%2C1789430%2C1804366%2C1529249%2C668775%2C1789132%2C1469462%2C1469498%2C1576658%2C1190524%2C1789127%2C1797644%2C1157750%2C1593455%2C1484965%2C1419045%2C1413880%2C1809700%2C1419598%2C668779%2C660830&ab_feature=94563%2C102749&ssmix=a&openudid=7bcd62fb4aef81c6&manifest_version_code=7780&resolution=1080*1920&update_version_code=77811&_rticket=1592988339861&plugin=18506&pos=5r_-9Onkv6e_ejsSeCoDeCUfv7G_8fLz-vTp6Pn4v6esrKSzr6uvqa6lsb_x_On06ej5-L-nr6uzraqlrq2osb_88Pzt3vTp5L-nv3o7EngqA3glH7-xv_zw_O3R8vP69Ono-fi_p6yspLOvq6-prqWxv_zw_O3R_On06ej5-L-nr6uzraqlrq2o4A%3D%3D&host_abi=armeabi-v7a&tma_jssdk_version=1.68.0.4&rom_version=miui_v12_20.6.18&cdid=b50e94de-46e5-4ea5-b001-8d745eedd208&oaid=be407f8d85394a15";
            String json = doGet(url);
            write2Log(filePath, json);
            System.out.println(sdf.format(date) + json);
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.MINUTES);
    }
}
