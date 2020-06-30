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
public class AdJsonRootBean {
    private AdData data;
    private AdEvent event;
}