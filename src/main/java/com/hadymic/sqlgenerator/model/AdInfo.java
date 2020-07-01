package com.hadymic.sqlgenerator.model;

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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_info")
@TableName("ad_info")
public class AdInfo {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId
    private Integer id;
    @Column
    @TableField
    private Integer interaction_type;
    @Column
    @TableField("ad_id")
    private Long ad_id;
    @Column
    @TableField("creative_id")
    private Long creative_id;
    @Column
    @TableField("convert_id")
    private Long convert_id;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String target_url;
    @Column
    @TableField
    private String source;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 2040)
    @TableField
    private String show_url_list;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 2040)
    @TableField
    private String click_url_list;
    @Column
    @TableField
    private String title;
    @Column
    @TableField
    private String market_url;
    @Column
    @TableField
    private String app_name;
    @Column
    @TableField
    private String package_name;
    @Column
    @TableField
    private String download_url;
    @Column
    @TableField
    private String download_path;
    @Column
    @TableField
    private String icon_url;
    @Column
    @TableField
    private String icon_path;
    @Column
    @TableField(exist = false)
    private List<AdImage> images;
    @Column
    @TableField
    private String image_ids;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String deeplink_url;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1020)
    @TableField
    private String fallback_url;
    @Column
    @TableField
    private String video_cover_url;
    @Column(type = MySqlTypeConstant.VARCHAR, length = 1530)
    @TableField
    private String endcard;
    @Column
    @TableField
    private String file_hash;
    @Column
    @TableField
    private Double video_duration;
    @Column
    @TableField
    private String video_url;
    @Column
    @TableField
    private String video_path;
}
