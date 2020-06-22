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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_animation")
@TableName("ad_animation")
public class Animation extends BaseModel {
    @IsKey
    @IsAutoIncrement
    @Column
    @TableId
    private Integer id;
    @Column
    @TableField
    private String animationType;
    @Column
    @TableField
    private Double animationDuration;
    @Column
    @TableField
    private Integer animationTranslateX;
    @Column
    @TableField
    private Integer animationTranslateY;
    @Column
    @TableField
    private String animationTimeFunction;
    @Column
    @TableField
    private Integer animationDelay;
    @Column
    @TableField
    private Integer animationIterationCount;
    @Column
    @TableField
    private String animationDirection;
    @Column
    @TableField
    private Integer animationInterval;
    @Column
    @TableField
    private Long key;
    @Column
    @TableField
    private String animationRippleBackgroundColor;
    @Column
    @TableField
    private Double animationScaleX;
    @Column
    @TableField
    private Double animationScaleY;
}
