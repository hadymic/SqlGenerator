/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model;

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

/**
 * Auto-generated: 2020-06-19 10:9:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_tpl_info")
@TableName("ad_tpl_info")
public class AdTplInfo {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer tpl_id;
    @Column
    @TableField
    private String id;
    @Column
    @TableField
    private String md5;
    @Column
    @TableField
    private String url;
    @TableField(exist = false)
    private AdTplInfoData data;
    @Column
    @TableField
    private String tpl_info_data_id;
    @TableField(exist = false)
    private AdDiffData diff_data;
    @Column
    @TableField
    private Integer diff_data_id;
    @Column
    @TableField
    private String version;
    @TableField(exist = false)
    private AdDynamicCreative dynamic_creative;
    @Column
    @TableField
    private Integer dynamic_creative_id;

}