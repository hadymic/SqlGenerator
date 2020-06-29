/**
 * Copyright 2020 bejson.com
 */
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
@Table(name = "ad_click_area")
@TableName("ad_click_area")
public class AdClickArea {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column
    @TableField
    private Boolean click_upper_content_area;
    @Column
    @TableField
    private Boolean click_upper_non_content_area;
    @Column
    @TableField
    private Boolean click_lower_content_area;
    @Column
    @TableField
    private Boolean click_lower_non_content_area;
    @Column
    @TableField
    private Boolean click_button_area;
    @Column
    @TableField
    private Boolean click_video_area;

}