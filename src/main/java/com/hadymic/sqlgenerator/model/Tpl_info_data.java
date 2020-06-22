package com.hadymic.sqlgenerator.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_tpl_info_data")
@TableName("ad_tpl_info_data")
public class Tpl_info_data {
    @IsKey
    @Column
    @TableId
    private String id;
    @Column
    @TableField
    private String type;

    private Values values;
    @Column
    @TableField
    private Integer values_id;

    private List<Tpl_info_data> children;
    @Column
    @TableField
    private String Tpl_info_data_ids;
}
