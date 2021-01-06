package com.zy.common.param.tenant;

import com.zy.common.param.BaseParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 租户表
 */
@Data
public class TenantInfoParam extends BaseParam {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @NotNull(message = "id不能为空，请检查id参数", groups = {edit.class, delete.class, detail.class})
    private Long id;

    /**
     * 租户名称
     */
    @NotBlank(message = "租户名称不能为空，请检查name参数", groups = {add.class, edit.class})
    private String name;

    /**
     * 租户的编码
     */
    @NotBlank(message = "租户的编码不能为空，请检查code参数", groups = {add.class, edit.class})
    private String code;

    /**
     * 租户管理员账号
     */
    @NotBlank(message = "租户管理员账号不能为空，请检查adminUsername参数", groups = {add.class})
    private String adminUsername;

    /**
     * 租户管理员密码
     */
    @NotBlank(message = "租户管理员账号不能为空，请检查adminPassword参数", groups = {add.class})
    private String adminPassword;

}
