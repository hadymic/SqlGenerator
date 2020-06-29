/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model.splash;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
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
@Table(name = "splash_video_info")
@TableName("splash_video_info")
public class SplashVideoInfo {

    @IsKey
    @Column
    @TableId
    private String video_id;
    @Column
    @TableField
    private Long video_group_id;
    @TableField(exist = false)
    private List<String> video_url_list;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String video_urls;
    @Column
    @TableField
    private String video_density;
    @TableField(exist = false)
    private List<String> play_track_url_list;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String play_track_urls;
    @TableField(exist = false)
    private List<String> playover_track_url_list;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String playover_track_urls;
    @TableField(exist = false)
    private List<String> action_track_url_list;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String action_track_urls;
    @Column
    @TableField
    private Boolean voice_switch;
    @Column
    @TableField
    private String secret_key;
    @Column
    @TableField
    private Integer video_duration_ms;

}