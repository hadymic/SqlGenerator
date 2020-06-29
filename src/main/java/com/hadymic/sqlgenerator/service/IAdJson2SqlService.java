package com.hadymic.sqlgenerator.service;

import com.hadymic.sqlgenerator.model.ad.AdJsonRootBean;
import com.hadymic.sqlgenerator.model.splash.SplashJsonRootBean;

public interface IAdJson2SqlService {
    boolean saveSplash(SplashJsonRootBean root);

    boolean saveAd(AdJsonRootBean root);
}
