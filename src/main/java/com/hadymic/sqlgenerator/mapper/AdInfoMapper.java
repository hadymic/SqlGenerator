package com.hadymic.sqlgenerator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hadymic.sqlgenerator.model.AdInfo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-06-22
 */
public interface AdInfoMapper extends BaseMapper<AdInfo> {

    List<AdInfo> selectAll();
}
