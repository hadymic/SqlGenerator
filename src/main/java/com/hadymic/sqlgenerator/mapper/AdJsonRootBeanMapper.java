package com.hadymic.sqlgenerator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hadymic.sqlgenerator.model.AdInfo;
import com.hadymic.sqlgenerator.model.AdJsonRootBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-06-22
 */
public interface AdJsonRootBeanMapper extends BaseMapper<AdJsonRootBean> {

    List<AdInfo> selectAdInfo();
}
