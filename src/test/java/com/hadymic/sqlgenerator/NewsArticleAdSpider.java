package com.hadymic.sqlgenerator;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewsArticleAdSpider {
    public static CloseableHttpClient getSSLClient() {
        CloseableHttpClient client = null;
        try {
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] cert, String oauthType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] cert, String oauthType) throws CertificateException {
                }
            };

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);

            // 配置请求参数
            RequestConfig reqConfig = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE)
                    .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                    .setConnectionRequestTimeout(10000)
                    .setConnectTimeout(10000)
                    .setSocketTimeout(10000)
                    .build();

            // 配置Registry
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", socketFactory).build();

            // 配置Connection
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 连接池最大连接数
            connectionManager.setMaxTotal(1000);
            // 每个路由最大连接数
            connectionManager.setDefaultMaxPerRoute(20);

            client = HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(reqConfig).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static String doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 通过址默认配置创建一个httpClient实例
//            httpClient = HttpClients.createDefault();
            httpClient = getSSLClient();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            httpGet.setHeader("Content-Type", "application/json;charset=utf-8");
            httpGet.setHeader("Host", "api3-normal-c-hl.snssdk.com");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", "odin_tt=286dd72efab3637dd65970224b241ffe64178b345af2a31e6597eef5b0c71653d5944293774564b000fc71af38fec8fc; install_id=940834812870488; ttreq=1$eda122603b3be8fd39c6e2f5d36bf53bd6b72878; qh[360]=1; history=JJBNaBNRFIVvXkbm4UzyZqCgCFUUCVJwYXFRlMAz6qKbEosg1GS0iyBFA4XoQktxqBUqqLgILgpiEBQEa3UhCG6mQkFTLHKf3RgRLf6kprbpXybgL%2Fdl%2BXHPvefcMzKZ6i%2FkjgwUBs%2F1Xzw%2BkM%2Bh1%2BxYuN99PpcvfISE8kLryjPJYhh5CVJ5YbZtTDIbnXtED7etNoDF0JkmOiVPD0EkjmKdg%2FImxONvQSSGzmWieZY8RkqxSsqNS78qPuMo6kRr1vio0RNH9y8PlBeWdw%2F6zEI3TbP1rmTGZ1EUNZAKNi37491fADdfN0hZPTsfMBMFQlp59QeHlc8MdPfqnCsTswEzMO5Amvbyj%2BbecRSLes%2B8Ubw2JVBUKVkz8XQkYBydbMuh9651qw3FT%2FCJhovtJRvFmqYPe%2FZttdtRfAdfefWruz5LxtG9TX7RP8knwKLoWkTNHQdP6lmhdfPCcF%2FGRrFC7hszvYuSmeg%2B1zn%2FGW%2BBWSiWiSqHfkufmWhyPZvrnKT%2F7B7qpbz%2FRIpuOgdAquzOVF%2Bof%2F%2Bh%2FfidzoDFEUD7LY29UMXt6FYo59TR6VJADS7Q3pvE%2B5vUrlPVytrX16VPXcjLpGx0zJyhBsWSdp8dqlGW6CjthZnGK%2FrP2QLyPwAAAP%2F%2F");
            httpGet.setHeader("X-SS-REQ-TICKET", "1593327147759");
            httpGet.setHeader("sdk-version", "2");
            httpGet.setHeader("passport-sdk-version", "17");
            httpGet.setHeader("X-SS-DP", "13");
            httpGet.setHeader("x-tt-trace-id", "00-f9b2dc6709d0cf50574ac1fec9f3000d-f9b2dc6709d0cf50-01");
            httpGet.setHeader("User-Agent", "com.ss.android.article.news/7780 (Linux; U; Android 9; zh_CN; MI 6; Build/PKQ1.190118.001; Cronet/TTNetVersion:72cf00a5 2020-05-07 QuicVersion:0144d358 2020-03-24)");
//            httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpGet.setHeader("X-Khronos", "1593327147");
            httpGet.setHeader("X-Gorgon", "040460bd00001ded2744827f6b6aa2b934137f44974632b032d5");
            httpGet.setHeader("x-common-params-v2", "iid=940834812870488&device_id=56051959156&ac=wifi&mac_address=00%3AEC%3A0A%3AF6%3A98%3A3E&channel=xiaomi&aid=13&app_name=news_article&version_code=778&version_name=7.7.8&device_platform=android&ab_version=660830%2C662176%2C1417596%2C662099%2C1789430%2C668774%2C1804366%2C1413880%2C1529249%2C1419598%2C1469462%2C1812120%2C1789132%2C1809700%2C1797644%2C1576658%2C1190524%2C1789127%2C1469498%2C1484965%2C1419045%2C1157750%2C1593455%2C668775%2C668779&ab_feature=94563%2C102749&ssmix=a&device_type=MI+6&device_brand=Xiaomi&language=zh&os_api=28&os_version=9&openudid=7bcd62fb4aef81c6&manifest_version_code=7780&resolution=1080*1920&dpi=480&update_version_code=77811&plugin=18506&pos=5r_-9Onkv6e_ejsSeCoDeCUfv7G_8fLz-vTp6Pn4v6esrKSzr6uvqa-psb_x_On06ej5-L-nr6uzraqlr6Sssb_88Pzt3vTp5L-nv3o7EngqA3glH7-xv_zw_O3R8vP69Ono-fi_p6yspLOvq6-pr6mxv_zw_O3R_On06ej5-L-nr6uzraqlr6Ss4A%3D%3D&host_abi=armeabi-v7a&tma_jssdk_version=1.68.0.4&rom_version=miui_v12_20.6.18&cdid=b50e94de-46e5-4ea5-b001-8d745eedd208&oaid=be407f8d85394a15");
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
            result = EntityUtils.toString(entity, StandardCharsets.UTF_8);

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

    public static void write2Log(String filePath, String json) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), StandardCharsets.UTF_8))) {
            writer.write(json);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String filePath = "D:\\adNewsArticleLog\\log-" + dateFormat.format(date) + ".log";
        String url = "https://api3-normal-c-hl.snssdk.com/api/news/feed/v88/?st_time=386&ad_ui_style=%7B%22is_crowd_generalization_style%22%3A1%2C%22van_package%22%3A130000040%7D&lynx_template_data=%5B%5D&lynx_version=0.2.11&last_response_extra=%7B%22data%22%3A%22e30%22%7D&last_ad_show_interval=0&cached_item_num=0&discard_cids=%5B1670513018245140%2C1649440064015389%5D&list_count=31&support_rn=0&concern_id=6286225228934679042&refer=1&refresh_reason=2&session_refresh_idx=2&count=20&min_behot_time=1593326893&last_refresh_sub_entrance_interval=1593327147&gps_mode=6&loc_mode=1&loc_time=1593327144&latitude=26.078291&longitude=119.262424&city=%E7%A6%8F%E5%B7%9E%E5%B8%82&tt_from=tab&lac=23002&cid=106985731&_rticket=1593327147756&cp=5cebfc833fe2bq1";
        String json = doGet(url);
        //筛选出其中的广告
        Map jsonMap = JSONObject.parseObject(json, Map.class);
        List<Map> data = (List) jsonMap.get("data");
        for (Map article : data) {
            String content = (String) article.get("content");
            if (content.contains("广告")) {
                write2Log(filePath, content);
                System.out.println(sdf.format(date) + content);
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String filePath = "D:\\adNewsArticleLog\\log-" + dateFormat.format(date) + ".log";
            String url = "https://api3-normal-c-hl.snssdk.com/api/news/feed/v88/?st_time=386&ad_ui_style=%7B%22is_crowd_generalization_style%22%3A1%2C%22van_package%22%3A130000040%7D&lynx_template_data=%5B%5D&lynx_version=0.2.11&last_response_extra=%7B%22data%22%3A%22e30%22%7D&last_ad_show_interval=0&cached_item_num=0&discard_cids=%5B1670513018245140%2C1649440064015389%5D&list_count=31&support_rn=0&concern_id=6286225228934679042&refer=1&refresh_reason=2&session_refresh_idx=2&count=20&min_behot_time=1593326893&last_refresh_sub_entrance_interval=1593327147&gps_mode=6&loc_mode=1&loc_time=1593327144&latitude=26.078291&longitude=119.262424&city=%E7%A6%8F%E5%B7%9E%E5%B8%82&tt_from=tab&lac=23002&cid=106985731&_rticket=1593327147756&cp=5cebfc833fe2bq1";
            String json = doGet(url);
            write2Log(filePath, json);
            System.out.println(sdf.format(date) + json);
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 1, 3, TimeUnit.MINUTES);
    }
}
