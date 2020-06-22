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

/**
 * Auto-generated: 2020-06-19 9:22:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_ext")
@TableName("ad_ext")
public class Ext extends BaseModel {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId
    private Integer id;
    @Column
    @TableField
    private Integer ad_slot_type;
    @Column
    @TableField
    private String oaid;
    @Column
    @TableField
    private String language;
    @Column
    @TableField
    private String ug_creative_id;
    @Column
    @TableField
    private Long ad_id;
    @Column
    @TableField
    private Long creative_id;
    @Column
    @TableField
    private Long convert_id;
    @Column
    @TableField
    private Long uid;
    @Column
    @TableField
    private Integer ad_type;
    @Column
    @TableField
    private Integer pricing;
    @Column
    @TableField
    private Integer ut;
    @Column
    @TableField
    private String version_code;
    @Column
    @TableField
    private Long device_id;
    @Column
    @TableField
    private Integer width;
    @Column
    @TableField
    private Integer height;
    @Column
    @TableField
    private String mac;
    @Column
    @TableField
    private String uuid;
    @Column
    @TableField
    private String uuid_md5;
    @Column
    @TableField
    private String os;
    @Column
    @TableField
    private String client_ip;
    @Column
    @TableField
    private String open_udid;
    @Column
    @TableField
    private String os_type;
    @Column
    @TableField
    private String app_name;
    @Column
    @TableField
    private String device_type;
    @Column
    @TableField
    private String os_version;
    @Column
    @TableField
    private String app_id;
    @Column
    @TableField
    private Integer template_id;
    @Column
    @TableField
    private Integer template_rate;
    @Column
    @TableField
    private Integer promotion_type;
    @Column
    @TableField
    private Integer img_gen_type;
    @Column
    @TableField
    private String img_md5;
    @Column
    @TableField
    private Integer source_type;
    @Column
    @TableField
    private Double pack_time;
    @Column
    @TableField
    private Long cid;
    @Column
    @TableField
    private Integer interaction_type;
    @Column
    @TableField
    private String src_type;
    @Column
    @TableField
    private String package_name;
    @Column
    @TableField
    private Integer pos;
    @Column
    @TableField
    private Integer landing_type;
    @Column
    @TableField
    private Boolean is_sdk;
    @Column
    @TableField
    private Boolean is_dsp_ad;
    @Column
    @TableField
    private String imei;
    @Column
    @TableField
    private String req_id;
    @Column
    @TableField
    private Long rit;
    @Column
    @TableField
    private String vid;
    @Column
    @TableField
    private Long orit;
    @Column
    @TableField
    private String ad_price;
    @Column
    @TableField
    private String engine_external_url;
    @Column
    @TableField
    private String engine_web_url;
    @Column
    @TableField
    private String variation_id;
    @Column
    @TableField
    private String app_bundle_id;
    @Column
    @TableField
    private Long dynamic_tpl_id;
    @Column
    @TableField
    private Long dynamic_ptpl_id;
    @Column
    @TableField
    private Integer show_type;
}