package com.hadymic.sqlgenerator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hadymic.sqlgenerator.model.AdData;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-06-22
 */
public interface AdDataMapper extends BaseMapper<AdData> {

    AdData selectImageIdsByAdId(String ad_id);
}
