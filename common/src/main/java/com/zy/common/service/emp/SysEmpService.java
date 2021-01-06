package com.zy.common.service.emp;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.emp.SysEmp;
import com.zy.common.models.login.LoginEmpInfo;
import com.zy.common.param.emp.SysEmpParam;
import com.zy.common.result.emp.SysEmpInfo;

/**
 * 员工service接口
 */
public interface SysEmpService extends IService<SysEmp> {

    /**
     * 获取登录用户员工相关信息
     *
     * @param empId 员工id（用户id）
     * @return 登录用户员工相关信息
     */
    LoginEmpInfo getLoginEmpInfo(Long empId);

    /**
     * 获取用户员工相关信息
     *
     * @param empId 员工id（用户id）
     * @return 用户员工相关信息
     */
    SysEmpInfo getSysEmpInfo(Long empId);

    /**
     * 增加或编辑员工相关信息
     *
     * @param sysEmpParam 增加或编辑参数
     */
    void addOrUpdate(SysEmpParam sysEmpParam);

    /**
     * 修改员工相关机构信息
     *
     * @param orgId   机构id
     * @param orgName 机构名称
     */
    void updateEmpOrgInfo(Long orgId, String orgName);

    /**
     * 根据机构id判断该机构下是否有员工
     *
     * @param orgId 机构id
     * @return 该机构下是否有员工，true是，false否
     */
    boolean hasOrgEmp(Long orgId);

    /**
     * 根据员工id删除对应的员工表信息
     *
     * @param empId 员工id（用户id）
     */
    void deleteEmpInfoByUserId(Long empId);
}
