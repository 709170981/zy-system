package com.zy.common.service.sms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.sms.SysSms;
import com.zy.common.enums.sms.SmsSendStatusEnum;
import com.zy.common.enums.sms.SmsVerifyEnum;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.sms.SysSmsInfoParam;
import com.zy.common.param.sms.SysSmsSendParam;
import com.zy.common.param.sms.SysSmsVerifyParam;

/**
 * 系统短信service接口
 */
public interface SysSmsInfoService extends IService<SysSms> {

    /**
     * 存储短信验证信息
     *
     * @param sysSmsSendParam 发送参数
     * @param validateCode    验证码
     * @return 短信记录id
     */
    Long saveSmsInfo(SysSmsSendParam sysSmsSendParam, String validateCode);

    /**
     * 更新短息发送状态
     *
     * @param smsId             短信记录id
     * @param smsSendStatusEnum 发送状态枚举
     */
    void updateSmsInfo(Long smsId, SmsSendStatusEnum smsSendStatusEnum);

    /**
     * 校验验证码是否正确
     *
     * @param sysSmsVerifyParam 短信校验参数
     * @return 短信校验结果枚举
     */
    SmsVerifyEnum validateSmsInfo(SysSmsVerifyParam sysSmsVerifyParam);

    /**
     * 短信发送记录查询
     *
     * @param sysSmsInfoParam 查询参数
     * @return 查询分页结果
     */
    PageResult<SysSms> page(SysSmsInfoParam sysSmsInfoParam);
}
