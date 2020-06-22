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
public class Ad_Data {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId
    private Integer id;
    @Column
    @TableField
    private Integer interaction_type;
    @Column
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
    //TODO
    @Column
    @TableField
    private String playable_style;
    @Column
    @TableField
    private Integer playable_style_id;
    @Column
    @TableField
    private Integer play_bar_style;
    @Column
    @TableField
    private Integer if_block_lp;

    private Icon icon;
    @Column
    @TableField
    private Integer icon_id;

    private Click_area click_area;
    @Column
    @TableField
    private Integer click_area_id;

    private Adslot adslot;
    @Column
    @TableField
    private Integer adslot_id;

    private List<Image> image;
    @Column
    @TableField
    private String image_ids;

    private List<String> show_url;
    @Column
    @TableField
    private String show_url_list;

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

    private Ext ext;
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

    private App app;
    @Column
    @TableField
    private Integer app_id;

    private List<Filter_word> filter_words;
    @Column
    @TableField
    private String filter_words_ids;
    @Column
    @TableField
    private Integer count_down;
    @Column
    @TableField
    private Integer expiration_time;

    private Download_conf download_conf;
    @Column
    @TableField
    private Integer download_conf_id;

    private Media_ext media_ext;
    @Column
    @TableField
    private Integer media_ext_id;

    private Tpl_info tpl_info;
    @Column
    @TableField
    private String tpl_info_id;
    @Column
    @TableField
    private String market_url;

    private Deep_link deep_link;
    @Column
    @TableField
    private Integer deep_link_id;

    private Session_param session_params;
    @Column
    @TableField
    private Integer session_params_id;

    private Video video;
    @Column
    @TableField
    private Integer video_id;
}