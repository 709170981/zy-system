package com.zy.common.annotion;

import com.zy.common.enums.LogAnnotionOpTypeEnum;

import java.lang.annotation.*;

/**
 * 标记需要做业务日志的方法
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BusinessLog {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String title() default "";

    /**
     * 业务操作类型枚举
     */
    LogAnnotionOpTypeEnum opType() default LogAnnotionOpTypeEnum.OTHER;
}
