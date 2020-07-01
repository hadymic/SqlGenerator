package com.hadymic.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hadymic.sqlgenerator.mapper.AdInfoMapper;
import com.hadymic.sqlgenerator.model.AdInfo;
import com.hadymic.sqlgenerator.service.IAdInfoService;
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
public class AdInfoServiceImpl extends ServiceImpl<AdInfoMapper, AdInfo> implements IAdInfoService {

}
