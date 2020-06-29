package com.hadymic.sqlgenerator.controller;

import com.hadymic.sqlgenerator.model.ad.AdJsonRootBean;
import com.hadymic.sqlgenerator.model.splash.SplashJsonRootBean;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("save")
public class AdJson2SqlController {
    @Autowired
    private IAdJson2SqlService adJson2SqlService;

    private static int adNum = 0;
    private static int splashNum = 0;

    @PostMapping("ad")
    public String ad(@RequestBody AdJsonRootBean root) {
        log.info(++adNum + " : " + "AdJsonBean: " + root.toString());
        boolean b = adJson2SqlService.saveAd(root);
        return b ? "success" : "fail";
    }

    @PostMapping("splash")
    public String splash(@RequestBody SplashJsonRootBean root) {
        log.info(++splashNum + " : " + "SplashJsonBean: " + root.toString());
        boolean b = adJson2SqlService.saveSplash(root);
        return b ? "success" : "fail";
    }
}
