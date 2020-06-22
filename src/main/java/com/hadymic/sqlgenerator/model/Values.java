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

import java.util.List;

/**
 * Auto-generated: 2020-06-19 10:18:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_values")
@TableName("ad_values")
public class Values extends BaseModel {
    @IsKey
    @IsAutoIncrement
    @Column
    @TableId
    private Integer id;
    @Column
    @TableField
    private String adType;
    @Column
    @TableField
    private String bgColor;
    @Column
    @TableField
    private String clickArea;
    @Column
    @TableField
    private Integer height;
    @Column
    @TableField
    private String heightMode;
    @Column
    @TableField
    private Integer width;
    @Column
    @TableField
    private String widthMode;
    @Column
    @TableField
    private String justifyHorizontal;
    @Column
    @TableField
    private String justifyVertical;
    @Column
    @TableField
    private Integer paddingBottom;
    @Column
    @TableField
    private Integer paddingLeft;
    @Column
    @TableField
    private Integer paddingRight;
    @Column
    @TableField
    private Integer paddingTop;
    @Column
    @TableField
    private Integer borderSize;
    @Column
    @TableField
    private String borderColor;
    @Column
    @TableField
    private Integer borderRadius;
    @Column
    @TableField
    private Integer fontSize;
    @Column
    @TableField
    private String color;
    @Column
    @TableField
    private String data;
    @Column
    @TableField
    private Integer lineHeight;
    @Column
    @TableField
    private Integer marginTop;
    @Column
    @TableField
    private Integer marginRight;
    @Column
    @TableField
    private Integer marginBottom;
    @Column
    @TableField
    private Integer marginLeft;
    @Column
    @TableField
    private Integer timingStart;
    @Column
    @TableField
    private String textAlign;

    private List<Animation> animations;
    @Column
    @TableField
    private String animations_ids;
}