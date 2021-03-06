package com.zy.common.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import com.alibaba.fastjson.JSONPath;
import com.zy.common.config.ConstantContextHolder;
import com.zy.common.consts.CommonConstant;
import com.zy.common.consts.SymbolConstant;
import com.zy.common.context.RequestNoContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 根据ip地址定位工具类，使用阿里云定位api
 */
public class IpAddressUtil {

    private static final Log log = Log.get();

    private static final String LOCAL_IP = "127.0.0.1";

    private static final String LOCAL_REMOTE_HOST = "0:0:0:0:0:0:0:1";

    /**
     * 获取客户端ip
     */
    public static String getIp(HttpServletRequest request) {
        if (ObjectUtil.isEmpty(request)) {
            return SymbolConstant.DASH;
        } else {
            String remoteHost = ServletUtil.getClientIP(request);
            return LOCAL_REMOTE_HOST.equals(remoteHost) ? LOCAL_IP : remoteHost;
        }
    }

    /**
     * 根据ip地址定位
     */
    @SuppressWarnings("unchecked")
    public static String getAddress(HttpServletRequest request) {
        String resultJson = SymbolConstant.DASH;

        String ip = getIp(request);

        //如果是本地ip或局域网ip，则直接不查询
        if (ObjectUtil.isEmpty(ip) || NetUtil.isInnerIP(ip)) {
            return resultJson;
        }

        try {
            //获取阿里云定位api接口
            String api = ConstantContextHolder.getIpGeoApi();
            //获取阿里云定位appCode
            String appCode = ConstantContextHolder.getIpGeoAppCode();
            if (ObjectUtil.isAllNotEmpty(api, appCode)) {
                String path = "$['data']['country','region','city','isp']";
                String appCodeSymbol = "APPCODE";
                HttpRequest http = HttpUtil.createGet(String.format(api, ip));
                http.header(CommonConstant.AUTHORIZATION, appCodeSymbol + " " + appCode);
                resultJson = http.timeout(3000).execute().body();
                resultJson = String.join("", (List<String>) JSONPath.read(resultJson, path));
            }
        } catch (Exception e) {
            resultJson = SymbolConstant.DASH;
            log.error(">>> 根据ip定位异常，请求号为：{}，具体信息为：{}", RequestNoContext.get(), e.getMessage());
        }
        return resultJson;
    }

}
