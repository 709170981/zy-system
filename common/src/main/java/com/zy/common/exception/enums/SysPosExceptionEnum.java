package com.zy.common.exception.enums;

import com.zy.common.annotion.ExpEnumType;
import com.zy.common.consts.SysExpEnumConstant;
import com.zy.common.exception.enums.abs.AbstractBaseExceptionEnum;
import com.zy.common.factory.ExpEnumCodeFactory;

/**
 * 系统职位相关异常枚举
 */
@ExpEnumType(module = SysExpEnumConstant.GUNS_SYS_MODULE_EXP_CODE, kind = SysExpEnumConstant.SYS_POS_EXCEPTION_ENUM)
public enum SysPosExceptionEnum implements AbstractBaseExceptionEnum {

    /**
     * 职位不存在
     */
    POS_NOT_EXIST(1, "职位不存在"),

    /**
     * 职位编码重复
     */
    POS_CODE_REPEAT(2, "职位编码重复，请检查code参数"),

    /**
     * 职位名称重复
     */
    POS_NAME_REPEAT(3, "职位名称重复，请检查name参数"),

    /**
     * 该职位下有员工
     */
    POS_CANNOT_DELETE(4, "该职位下有员工，无法删除");

    private final Integer code;

    private final String message;

    SysPosExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return ExpEnumCodeFactory.getExpEnumCode(this.getClass(), code);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
