package com.zy.common.service.app;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.app.SysApp;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.app.SysAppParam;

import java.util.List;

/**
 * 系统应用service接口
 */
public interface SysAppService extends IService<SysApp> {

    /**
     * 获取用户应用相关信息
     *
     * @param userId 用户id
     * @return 用户拥有的应用信息，格式：[{"code":"system","name":"系统应用","active":true}]
     */
    List<Dict> getLoginApps(Long userId);

    /**
     * 查询系统应用
     *
     * @param sysAppParam 查询参数
     * @return 查询分页结果
     */
    PageResult<SysApp> page(SysAppParam sysAppParam);

    /**
     * 添加系统应用
     *
     * @param sysAppParam 添加参数
     */
    void add(SysAppParam sysAppParam);

    /**
     * 删除系统应用
     *
     * @param sysAppParam 删除参数
     */
    void delete(SysAppParam sysAppParam);

    /**
     * 编辑系统应用
     *
     * @param sysAppParam 编辑参数
     */
    void edit(SysAppParam sysAppParam);

    /**
     * 查看系统应用
     *
     * @param sysAppParam 查看参数
     * @return 系统应用
     */
    SysApp detail(SysAppParam sysAppParam);

    /**
     * 系统应用列表
     *
     * @param sysAppParam 查询参数
     * @return 系统应用列表
     */
    List<SysApp> list(SysAppParam sysAppParam);

    /**
     * 设为默认应用
     *
     * @param sysAppParam 设为默认应用参数
     */
    void setAsDefault(SysAppParam sysAppParam);
}
