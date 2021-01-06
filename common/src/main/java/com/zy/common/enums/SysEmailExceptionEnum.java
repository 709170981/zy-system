package com.zy.common.enums;

import com.zy.common.annotion.ExpEnumType;
import com.zy.common.consts.SysExpEnumConstant;
import com.zy.common.exception.enums.abs.AbstractBaseExceptionEnum;
import com.zy.common.factory.ExpEnumCodeFactory;

/**
 * 系统应用相关异常枚举
 */
@ExpEnumType(module = SysExpEnumConstant.GUNS_SYS_MODULE_EXP_CODE, kind = SysExpEnumConstant.EMAIL_EXCEPTION_ENUM)
public enum SysEmailExceptionEnum implements AbstractBaseExceptionEnum {

    /**
     * 收件人为空
     */
    EMAIL_TO_EMPTY(1, "收件人为空，请检查to参数"),

    /**
     * 标题为空
     */
    EMAIL_TITLE_EMPTY(2, "标题为空，请检查title参数"),

    /**
     * 内容为空
     */
    EMAIL_CONTENT_EMPTY(3, "内容为空，请检查content参数"),

    /**
     * 邮件发送失败
     */
    EMAIL_SEND_ERROR(4, "邮件发送失败，请检查发送参数");

    private final Integer code;

    private final String message;

    SysEmailExceptionEnum(Integer code, String message) {
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
