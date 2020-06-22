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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_session_param")
@TableName("ad_session_param")
public class Session_param extends BaseModel {
    @IsKey
    @IsAutoIncrement
    @Column
    @TableId
    private Integer id;
    @Column
    @TableField
    private Integer ad_count_require;
    @Column
    @TableField
    private String ext;
    @Column
    @TableField
    private Long parent_aid;
    @Column
    @TableField
    private Long parent_cid;
    @Column
    @TableField
    private String parent_request_id;
    @Column
    @TableField
    private Integer parent_type;
}
