package com.zy.common.repository.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.common.entity.role.SysRoleDataScope;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统角色范围mapper接口
 */
@Mapper
@Repository
public interface SysRoleDataScopeMapper extends BaseMapper<SysRoleDataScope> {
}
