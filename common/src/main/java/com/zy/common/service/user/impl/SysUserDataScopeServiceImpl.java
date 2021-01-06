package com.zy.common.service.user.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.common.entity.user.SysUserDataScope;
import com.zy.common.param.user.SysUserParam;
import com.zy.common.repository.user.SysUserDataScopeMapper;
import com.zy.common.service.user.SysUserDataScopeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统用户数据范围service接口实现类
 */
@Service
public class SysUserDataScopeServiceImpl extends ServiceImpl<SysUserDataScopeMapper, SysUserDataScope>
        implements SysUserDataScopeService {

    @Override
    public void grantData(SysUserParam sysUserParam) {
        Long userId = sysUserParam.getId();
        //删除所拥有数据
        LambdaQueryWrapper<SysUserDataScope> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserDataScope::getUserId, userId);
        this.remove(queryWrapper);
        //授权数据
        sysUserParam.getGrantOrgIdList().forEach(orgId -> {
            SysUserDataScope sysUserDataScope = new SysUserDataScope();
            sysUserDataScope.setUserId(userId);
            sysUserDataScope.setOrgId(orgId);
            this.save(sysUserDataScope);
        });
    }

    @Override
    public List<Long> getUserDataScopeIdList(Long uerId) {
        List<Long> userDataScopeIdList = CollectionUtil.newArrayList();
        LambdaQueryWrapper<SysUserDataScope> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserDataScope::getUserId, uerId);
        this.list(queryWrapper).forEach(sysUserDataScope -> userDataScopeIdList.add(sysUserDataScope.getOrgId()));
        return userDataScopeIdList;
    }

    @Override
    public void deleteUserDataScopeListByOrgIdList(List<Long> orgIdList) {
        LambdaQueryWrapper<SysUserDataScope> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysUserDataScope::getOrgId, orgIdList);
        this.remove(queryWrapper);
    }

    @Override
    public void deleteUserDataScopeListByUserId(Long userId) {
        LambdaQueryWrapper<SysUserDataScope> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserDataScope::getUserId, userId);
        this.remove(queryWrapper);
    }
}
