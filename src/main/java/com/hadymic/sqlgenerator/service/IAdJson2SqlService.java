package com.hadymic.sqlgenerator.service;

import com.hadymic.sqlgenerator.model.AdJsonRootBean;

public interface IAdJson2SqlService {

    boolean saveAd(AdJsonRootBean root);
}
