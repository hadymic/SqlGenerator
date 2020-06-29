/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model.splash;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Auto-generated: 2020-06-29 10:59:12
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "splash_log_extra")
@TableName("splash_log_extra")
public class SplashLogExtra {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column
    @TableField
    private Integer rit;
    @Column
    @TableField
    private String req_id;
    @Column
    @TableField
    private Boolean is_no_ad;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 2040)
    @TableField
    private String vid;
    @Column
    @TableField
    private String an;
    @Column
    @TableField
    private String splash_api;
    @Column
    @TableField
    private String splash_type;
    @Column
    @TableField
    private Long ad_id;
    @Column
    @TableField
    private Integer city_id;
    @Column
    @TableField
    private Integer city_id_brand;
    @Column
    @TableField
    private Integer display_after;
    @Column
    @TableField
    private Integer dsp_id;
    @Column
    @TableField
    private Long dsp_local_ad_id;
    @Column
    @TableField
    private Integer expire_seconds;
    @Column
    @TableField
    private Long expire_timestamp;
    @Column
    @TableField
    private Integer first_splash_show;
    @Column
    @TableField
    private Boolean is_preview;
    @Column
    @TableField("`rank`")
    private Integer rank;
    @Column
    @TableField
    private String splash_send;
    @Column
    @TableField
    private Integer style_id;
    @TableField(exist = false)
    private List<Integer> style_ids;
    @Column
    @TableField
    private String style_ids_list;

}