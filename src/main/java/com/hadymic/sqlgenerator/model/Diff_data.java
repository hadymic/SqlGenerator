/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
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
public class Diff_data extends BaseModel {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId
    private Integer id;

    private Diff_data diff_data;
    @Column
    @TableField
    private Integer diff_data_id;
    @Column
    @TableField
    private String tag_diff;

    private Values values;
    @Column
    @TableField
    private Integer values_id;

    private List<Tpl_info_data> children;
    @Column
    @TableField
    private String tpl_info_data_ids;

}