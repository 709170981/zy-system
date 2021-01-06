package com.zy.common.service.sms;

import com.zy.common.enums.sms.SmsSendStatusEnum;
import com.zy.common.enums.sms.SmsVerifyEnum;
import com.zy.common.param.sms.SysSmsSendParam;
import com.zy.common.param.sms.SysSmsVerifyParam;

/**
 * 短信通知接口
 */
public interface SmsSenderService {

    /**
     * 发送短信
     *
     * @param sysSmsSendParam 短信发送参数
     * @return 是否成功
     */
    boolean sendShortMessage(SysSmsSendParam sysSmsSendParam);

    /**
     * 验证短信
     *
     * @param sysSmsVerifyParam 短信验证参数
     * @return 校验结果
     */
    SmsVerifyEnum verifyShortMessage(SysSmsVerifyParam sysSmsVerifyParam);

    /**
     * 查看短信发送状态 0=未发送，1=发送成功，2=发送失败
     *
     * @param smsId 短信发送记录id
     * @return 发送状态
     */
    SmsSendStatusEnum getMessageSendStatus(Integer smsId);

}
