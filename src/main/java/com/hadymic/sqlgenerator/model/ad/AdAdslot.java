/**
 * Copyright 2020 bejson.com
 */
package com.hadymic.sqlgenerator.model.ad;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
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
@Table(name = "ad_adslot")
@TableName("ad_adslot")
public class AdAdslot {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Column(name = "m_code_id")
    @TableField
    @JsonProperty("mCodeId")
    private String mCodeId;

    @Column(name = "m_is_auto_play")
    @TableField
    @JsonProperty("mIsAutoPlay")
    private Boolean mIsAutoPlay;

    @Column(name = "m_img_accepted_width")
    @TableField
    @JsonProperty("mImgAcceptedWidth")
    private Integer mImgAcceptedWidth;

    @Column(name = "m_img_accepted_height")
    @TableField
    @JsonProperty("mImgAcceptedHeight")
    private Integer mImgAcceptedHeight;

    @Column(name = "m_express_view_accepted_width")
    @TableField
    @JsonProperty("mExpressViewAcceptedWidth")
    private Integer mExpressViewAcceptedWidth;

    @Column(name = "m_express_view_accepted_height")
    @TableField
    @JsonProperty("mExpressViewAcceptedHeight")
    private Double mExpressViewAcceptedHeight;

    @Column(name = "m_ad_count")
    @TableField
    @JsonProperty("mAdCount")
    private Integer mAdCount;

    @Column(name = "m_support_deep_link")
    @TableField
    @JsonProperty("mSupportDeepLink")
    private Boolean mSupportDeepLink;

    @Column(name = "m_reward_amount")
    @TableField
    @JsonProperty("mRewardAmount")
    private Integer mRewardAmount;

    @Column(name = "m_orientation")
    @TableField
    @JsonProperty("mOrientation")
    private Integer mOrientation;

    @Column(name = "m_native_ad_type")
    @TableField
    @JsonProperty("mNativeAdType")
    private Integer mNativeAdType;

    @Column(name = "m_media_extra")
    @TableField
    @JsonProperty("mMediaExtra")
    private String mMediaExtra;

    @Column(name = "m_reward_name")
    @TableField
    @JsonProperty("mRewardName")
    private String mRewardName;

    @Column(name = "m_user_id")
    @TableField
    @JsonProperty("mUserID")
    private String mUserId;

}