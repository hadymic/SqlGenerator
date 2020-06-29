package com.hadymic.sqlgenerator.model.ad;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
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
public class AdTplInfoData {
    @IsKey
    @Column
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @Column
    @TableField
    private String type;
    @TableField(exist = false)
    private AdValues values;
    @Column
    @TableField
    private Integer values_id;
    @TableField(exist = false)
    private List<AdTplInfoData> children;
    @Column
    @TableField
    private String children_ids;
}
