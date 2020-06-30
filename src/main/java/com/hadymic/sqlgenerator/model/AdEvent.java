/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model;

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
public class AdEvent {
    private String abi;
    private String android_id;
    private Long app_id;
    private String app_package_name;
    private Integer app_version_code;
    private String app_version_name;
    private String brand;
    private String carrier;
    private String country;
    private String density;
    private String dev_id;
    private Integer event_id;
    private String gaid;
    private String host;
    private String imei;
    private String imei2;
    private String imsi;
    private String imsi2;
    private String ip;
    private String mac;
    private String model;
    private String network_state;
    private Integer os_version_code;
    private String os_version_name;
    private String plmn;
    private String plmn2;
    private String referrer;
    private String resolution;
    private Integer sdk_version_code;
    private String sdk_version_name;
    private String source;
    private String timezone;
    private String ua;
    private Integer user_property;
    private String user_pseudo_id;
}