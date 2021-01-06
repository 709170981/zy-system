package com.zy.common.factory;

import cn.hutool.core.util.ObjectUtil;
import com.zy.common.config.ConstantContextHolder;
import com.zy.common.entity.user.SysUser;
import com.zy.common.enums.AdminTypeEnum;
import com.zy.common.enums.CommonStatusEnum;
import com.zy.common.enums.SexEnum;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 填充用户附加信息工厂
 */
public class SysUserFactory {

    /**
     * 管理员类型（1超级管理员 2非管理员）
     * 新增普通用户时填充相关信息
     */
    public static void fillAddCommonUserInfo(SysUser sysUser) {
        fillBaseUserInfo(sysUser);
        sysUser.setAdminType(AdminTypeEnum.NONE.getCode());
    }

    /**
     * 填充用户基本字段
     */
    public static void fillBaseUserInfo(SysUser sysUser) {
        //密码为空则设置密码
        if (ObjectUtil.isEmpty(sysUser.getPassword())) {
            //没有密码则设置默认密码
            String password = ConstantContextHolder.getDefaultPassWord();
            //设置密码为Md5加密后的密码
            sysUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        }

        if (ObjectUtil.isEmpty(sysUser.getAvatar())) {
            sysUser.setAvatar(null);
        }

        if (ObjectUtil.isEmpty(sysUser.getSex())) {
            sysUser.setSex(SexEnum.NONE.getCode());
        }

        sysUser.setStatus(CommonStatusEnum.ENABLE.getCode());
    }
}
