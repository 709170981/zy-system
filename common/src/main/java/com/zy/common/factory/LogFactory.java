package com.zy.common.factory;

import cn.hutool.core.date.DateTime;
import com.zy.common.annotion.BusinessLog;
import com.zy.common.consts.SymbolConstant;
import com.zy.common.entity.log.SysOpLog;
import com.zy.common.entity.log.SysVisLog;
import com.zy.common.enums.LogSuccessStatusEnum;
import com.zy.common.enums.VisLogTypeEnum;
import com.zy.common.utils.JoinPointUtil;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * 日志对象创建工厂
 */
public class LogFactory {

    /**
     * 创建登录日志
     */
    static void createSysLoginLog(SysVisLog sysVisLog, String account, String successCode, String failMessage) {
        sysVisLog.setName(VisLogTypeEnum.LOGIN.getMessage());
        sysVisLog.setSuccess(successCode);

        sysVisLog.setVisType(VisLogTypeEnum.LOGIN.getCode());
        sysVisLog.setVisTime(DateTime.now());
        sysVisLog.setAccount(account);

        if (LogSuccessStatusEnum.SUCCESS.getCode().equals(successCode)) {
            sysVisLog.setMessage(VisLogTypeEnum.LOGIN.getMessage() + LogSuccessStatusEnum.SUCCESS.getMessage());
        }
        if (LogSuccessStatusEnum.FAIL.getCode().equals(successCode)) {
            sysVisLog.setMessage(VisLogTypeEnum.LOGIN.getMessage() +
                    LogSuccessStatusEnum.FAIL.getMessage() + SymbolConstant.COLON + failMessage);
        }
    }

    /**
     * 创建登出日志
     */
    static void createSysExitLog(SysVisLog sysVisLog, String account) {
        sysVisLog.setName(VisLogTypeEnum.EXIT.getMessage());
        sysVisLog.setSuccess(LogSuccessStatusEnum.SUCCESS.getCode());
        sysVisLog.setMessage(VisLogTypeEnum.EXIT.getMessage() + LogSuccessStatusEnum.SUCCESS.getMessage());
        sysVisLog.setVisType(VisLogTypeEnum.EXIT.getCode());
        sysVisLog.setVisTime(DateTime.now());
        sysVisLog.setAccount(account);
    }

    /**
     * 创建操作日志
     */
    static void createSysOperationLog(SysOpLog sysOpLog, String account, BusinessLog businessLog, JoinPoint joinPoint, String result) {
        fillCommonSysOpLog(sysOpLog, account, businessLog, joinPoint);
        sysOpLog.setSuccess(LogSuccessStatusEnum.SUCCESS.getCode());
        sysOpLog.setResult(result);
        sysOpLog.setMessage(LogSuccessStatusEnum.SUCCESS.getMessage());
    }

    /**
     * 创建异常日志
     */
    static void createSysExceptionLog(SysOpLog sysOpLog, String account, BusinessLog businessLog, JoinPoint joinPoint, Exception exception) {
        fillCommonSysOpLog(sysOpLog, account, businessLog, joinPoint);
        sysOpLog.setSuccess(LogSuccessStatusEnum.FAIL.getCode());
        sysOpLog.setMessage(Arrays.toString(exception.getStackTrace()));
    }

    /**
     * 生成通用操作日志字段
     */
    private static void fillCommonSysOpLog(SysOpLog sysOpLog, String account, BusinessLog businessLog, JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        String param = JoinPointUtil.getArgsJsonString(joinPoint);

        sysOpLog.setName(businessLog.title());
        sysOpLog.setOpType(businessLog.opType().ordinal());
        sysOpLog.setClassName(className);
        sysOpLog.setMethodName(methodName);
        sysOpLog.setParam(param);
        sysOpLog.setOpTime(DateTime.now());
        sysOpLog.setAccount(account);
    }

    /**
     * 构建基础访问日志
     */
    public static SysVisLog genBaseSysVisLog(String ip, String location, String browser, String os) {
        SysVisLog sysVisLog = new SysVisLog();
        sysVisLog.setIp(ip);
        sysVisLog.setLocation(location);
        sysVisLog.setBrowser(browser);
        sysVisLog.setOs(os);
        return sysVisLog;
    }

    /**
     * 构建基础操作日志
     */
    public static SysOpLog genBaseSysOpLog(String ip, String location, String browser, String os, String url, String method) {
        SysOpLog sysOpLog = new SysOpLog();
        sysOpLog.setIp(ip);
        sysOpLog.setLocation(location);
        sysOpLog.setBrowser(browser);
        sysOpLog.setOs(os);
        sysOpLog.setUrl(url);
        sysOpLog.setReqMethod(method);
        return sysOpLog;
    }

}
