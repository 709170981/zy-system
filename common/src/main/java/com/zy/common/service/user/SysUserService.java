package com.zy.common.service.user;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.user.SysUser;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.user.SysUserParam;
import com.zy.common.result.user.SysUserResult;

import java.util.List;

/**
 * 系统用户service接口
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据账号获取用户
     *
     * @param account 账号
     * @return 用户
     * @author xuyuxiang
     * @date 2020/3/11 17:51
     */
    SysUser getUserByCount(String account);

    /**
     * 查询系统用户
     *
     * @param sysUserParam 查询参数
     * @return 查询分页结果
     * @author xuyuxiang
     * @date 2020/3/23 9:23
     */
    PageResult<SysUserResult> page(SysUserParam sysUserParam);

    /**
     * 根据用户账号模糊搜索系统用户列表
     *
     * @param sysUserParam 查询参数
     * @return 增强版hashMap 格式：[{"id:":123, "firstName":"张三"}]
     */
    List<Dict> list(SysUserParam sysUserParam);

    /**
     * 增加系统用户
     *
     * @param sysUserParam 添加参数
     */
    void add(SysUserParam sysUserParam);

    /**
     * 删除系统用户
     *
     * @param sysUserParam 删除参数
     */
    void delete(SysUserParam sysUserParam);

    /**
     * 编辑系统用户
     *
     * @param sysUserParam 编辑参数
     */
    void edit(SysUserParam sysUserParam);

    /**
     * 查看系统用户
     *
     * @param sysUserParam 查看参数
     * @return 用户结果集
     */
    SysUserResult detail(SysUserParam sysUserParam);

    /**
     * 修改状态
     *
     * @param sysUserParam 修改参数
     */
    void changeStatus(SysUserParam sysUserParam);

    /**
     * 授权角色
     *
     * @param sysUserParam 授权参数
     */
    void grantRole(SysUserParam sysUserParam);

    /**
     * 授权数据
     *
     * @param sysUserParam 授权参数
     */
    void grantData(SysUserParam sysUserParam);

    /**
     * 更新信息
     *
     * @param sysUserParam 更新参数
     */
    void updateInfo(SysUserParam sysUserParam);

    /**
     * 修改密码
     *
     * @param sysUserParam 修改密码参数
     */
    void updatePwd(SysUserParam sysUserParam);

    /**
     * 获取用户的数据范围（组织机构id集合）
     *
     * @param userId 用户id
     * @param orgId  组织机构id
     * @return 数据范围id集合（组织机构id集合）
     */
    List<Long> getUserDataScopeIdList(Long userId, Long orgId);

    /**
     * 根据用户id获取姓名
     *
     * @param userId 用户id
     * @return 用户姓名
     */
    String getNameByUserId(Long userId);

    /**
     * 拥有角色
     *
     * @param sysUserParam 查询参数
     * @return 角色id集合
     */
    List<Long> ownRole(SysUserParam sysUserParam);

    /**
     * 拥有数据
     *
     * @param sysUserParam 查询参数
     * @return 数据范围id集合
     */
    List<Long> ownData(SysUserParam sysUserParam);

    /**
     * 重置密码
     *
     * @param sysUserParam 重置参数
     */
    void resetPwd(SysUserParam sysUserParam);

    /**
     * 修改头像
     *
     * @param sysUserParam 修改头像参数
     */
    void updateAvatar(SysUserParam sysUserParam);

    /**
     * 导出用户
     *
     * @param sysUserParam 导出参数
     */
    void export(SysUserParam sysUserParam);

    /**
     * 用户选择器
     *
     * @param sysUserParam 查询参数
     * @return 用户列表集合，格式[{"id":123,"name":"张三"},{"id":456,"name":"李四"}]
     */
    List<Dict> selector(SysUserParam sysUserParam);
}
