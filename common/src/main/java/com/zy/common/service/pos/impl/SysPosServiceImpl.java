package com.zy.common.service.pos.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.common.entity.pos.SysPos;
import com.zy.common.enums.CommonStatusEnum;
import com.zy.common.exception.ServiceException;
import com.zy.common.exception.enums.SysPosExceptionEnum;
import com.zy.common.factory.PageFactory;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.pos.SysPosParam;
import com.zy.common.repository.pos.SysPosMapper;
import com.zy.common.service.emp.SysEmpExtOrgPosService;
import com.zy.common.service.emp.SysEmpPosService;
import com.zy.common.service.pos.SysPosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统职位service接口实现类
 */
@Service
public class SysPosServiceImpl extends ServiceImpl<SysPosMapper, SysPos> implements SysPosService {

    @Resource
    private SysEmpPosService sysEmpPosService;

    @Resource
    private SysEmpExtOrgPosService sysEmpExtOrgPosService;

    @Override
    public PageResult<SysPos> page(SysPosParam sysPosParam) {
        LambdaQueryWrapper<SysPos> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotNull(sysPosParam)) {
            //根据职位名称模糊查询
            if (ObjectUtil.isNotEmpty(sysPosParam.getName())) {
                queryWrapper.like(SysPos::getName, sysPosParam.getName());
            }
            //根据职位编码模糊查询
            if (ObjectUtil.isNotEmpty(sysPosParam.getCode())) {
                queryWrapper.like(SysPos::getCode, sysPosParam.getCode());
            }
        }
        queryWrapper.eq(SysPos::getStatus, CommonStatusEnum.ENABLE.getCode());
        //根据排序升序排列，序号越小越在前
        queryWrapper.orderByAsc(SysPos::getSort);
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    @Override
    public List<SysPos> list(SysPosParam sysPosParam) {
        LambdaQueryWrapper<SysPos> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotNull(sysPosParam)) {
            //根据职位编码模糊查询
            if (ObjectUtil.isNotEmpty(sysPosParam.getCode())) {
                queryWrapper.eq(SysPos::getCode, sysPosParam.getCode());
            }
        }
        queryWrapper.eq(SysPos::getStatus, CommonStatusEnum.ENABLE.getCode());
        //根据排序升序排列，序号越小越在前
        queryWrapper.orderByAsc(SysPos::getSort);
        return this.list(queryWrapper);
    }

    @Override
    public void add(SysPosParam sysPosParam) {
        //校验参数，检查是否存在相同的名称和编码
        checkParam(sysPosParam, false);
        SysPos sysPos = new SysPos();
        BeanUtil.copyProperties(sysPosParam, sysPos);
        sysPos.setStatus(CommonStatusEnum.ENABLE.getCode());
        this.save(sysPos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(SysPosParam sysPosParam) {
        SysPos sysPos = this.querySysPos(sysPosParam);
        Long id = sysPos.getId();
        //该职位下是否有员工
        boolean hasPosEmp = sysEmpPosService.hasPosEmp(id);
        //只要还有，则不能删
        if (hasPosEmp) {
            throw new ServiceException(SysPosExceptionEnum.POS_CANNOT_DELETE);
        }
        //该附属职位下是否有员工
        boolean hasExtPosEmp = sysEmpExtOrgPosService.hasExtPosEmp(id);
        //只要还有，则不能删
        if (hasExtPosEmp) {
            throw new ServiceException(SysPosExceptionEnum.POS_CANNOT_DELETE);
        }
        sysPos.setStatus(CommonStatusEnum.DELETED.getCode());
        this.updateById(sysPos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SysPosParam sysPosParam) {
        SysPos sysPos = this.querySysPos(sysPosParam);
        //校验参数，检查是否存在相同的名称和编码
        checkParam(sysPosParam, true);
        BeanUtil.copyProperties(sysPosParam, sysPos);
        //不能修改状态，用修改状态接口修改状态
        sysPos.setStatus(null);
        this.updateById(sysPos);
    }

    @Override
    public SysPos detail(SysPosParam sysPosParam) {
        return this.querySysPos(sysPosParam);
    }

    /**
     * 校验参数，检查是否存在相同的名称和编码
     *
     * @author xuyuxiang
     * @date 2020/3/25 21:23
     */
    private void checkParam(SysPosParam sysPosParam, boolean isExcludeSelf) {
        Long id = sysPosParam.getId();
        String name = sysPosParam.getName();
        String code = sysPosParam.getCode();

        LambdaQueryWrapper<SysPos> queryWrapperByName = new LambdaQueryWrapper<>();
        queryWrapperByName.eq(SysPos::getName, name)
                .ne(SysPos::getStatus, CommonStatusEnum.DELETED.getCode());

        LambdaQueryWrapper<SysPos> queryWrapperByCode = new LambdaQueryWrapper<>();
        queryWrapperByCode.eq(SysPos::getCode, code)
                .ne(SysPos::getStatus, CommonStatusEnum.DELETED.getCode());

        if (isExcludeSelf) {
            queryWrapperByName.ne(SysPos::getId, id);
            queryWrapperByCode.ne(SysPos::getId, id);
        }

        int countByName = this.count(queryWrapperByName);
        int countByCode = this.count(queryWrapperByCode);

        if (countByName >= 1) {
            throw new ServiceException(SysPosExceptionEnum.POS_NAME_REPEAT);
        }
        if (countByCode >= 1) {
            throw new ServiceException(SysPosExceptionEnum.POS_CODE_REPEAT);
        }
    }

    /**
     * 获取系统职位
     *
     * @author xuyuxiang
     * @date 2020/3/26 9:56
     */
    private SysPos querySysPos(SysPosParam sysPosParam) {
        SysPos sysPos = this.getById(sysPosParam.getId());
        if (ObjectUtil.isNull(sysPos)) {
            throw new ServiceException(SysPosExceptionEnum.POS_NOT_EXIST);
        }
        return sysPos;
    }
}
