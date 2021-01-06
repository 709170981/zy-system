package com.zy.common.service.role.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.common.entity.role.SysRoleMenu;
import com.zy.common.param.role.SysRoleParam;
import com.zy.common.repository.role.SysRoleMenuMapper;
import com.zy.common.service.role.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统角色菜单service接口实现类
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public List<Long> getRoleMenuIdList(List<Long> roleIdList) {
        List<Long> menuIdList = CollectionUtil.newArrayList();

        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.in(SysRoleMenu::getRoleId, roleIdList);

        this.list(queryWrapper).forEach(sysRoleMenu -> menuIdList.add(sysRoleMenu.getMenuId()));

        return menuIdList;
    }

    @Override
    public void grantMenu(SysRoleParam sysRoleParam) {
        Long roleId = sysRoleParam.getId();
        //删除所拥有菜单
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenu::getRoleId, roleId);
        this.remove(queryWrapper);
        //授权菜单
        sysRoleParam.getGrantMenuIdList().forEach(menuId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            this.save(sysRoleMenu);
        });
    }

    @Override
    public void deleteRoleMenuListByMenuIdList(List<Long> menuIdList) {
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysRoleMenu::getMenuId, menuIdList);
        this.remove(queryWrapper);
    }

    @Override
    public void deleteRoleMenuListByRoleId(Long roleId) {
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenu::getRoleId, roleId);
        this.remove(queryWrapper);
    }
}
