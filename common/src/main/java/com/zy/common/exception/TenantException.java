package com.zy.common.exception;


import com.zy.common.exception.enums.abs.AbstractBaseExceptionEnum;

/**
 * 多租户的异常
 */
public class TenantException extends ServiceException {

    public TenantException(AbstractBaseExceptionEnum exception) {
        super(exception);
    }

}
