/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model.ad;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Auto-generated: 2020-06-19 10:18:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_diff_data")
@TableName("ad_diff_data")
public class AdDiffData {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(exist = false)
    private AdDiffData diff_data;
    @Column
    @TableField
    private Integer diff_data_id;
    @Column
    @TableField
    private String tag_diff;
    @TableField(exist = false)
    private AdValues values;
    @Column
    @TableField
    private Integer values_id;
    @TableField(exist = false)
    private List<AdDiffDataChildren> children;
    @Column
    @TableField
    private String children_ids;

}