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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_deep_link")
@TableName("ad_deep_link")
public class AdDeepLink {
    @IsKey
    @IsAutoIncrement
    @Column
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column(length = 1020)
    @TableField
    private String deeplink_url;
    @Column
    @TableField
    private Integer fallback_type;
    @Column(length = 1020)
    @TableField
    private String fallback_url;
}
