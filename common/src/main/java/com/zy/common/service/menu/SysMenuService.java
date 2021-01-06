package com.zy.common.service.menu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.menu.SysMenu;
import com.zy.common.models.node.LoginMenuTreeNode;
import com.zy.common.models.node.MenuBaseTreeNode;
import com.zy.common.param.menu.SysMenuParam;

import java.util.List;

/**
 * 系统菜单service接口
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取用户权限相关信息
     *
     * @param userId 用户id
     * @return 权限集合
     */
    List<String> getLoginPermissions(Long userId);

    /**
     * 获取用户AntDesign菜单相关信息，前端使用
     *
     * @param userId  用户id
     * @param appCode 应用编码
     * @return AntDesign菜单信息结果集
     */
    List<LoginMenuTreeNode> getLoginMenusAntDesign(Long userId, String appCode);

    /**
     * 获取用户菜单所属的应用编码集合
     *
     * @param userId 用户id
     * @return 用户菜单所属的应用编码集合
     */
    List<String> getUserMenuAppCodeList(Long userId);

    /**
     * 系统菜单列表（树表）
     *
     * @param sysMenuParam 查询参数
     * @return 菜单树表列表
     */
    List<SysMenu> list(SysMenuParam sysMenuParam);

    /**
     * 添加系统菜单
     *
     * @param sysMenuParam 添加参数
     */
    void add(SysMenuParam sysMenuParam);

    /**
     * 删除系统菜单
     *
     * @param sysMenuParam 删除参数
     */
    void delete(SysMenuParam sysMenuParam);

    /**
     * 编辑系统菜单
     *
     * @param sysMenuParam 编辑参数
     */
    void edit(SysMenuParam sysMenuParam);

    /**
     * 查看系统菜单
     *
     * @param sysMenuParam 查看参数
     * @return 系统菜单
     */
    SysMenu detail(SysMenuParam sysMenuParam);

    /**
     * 获取系统菜单树，用于新增，编辑时选择上级节点
     *
     * @param sysMenuParam 查询参数
     * @return 菜单树列表
     */
    List<MenuBaseTreeNode> tree(SysMenuParam sysMenuParam);

    /**
     * 获取系统菜单树，用于给角色授权时选择
     *
     * @param sysMenuParam 查询参数
     * @return 菜单树列表
     */
    List<MenuBaseTreeNode> treeForGrant(SysMenuParam sysMenuParam);

    /**
     * 根据应用编码判断该机构下是否有状态为正常的菜单
     *
     * @param appCode 应用编码
     * @return 该应用下是否有正常菜单，true是，false否
     */
    boolean hasMenu(String appCode);
}
