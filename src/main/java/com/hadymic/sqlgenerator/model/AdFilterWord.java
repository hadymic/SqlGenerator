/**
  * Copyright 2020 bejson.com 
  */
package com.hadymic.sqlgenerator.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
@Table(name = "ad_filter_word")
@TableName("ad_filter_word")
public class AdFilterWord {

    @IsKey
    @Column
    @TableId
    private String id;
    @Column
    @TableField
    private String name;
    @Column
    @TableField
    private Boolean is_selected;
    @TableField(exist = false)
    private List<AdFilterWord> options;
    @Column
    @TableField
    private String options_ids;

}