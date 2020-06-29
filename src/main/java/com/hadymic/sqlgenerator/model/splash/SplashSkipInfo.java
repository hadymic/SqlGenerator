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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "splash_skip_info")
@TableName("splash_skip_info")
public class SplashSkipInfo {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column
    @TableField
    private String countdown_unit;
    @Column
    @TableField
    private Integer height_extra_size;
    @Column
    @TableField
    private Integer width_extra_size;
    @Column
    @TableField
    private String text_color;
    @Column
    @TableField
    private String background_color;
    @Column
    @TableField
    private String text;
    @Column
    @TableField
    private Integer countdown_enable;
    @Column
    @TableField
    private Integer show_skip_seconds;

}