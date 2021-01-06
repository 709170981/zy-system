package com.zy.cms.controller.monitor;

import com.zy.common.annotion.BusinessLog;
import com.zy.common.enums.LogAnnotionOpTypeEnum;
import com.zy.common.models.response.ResponseData;
import com.zy.common.models.response.SuccessResponseData;
import com.zy.common.service.monitor.SysMachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统属性监控控制器
 *
 * @author xuyuxiang
 * @date 2020/6/5 14:36
 */
@RestController
public class SysMachineController {

    @Resource
    private SysMachineService sysMachineService;

    /**
     * 系统属性监控
     *
     * @author xuyuxiang
     * @date 2020/6/5 14:38
     */
    @GetMapping("/sysMachine/query")
    @BusinessLog(title = "系统属性监控_查询", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData query() {
        return new SuccessResponseData(sysMachineService.query());
    }
}
