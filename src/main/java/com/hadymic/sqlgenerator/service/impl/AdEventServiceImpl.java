package com.hadymic.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hadymic.sqlgenerator.mapper.AdEventMapper;
import com.hadymic.sqlgenerator.model.Event;
import com.hadymic.sqlgenerator.service.IAdEventService;
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
public class AdEventServiceImpl extends ServiceImpl<AdEventMapper, Event> implements IAdEventService {

}
