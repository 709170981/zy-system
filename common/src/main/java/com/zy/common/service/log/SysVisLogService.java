package com.zy.common.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.log.SysVisLog;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.log.SysVisLogParam;

/**
 * 系统访问日志service接口
 */
public interface SysVisLogService extends IService<SysVisLog> {

    /**
     * 查询系统访问日志
     * @param sysVisLogParam 查询参数
     * @return 查询结果集合
     */
    PageResult<SysVisLog> page(SysVisLogParam sysVisLogParam);

    /**
     * 清空系统访问日志
     */
    void delete();
}
