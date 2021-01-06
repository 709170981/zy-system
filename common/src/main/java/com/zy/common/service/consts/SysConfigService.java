package com.zy.common.service.consts;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.consts.SysConfig;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.consts.SysConfigParam;

import java.util.List;

/**
 * 系统参数配置service接口
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 查询系统参数配置
     *
     * @param sysConfigParam 查询参数
     * @return 查询分页结果
     */
    PageResult<SysConfig> page(SysConfigParam sysConfigParam);

    /**
     * 查询系统参数配置
     *
     * @param sysConfigParam 查询参数
     * @return 系统参数配置列表
     */
    List<SysConfig> list(SysConfigParam sysConfigParam);

    /**
     * 查看系统参数配置
     *
     * @param sysConfigParam 查看参数
     * @return 系统参数配置
     */
    SysConfig detail(SysConfigParam sysConfigParam);

    /**
     * 添加系统参数配置
     *
     * @param sysConfigParam 添加参数
     */
    void add(SysConfigParam sysConfigParam);

    /**
     * 删除系统参数配置
     *
     * @param sysConfigParam 删除参数
     */
    void delete(SysConfigParam sysConfigParam);

    /**
     * 编辑系统参数配置
     *
     * @param sysConfigParam 编辑参数
     */
    void edit(SysConfigParam sysConfigParam);

}
