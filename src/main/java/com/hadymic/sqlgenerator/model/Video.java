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

/**
 * Auto-generated: 2020-06-22 10:47:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_video")
@TableName("ad_video")
public class Video {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId
    private Integer id;
    @Column
    @TableField
    private Integer cover_height;
    @Column
    @TableField
    private String cover_url;
    @Column
    @TableField
    private Integer cover_width;
    @Column
    @TableField
    private String endcard;
    @Column
    @TableField
    private String file_hash;
    @Column
    @TableField
    private String resolution;
    @Column
    @TableField
    private Long size;
    @Column
    @TableField
    private Double video_duration;
    @Column
    @TableField
    private String video_url;
    @Column
    @TableField
    private String playable_download_url;
    @Column
    @TableField
    private Integer if_playable_loading_show;
    @Column
    @TableField
    private Integer remove_loading_page_type;

}