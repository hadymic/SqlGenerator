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
@Table(name = "ad_adslot")
@TableName("ad_adslot")
public class Adslot {

    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column
    @TableField
    private String mCodeId;
    @Column
    @TableField
    private Boolean mIsAutoPlay;
    @Column
    @TableField
    private Integer mImgAcceptedWidth;
    @Column
    @TableField
    private Integer mImgAcceptedHeight;
    @Column
    @TableField
    private Integer mExpressViewAcceptedWidth;
    @Column
    @TableField
    private Double mExpressViewAcceptedHeight;
    @Column
    @TableField
    private Integer mAdCount;
    @Column
    @TableField
    private Boolean mSupportDeepLink;
    @Column
    @TableField
    private Integer mRewardAmount;
    @Column
    @TableField
    private Integer mOrientation;
    @Column
    @TableField
    private Integer mNativeAdType;
    @Column
    @TableField
    private String mMediaExtra;
    @Column
    @TableField
    private String mRewardName;
    @Column
    @TableField
    private String mUserID;

}