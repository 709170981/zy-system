package com.zy.common.service.monitor;


import com.zy.common.result.monitor.SysMachineResult;

/**
 * 系统属性监控service接口
 */
public interface SysMachineService {

    /**
     * 系统属性监控
     *
     * @return 系统属性结果集
     */
    SysMachineResult query();
}
