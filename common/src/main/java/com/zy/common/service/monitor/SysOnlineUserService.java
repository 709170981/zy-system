package com.zy.common.service.monitor;

import com.zy.common.models.page.PageResult;
import com.zy.common.param.monitor.SysOnlineUserParam;
import com.zy.common.result.monitor.SysOnlineUserResult;

/**
 * 系统在线用户service接口
 */
public interface SysOnlineUserService {

    /**
     * 系统在线用户列表
     * @param sysOnlineUserParam 查询参数
     * @return 在线用户列表
     */
    PageResult<SysOnlineUserResult> list(SysOnlineUserParam sysOnlineUserParam);

    /**
     * 系统在线用户强退
     * @param sysOnlineUserParam 操作参数
     */
    void forceExist(SysOnlineUserParam sysOnlineUserParam);
}
