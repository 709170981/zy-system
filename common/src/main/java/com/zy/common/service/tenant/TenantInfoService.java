package com.zy.common.service.tenant;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.tenant.TenantInfo;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.tenant.TenantInfoParam;

/**
 * 租户表 服务类
 */
public interface TenantInfoService extends IService<TenantInfo> {

    /**
     * 新增租户
     *
     * @param param 添加参数
     */
    void add(TenantInfoParam param);

    /**
     * 删除租户
     *
     * @param param 删除参数
     */
    void delete(TenantInfoParam param);

    /**
     * 更新租户
     *
     * @param param 更新参数
     */
    void update(TenantInfoParam param);

    /**
     * 分页查询租户列表
     *
     * @param param 查询参数
     * @return 查询结果
     */
    PageResult<TenantInfo> page(TenantInfoParam param);

    /**
     * 获取租户信息，通过租户编码
     *
     * @param code 租户编码
     * @return 租户信息
     */
    TenantInfo getByCode(String code);

}
