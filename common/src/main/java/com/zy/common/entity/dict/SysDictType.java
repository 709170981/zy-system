package com.zy.common.entity.dict;

import com.baomidou.mybatisplus.annotation.*;
import com.zy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统字典类型表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_type")
public class SysDictType extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    /**
     * 状态（字典 0正常 1停用 2删除）
     */
    private Integer status;

}
