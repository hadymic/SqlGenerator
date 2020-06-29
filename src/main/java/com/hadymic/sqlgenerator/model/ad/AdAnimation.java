package com.hadymic.sqlgenerator.model.ad;

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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_animation")
@TableName("ad_animation")
public class AdAnimation {
    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column
    @TableField
    private String animationType;
    @Column
    @TableField
    private Double animationDuration;
    @Column(name = "animation_translate_x")
    @TableField
    private Integer animationTranslateX;
    @Column(name = "animation_translate_y")
    @TableField
    private Integer animationTranslateY;
    @Column(name = "animation_time_function")
    @TableField
    private String animationTimeFunction;
    @Column
    @TableField
    private Integer animationDelay;
    @Column(name = "animation_iteration_count")
    @TableField
    private Integer animationIterationCount;
    @Column
    @TableField
    private String animationDirection;
    @Column
    @TableField
    private Integer animationInterval;
    @Column
    @TableField("`key`")
    private Long key;
    @Column(name = "animation_ripple_background_color")
    @TableField
    private String animationRippleBackgroundColor;
    @Column(name = "animation_scale_x")
    @TableField
    private Double animationScaleX;
    @Column(name = "animation_scale_y")
    @TableField
    private Double animationScaleY;
}
