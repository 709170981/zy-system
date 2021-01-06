package com.zy.cms.controller.log;

import com.zy.common.annotion.BusinessLog;
import com.zy.common.annotion.Permission;
import com.zy.common.enums.LogAnnotionOpTypeEnum;
import com.zy.common.models.response.ResponseData;
import com.zy.common.models.response.SuccessResponseData;
import com.zy.common.param.log.SysOpLogParam;
import com.zy.common.param.log.SysVisLogParam;
import com.zy.common.service.log.SysOpLogService;
import com.zy.common.service.log.SysVisLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统日志控制器
 */
@RestController
public class SysLogController {

    @Resource
    private SysVisLogService sysVisLogService;

    @Resource
    private SysOpLogService sysOpLogService;

    /**
     * 查询访问日志
     *
     * @author xuyuxiang
     * @date 2020/3/20 18:52
     */
    @Permission
    @GetMapping("/sysVisLog/page")
    public ResponseData visLogPage(SysVisLogParam visLogParam) {
        return new SuccessResponseData(sysVisLogService.page(visLogParam));
    }

    /**
     * 查询操作日志
     *
     * @author xuyuxiang
     * @date 2020/3/20 18:52
     */
    @Permission
    @GetMapping("/sysOpLog/page")
    public ResponseData opLogPage(SysOpLogParam sysOpLogParam) {
        return new SuccessResponseData(sysOpLogService.page(sysOpLogParam));
    }

    /**
     * 清空访问日志
     *
     * @author xuyuxiang
     * @date 2020/3/23 16:28
     */
    @Permission
    @PostMapping("/sysVisLog/delete")
    @BusinessLog(title = "访问日志_清空", opType = LogAnnotionOpTypeEnum.CLEAN)
    public ResponseData visLogDelete() {
        sysVisLogService.delete();
        return new SuccessResponseData();
    }

    /**
     * 清空操作日志
     *
     * @author xuyuxiang
     * @date 2020/3/23 16:28
     */
    @Permission
    @PostMapping("/sysOpLog/delete")
    @BusinessLog(title = "操作日志_清空", opType = LogAnnotionOpTypeEnum.CLEAN)
    public ResponseData opLogDelete() {
        sysOpLogService.delete();
        return new SuccessResponseData();
    }
}
