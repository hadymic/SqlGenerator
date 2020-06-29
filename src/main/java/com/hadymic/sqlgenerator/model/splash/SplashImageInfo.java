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
@Table(name = "splash_image_info")
@TableName("splash_image_info")
public class SplashImageInfo {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column
    @TableField
    private String uri;
    @Column
    @TableField
    private Integer width;
    @Column
    @TableField
    private Integer height;
    @TableField(exist = false)
    private List<SplashUrlList> url_list;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String urls;
    @Column
    @TableField
    private String secret_key;

}