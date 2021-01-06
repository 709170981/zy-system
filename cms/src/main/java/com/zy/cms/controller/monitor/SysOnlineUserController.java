package com.zy.cms.controller.monitor;

import com.zy.common.annotion.BusinessLog;
import com.zy.common.annotion.Permission;
import com.zy.common.enums.LogAnnotionOpTypeEnum;
import com.zy.common.models.response.ResponseData;
import com.zy.common.models.response.SuccessResponseData;
import com.zy.common.param.monitor.SysOnlineUserParam;
import com.zy.common.service.monitor.SysOnlineUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 在线用户控制器
 */
@RestController
public class SysOnlineUserController {

    @Resource
    private SysOnlineUserService sysOnlineUserService;

    /**
     * 在线用户列表
     *
     * @author xuyuxiang
     * @date 2020/4/7 16:58
     */
    @Permission
    @GetMapping("/sysOnlineUser/list")
    @BusinessLog(title = "系统在线用户_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData list(SysOnlineUserParam sysOnlineUserParam) {
        return new SuccessResponseData(sysOnlineUserService.list(sysOnlineUserParam));
    }

    /**
     * 在线用户强退
     *
     * @author xuyuxiang
     * @date 2020/4/7 17:36
     */
    @Permission
    @PostMapping("/sysOnlineUser/forceExist")
    @BusinessLog(title = "系统在线用户_强退", opType = LogAnnotionOpTypeEnum.FORCE)
    public ResponseData forceExist(@RequestBody @Validated(SysOnlineUserParam.force.class) SysOnlineUserParam sysOnlineUserParam) {
        sysOnlineUserService.forceExist(sysOnlineUserParam);
        return new SuccessResponseData();
    }
}
