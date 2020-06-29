package com.hadymic.sqlgenerator.service.ad.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hadymic.sqlgenerator.mapper.ad.AdEventMapper;
import com.hadymic.sqlgenerator.model.ad.AdEvent;
import com.hadymic.sqlgenerator.service.ad.IAdEventService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-06-22
 */
@Service
public class AdEventServiceImpl extends ServiceImpl<AdEventMapper, AdEvent> implements IAdEventService {

}
