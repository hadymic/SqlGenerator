package com.hadymic.sqlgenerator.controller;

import com.hadymic.sqlgenerator.mapper.AdImageMapper;
import com.hadymic.sqlgenerator.mapper.AdJsonRootBeanMapper;
import com.hadymic.sqlgenerator.model.AdImage;
import com.hadymic.sqlgenerator.model.AdInfo;
import com.hadymic.sqlgenerator.model.AdJsonRootBean;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log
@RestController
public class AdJson2SqlController {
    @Autowired
    private IAdJson2SqlService adJson2SqlService;

    @Autowired
    private AdJsonRootBeanMapper adJsonRootBeanMapper;

    @Autowired
    private AdImageMapper adImageMapper;

    private static int adNum = 0;

    @PostMapping("save/ad")
    public String saveAd(@RequestBody AdJsonRootBean root) {
        log.info(++adNum + " : AdJsonBean: " + root.toString());
        boolean b = adJson2SqlService.saveAd(root);
        return b ? "success" : "fail";
    }

    @GetMapping("get")
    public List<AdInfo> get() {
        List<AdInfo> adInfos = adJsonRootBeanMapper.selectAdInfo();
        for (AdInfo adInfo : adInfos) {
            String image_ids = adInfo.getImage_ids();
            String[] imageIds = image_ids.substring(1, image_ids.length() - 1).split(",");
            List<AdImage> images = adImageMapper.selectBatchIds(Arrays.asList(imageIds));
            List<String> urls = images.stream().map(AdImage::getUrl).collect(Collectors.toList());
            adInfo.setImage_url(urls);

            log.info(++adNum + " : AdInfo: " + adInfo.toString());
        }
        return adInfos;
    }
}
