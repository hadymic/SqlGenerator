/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model.splash;

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
@Table(name = "splash")
@TableName("splash")
public class Splash {

    @IsKey
    @Column
    @TableId
    private Long id;
    @Column
    @TableField
    private Long splash_id;
    @Column
    @TableField
    private String item_key;
    @Column
    @TableField
    private String report_key;
    @Column
    @TableField
    private String display_density;
    @Column
    @TableField
    private String type;
    @TableField(exist = false)
    private SplashLogExtra log_extra;
    @Column
    @TableField
    private Integer log_extra_id;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String open_url;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String web_url;
    @Column
    @TableField
    private String web_title;
    @TableField(exist = false)
    private SplashImageInfo image_info;
    @Column
    @TableField
    private Integer image_info_id;
    @TableField(exist = false)
    private SplashVideoInfo video_info;
    @Column
    @TableField
    private String video_info_id;
    @TableField(exist = false)
    private List<String> track_url_list;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String track_urls;
    @TableField(exist = false)
    private List<String> click_track_url_list;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String click_track_urls;
    @Column
    @TableField
    private String splash_ad_id;
    @Column
    @TableField
    private Integer skip_btn;
    @Column
    @TableField
    private Integer skip_btn_style;
    @Column
    @TableField
    private Integer click_btn;
    @Column
    @TableField
    private Integer image_mode;
    @Column
    @TableField
    private Integer splash_type;
    @Column
    @TableField
    private Integer banner_mode;
    @Column
    @TableField("`repeat`")
    private Integer repeat;
    @Column
    @TableField
    private Long display_after;
    @Column
    @TableField
    private Integer display_time_ms;
    @Column
    @TableField
    private Long expire_seconds;
    @Column
    @TableField
    private Long expire_timestamp;
    @Column
    @TableField
    private Integer splash_show_type;
    @Column
    @TableField
    private Integer predownload;
    @Column
    @TableField
    private Integer splash_load_type;
    @Column
    @TableField
    private Integer forbid_jump;
    @Column
    @TableField
    private Integer intercept_flag;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String url;
    @Column
    @TableField
    private String title;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String track_url;
    @Column
    @TableField
    private String action;
    @Column
    @TableField
    private Integer max_display_time_ms;
    @Column
    @TableField
    private Integer display_time;
    @Column
    @TableField
    private String label_info;
    @TableField(exist = false)
    private SplashSkipInfo skip_info;
    @Column
    @TableField
    private Integer skip_info_id;
    @Column
    @TableField
    private String predownload_text;
    @Column
    @TableField
    private Integer sound_control;
    @Column
    @TableField
    private Integer show_sound_time;
    @Column
    @TableField
    private String mp_url;
    @TableField(exist = false)
    private SplashShareInfo share_info;
    @Column
    @TableField
    private Integer share_info_id;

}