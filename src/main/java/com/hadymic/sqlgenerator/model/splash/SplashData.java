/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model.splash;

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
public class SplashData {

    private List<Long> penalty_period;
    private Integer splash_interval;
    private Integer leave_interval;
    private Integer show_limit;
    private Boolean is_need_ack;
    private Integer splash_load_interval;
    private List<Splash> splash;
    private List<String> show_queue;
    private SplashLogExtra log_extra;
    private Integer concurrent_downloads;
    private Long server_time;
    private String period_first_map;

}