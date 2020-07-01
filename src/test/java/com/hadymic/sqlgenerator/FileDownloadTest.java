package com.hadymic.sqlgenerator;

import com.hadymic.sqlgenerator.constant.FileType;
import com.hadymic.sqlgenerator.constant.InteractionType;
import com.hadymic.sqlgenerator.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileDownloadTest {

    @Test
    public void testImage() {
//        String url = "http://sf3-ttcdn-tos.pstatp.com/img/web.business.image/202005225d0da2a71c7407fd4d4a89f0~noop.webp";
//        String url = "http://sf1-ttcdn-tos.pstatp.com/obj/web.business.image/202006015d0d4416cdaab31d4a2380f4";

//        String url = "https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/30a760001fbc01fd6a5ea~noop.webp";
        String url = "https://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/325c7000f07c1c00d8a4c~noop.webp";
        FileUtils.saveFileFromInternet(url, FileType.FILE_TYPE_IMAGE, InteractionType.INTERACTION_TYPE_LANDING_PAGE.getType());
    }

    @Test
    public void testIcon() {
//        String url = "http://sf1-ttcdn-tos.pstatp.com/obj/web.business.image/202006035d0dcda2c1b183c741799284";
//        String url = "http://p3-tt.byteimg.com/img/web.business.image/202005265d0d80e3083373aa4003bb02~100x100.image";
        String url = "https://p3-tt.byteimg.com/img/web.business.image/202002065d0d83170e5a2c7d4c2f8e01~100x100.image";
        FileUtils.saveFileFromInternet(url, FileType.FILE_TYPE_ICON, InteractionType.INTERACTION_TYPE_LANDING_PAGE.getType());
    }

    @Test
    public void testApk() {
//        String url = "https://ad.toutiao.com/advertiser_package/dl/fa3b1087_1650912836426760_1587118686163";
//        String url = "https://abtest-wailaxin.1sapp.com/download/apk?app=qclean&dtu=28751";
//        String url = "https://misc.wcd.qq.com/app?packageName=com.tencent.qqpimsecure&channelId=1100111261";
//        String url = "https://s.toutiao.com/o7cEL/";
//        String url = "http://static-apk.supamob.com.cn/filemagic/212477.apk";
        String url = "http://s.huoshanzhibo.com/onHe6/";
        FileUtils.saveFileFromInternet(url, FileType.FILE_TYPE_APK, InteractionType.INTERACTION_TYPE_LANDING_PAGE.getType());
    }

    @Test
    public void testVideo() {
        String url = "http://v3-ad.ixigua.com/57efedcd1633276ced4141204749339e/5efbfcda/video/tos/cn/tos-cn-ve-51/123456bc85f6435588fa884c35e898e9/toutiao.mp4";
        FileUtils.saveFileFromInternet(url, FileType.FILE_TYPE_ICON, InteractionType.INTERACTION_TYPE_LANDING_PAGE.getType());
    }
}
