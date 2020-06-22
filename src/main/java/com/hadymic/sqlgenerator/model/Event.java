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
@Table(name = "ad_event")
@TableName("ad_event")
public class Event {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column
    @TableField
    private String abi;
    @Column
    @TableField
    private String android_id;
    @Column
    @TableField
    private Long app_id;
    @Column
    @TableField
    private String app_package_name;
    @Column
    @TableField
    private Integer app_version_code;
    @Column
    @TableField
    private String app_version_name;
    @Column
    @TableField
    private String brand;
    @Column
    @TableField
    private String carrier;
    @Column
    @TableField
    private String country;
    @Column
    @TableField
    private String density;
    @Column
    @TableField
    private String dev_id;
    @Column
    @TableField
    private Integer event_id;
    @Column
    @TableField
    private String gaid;
    @Column
    @TableField
    private String host;
    @Column
    @TableField
    private String imei;
    @Column
    @TableField
    private String imei2;
    @Column
    @TableField
    private String imsi;
    @Column
    @TableField
    private String imsi2;
    @Column
    @TableField
    private String ip;
    @Column
    @TableField
    private String mac;
    @Column
    @TableField
    private String model;
    @Column
    @TableField
    private String network_state;
    @Column
    @TableField
    private Integer os_version_code;
    @Column
    @TableField
    private String os_version_name;
    @Column
    @TableField
    private String plmn;
    @Column
    @TableField
    private String plmn2;
    @Column
    @TableField
    private String referrer;
    @Column
    @TableField
    private String resolution;
    @Column
    @TableField
    private Integer sdk_version_code;
    @Column
    @TableField
    private String sdk_version_name;
    @Column
    @TableField
    private String source;
    @Column
    @TableField
    private String timezone;
    @Column
    @TableField
    private String ua;
    @Column
    @TableField
    private Integer user_property;
    @Column
    @TableField
    private String user_pseudo_id;

}