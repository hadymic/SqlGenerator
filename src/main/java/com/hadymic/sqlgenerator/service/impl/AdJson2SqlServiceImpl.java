package com.hadymic.sqlgenerator.service.impl;

import com.hadymic.sqlgenerator.mapper.AdEventMapper;
import com.hadymic.sqlgenerator.model.JsonRootBean;
import com.hadymic.sqlgenerator.service.IAdDataService;
import com.hadymic.sqlgenerator.service.IAdEventService;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import com.hadymic.sqlgenerator.service.IAdJsonrootbeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdJson2SqlServiceImpl implements IAdJson2SqlService {
    @Autowired
    private IAdJsonrootbeanService adJsonrootbeanService;
    @Autowired
    private IAdDataService adDataService;
    @Autowired
    private IAdEventService adEventService;

    @Autowired
    private AdEventMapper adEventMapper;

    @Override
    public boolean save(JsonRootBean root) {
        System.out.println(root.getEvent());
        int insert = adEventMapper.insert(root.getEvent());
        return insert >= 1;
    }
}
