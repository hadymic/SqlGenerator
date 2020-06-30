/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model;

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
@Table(name = "ad_data")
@TableName("ad_data")
public class AdData {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column
    @TableField
    private Integer interaction_type;
    @Column(length = 1020)
    @TableField
    private String target_url;
    @Column
    @TableField
    private String ad_id;
    @Column
    @TableField
    private String source;
    @Column
    @TableField
    private Boolean screenshot;
    @Column
    @TableField
    private Integer dislike_control;
    @Column
    @TableField
    private Integer play_bar_show_time;
    @Column
    @TableField
    private Boolean is_playable;
    @Column
    @TableField
    private Integer playable_type;
    @TableField(exist = false)
    private AdPlayableStyle playable_style;
    @Column
    @TableField
    private Integer playable_style_id;
    @Column
    @TableField
    private Integer play_bar_style;
    @Column
    @TableField
    private Integer if_block_lp;
    @TableField(exist = false)
    private AdIcon icon;
    @Column
    @TableField
    private Integer icon_id;
    @TableField(exist = false)
    private AdClickArea click_area;
    @Column
    @TableField
    private Integer click_area_id;
    @TableField(exist = false)
    private AdAdslot adslot;
    @Column
    @TableField
    private Integer adslot_id;
    @TableField(exist = false)
    private List<AdImage> image;
    @Column
    @TableField("image_ids")
    private String image_ids;
    @TableField(exist = false)
    private List<String> show_url;
    @Column(length = 1020)
    @TableField
    private String show_url_list;
    @TableField(exist = false)
    private List<String> click_url;
    @Column
    @TableField
    private String click_url_list;
    @Column
    @TableField
    private String phone_num;
    @Column
    @TableField
    private String title;
    @Column
    @TableField
    private String description;
    @TableField(exist = false)
    private AdExt ext;
    @Column
    @TableField
    private Integer ext_id;
    @Column
    @TableField
    private Integer image_mode;
    @Column
    @TableField
    private Integer intercept_flag;
    @Column
    @TableField
    private String button_text;
    @Column
    @TableField
    private Integer ad_logo;
    @Column
    @TableField
    private Integer video_adaptation;
    @Column
    @TableField
    private Integer feed_video_opentype;
    @TableField(exist = false)
    private AdApp app;
    @Column
    @TableField
    private Integer app_id;
    @TableField(exist = false)
    private List<AdFilterWord> filter_words;
    @Column
    @TableField
    private String filter_words_ids;
    @Column
    @TableField
    private Integer count_down;
    @Column
    @TableField
    private Integer expiration_time;
    @TableField(exist = false)
    private AdDownloadConf download_conf;
    @Column
    @TableField
    private Integer download_conf_id;
    @TableField(exist = false)
    private AdMediaExt media_ext;
    @Column
    @TableField
    private Integer media_ext_id;
    @TableField(exist = false)
    private AdTplInfo tpl_info;
    @Column
    @TableField
    private String tpl_info_id;
    @Column
    @TableField
    private String market_url;
    @TableField(exist = false)
    private AdDeepLink deep_link;
    @Column
    @TableField
    private Integer deep_link_id;
    @TableField(exist = false)
    private AdSessionParam session_params;
    @Column
    @TableField
    private Integer session_params_id;
    @TableField(exist = false)
    private AdVideo video;
    @Column
    @TableField
    private Integer video_id;
}