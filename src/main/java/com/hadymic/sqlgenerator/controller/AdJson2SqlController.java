package com.hadymic.sqlgenerator.controller;

import com.hadymic.sqlgenerator.mapper.AdImageMapper;
import com.hadymic.sqlgenerator.mapper.AdInfoMapper;
import com.hadymic.sqlgenerator.mapper.AdJsonRootBeanMapper;
import com.hadymic.sqlgenerator.model.AdImage;
import com.hadymic.sqlgenerator.model.AdInfo;
import com.hadymic.sqlgenerator.model.AdJsonRootBean;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ad")
public class AdJson2SqlController {
    @Autowired
    private IAdJson2SqlService adJson2SqlService;

    @Autowired
    private AdJsonRootBeanMapper adJsonRootBeanMapper;

    @Autowired
    private AdImageMapper adImageMapper;

    @Autowired
    private AdInfoMapper adInfoMapper;

    private static Logger logger = LoggerFactory.getLogger(AdJson2SqlController.class);

    private static int adNum = 0;

    @PostMapping("save")
    public String saveAd(@RequestBody AdJsonRootBean root) {
        logger.info(++adNum + " : AdJsonBean: " + root.toString());
        boolean b = adJson2SqlService.saveAd(root);
        return b ? "success" : "fail";
    }

    @GetMapping("get")
    public List<AdInfo> get() {
        List<AdInfo> adInfos = adInfoMapper.selectAll();

        for (AdInfo adInfo : adInfos) {
            String imageIds = adInfo.getImage_ids();
            imageIds = imageIds.substring(1, imageIds.length() - 1);
            String[] split = imageIds.split(",");

            List<AdImage> adImageList = new ArrayList<>();
            for (String imageId : split) {
                AdImage adImage = adImageMapper.selectById(imageId);
                adImageList.add(adImage);
            }
            adInfo.setImages(adImageList);
        }
        return adInfos;
    }
}
