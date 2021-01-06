package com.zy.common.service.pos;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.pos.SysPos;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.pos.SysPosParam;

import java.util.List;

/**
 * 系统职位service接口
 */
public interface SysPosService extends IService<SysPos> {

    /**
     * 查询系统职位
     *
     * @param sysPosParam 查询参数
     * @return 查询分页结果
     */
    PageResult<SysPos> page(SysPosParam sysPosParam);

    /**
     * 系统职位列表
     *
     * @param sysPosParam 查询参数
     * @return 职位列表
     */
    List<SysPos> list(SysPosParam sysPosParam);

    /**
     * 添加系统职位
     *
     * @param sysPosParam 添加参数
     */
    void add(SysPosParam sysPosParam);

    /**
     * 删除系统职位
     *
     * @param sysPosParam 删除参数
     */
    void delete(SysPosParam sysPosParam);

    /**
     * 编辑系统职位
     *
     * @param sysPosParam 编辑参数
     */
    void edit(SysPosParam sysPosParam);

    /**
     * 查看系统职位
     *
     * @param sysPosParam 查看参数
     * @return 系统职位
     */
    SysPos detail(SysPosParam sysPosParam);
}
