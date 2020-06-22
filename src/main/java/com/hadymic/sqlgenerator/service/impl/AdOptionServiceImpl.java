package com.hadymic.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hadymic.sqlgenerator.mapper.AdOptionMapper;
import com.hadymic.sqlgenerator.model.Option;
import com.hadymic.sqlgenerator.service.IAdOptionService;
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
public class AdOptionServiceImpl extends ServiceImpl<AdOptionMapper, Option> implements IAdOptionService {

}
