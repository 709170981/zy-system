package com.zy.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.zy.common.annotion.BusinessLog;
import com.zy.common.entity.log.SysOpLog;
import com.zy.common.entity.log.SysVisLog;
import com.zy.common.exception.ServiceException;
import com.zy.common.exception.enums.ServerExceptionEnum;
import com.zy.common.factory.LogFactory;
import com.zy.common.factory.LogTaskFactory;
import com.zy.common.utils.HttpServletUtil;
import com.zy.common.utils.IpAddressUtil;
import com.zy.common.utils.UaUtil;
import org.aspectj.lang.JoinPoint;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import javax.servlet.http.HttpServletRequest;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 日志管理器
 */
public class LogManager {

    /**
     * 异步操作记录日志的线程池
     */
    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(10, new ScheduledExecutorFactoryBean());

    private LogManager() {
    }

    private static final LogManager LOG_MANAGER = new LogManager();

    public static LogManager me() {
        return LOG_MANAGER;
    }

    /**
     * 异步执行日志的方法
     */
    private void executeLog(TimerTask task) {

        //如果演示模式开启，则不记录日志
        if (ConstantContextHolder.getDemoEnvFlag()) {
            return;
        }

        //日志记录操作延时
        int operateDelayTime = 10;
        EXECUTOR.schedule(task, operateDelayTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 登录日志
     */
    public void executeLoginLog(final String account, final String success, final String failMessage) {
        SysVisLog sysVisLog = this.genBaseSysVisLog();
        TimerTask timerTask = LogTaskFactory.loginLog(sysVisLog, account,
                success,
                failMessage);
        executeLog(timerTask);
    }

    /**
     * 登出日志
     */
    public void executeExitLog(final String account) {
        SysVisLog sysVisLog = this.genBaseSysVisLog();
        TimerTask timerTask = LogTaskFactory.exitLog(sysVisLog, account);
        executeLog(timerTask);
    }

    /**
     * 操作日志
     */
    public void executeOperationLog(BusinessLog businessLog, final String account, JoinPoint joinPoint, final String result) {
        SysOpLog sysOpLog = this.genBaseSysOpLog();
        TimerTask timerTask = LogTaskFactory.operationLog(sysOpLog, account, businessLog, joinPoint, result);
        executeLog(timerTask);
    }

    /**
     * 异常日志
     */
    public void executeExceptionLog(BusinessLog businessLog, final String account, JoinPoint joinPoint, Exception exception) {
        SysOpLog sysOpLog = this.genBaseSysOpLog();
        TimerTask timerTask = LogTaskFactory.exceptionLog(sysOpLog, account, businessLog, joinPoint, exception);
        executeLog(timerTask);
    }

    /**
     * 构建基础访问日志
     */
    private SysVisLog genBaseSysVisLog() {
        HttpServletRequest request = HttpServletUtil.getRequest();
        if (ObjectUtil.isNotNull(request)) {
            String ip = IpAddressUtil.getIp(request);
            String address = IpAddressUtil.getAddress(request);
            String browser = UaUtil.getBrowser(request);
            String os = UaUtil.getOs(request);
            return LogFactory.genBaseSysVisLog(ip, address, browser, os);
        } else {
            throw new ServiceException(ServerExceptionEnum.REQUEST_EMPTY);
        }
    }

    /**
     * 构建基础操作日志
     */
    private SysOpLog genBaseSysOpLog() {
        HttpServletRequest request = HttpServletUtil.getRequest();
        if (ObjectUtil.isNotNull(request)) {
            String ip = IpAddressUtil.getIp(request);
            String address = IpAddressUtil.getAddress(request);
            String browser = UaUtil.getBrowser(request);
            String os = UaUtil.getOs(request);
            String url = request.getRequestURI();
            String method = request.getMethod();
            return LogFactory.genBaseSysOpLog(ip, address, browser, os, url, method);
        } else {
            throw new ServiceException(ServerExceptionEnum.REQUEST_EMPTY);
        }
    }

}
