package com.zy.common.service.org;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.org.SysOrg;
import com.zy.common.models.node.AntdBaseTreeNode;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.org.SysOrgParam;

import java.util.List;

/**
 * 系统组织机构service接口
 */
public interface SysOrgService extends IService<SysOrg> {

    /**
     * 查询系统机构
     *
     * @param sysOrgParam 查询参数
     * @return 查询分页结果
     */
    PageResult<SysOrg> page(SysOrgParam sysOrgParam);

    /**
     * 系统组织机构列表
     *
     * @param sysOrgParam 查询参数
     * @return 组织机构列表
     */
    List<SysOrg> list(SysOrgParam sysOrgParam);

    /**
     * 添加系统组织机构
     *
     * @param sysOrgParam 添加参数
     */
    void add(SysOrgParam sysOrgParam);

    /**
     * 删除系统组织机构
     *
     * @param sysOrgParam 删除参数
     */
    void delete(SysOrgParam sysOrgParam);

    /**
     * 编辑系统组织机构
     *
     * @param sysOrgParam 编辑参数
     */
    void edit(SysOrgParam sysOrgParam);

    /**
     * 查看系统组织机构
     *
     * @param sysOrgParam 查看参数
     * @return 组织机构
     */
    SysOrg detail(SysOrgParam sysOrgParam);

    /**
     * 获取系统组织机构树
     *
     * @param sysOrgParam 查询参数
     * @return 系统组织机构树
     */
    List<AntdBaseTreeNode> tree(SysOrgParam sysOrgParam);

    /**
     * 根据数据范围类型获取当前登录用户的数据范围id集合
     *
     * @param dataScopeType 数据范围类型（1全部数据 2本部门及以下数据 3本部门数据 4仅本人数据）
     * @param orgId         组织机构id
     * @return 数据范围id集合
     */
    List<Long> getDataScopeListByDataScopeType(Integer dataScopeType, Long orgId);
}
