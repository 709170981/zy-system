package com.zy.common.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.zy.common.consts.CommonConstant;
import com.zy.common.consts.SymbolConstant;
import com.zy.common.exception.ServiceException;

import java.util.List;

import static com.zy.common.exception.enums.ServerExceptionEnum.CONSTANT_EMPTY;

/**
 * 系统参数配置获取
 */
public class ConstantContextHolder {

    private static final Log log = Log.get();

    /**
     * 获取租户功能是否开启
     */
    public static Boolean getTenantOpenFlag() {
        return getSysConfigWithDefault("GUNS_TENANT_OPEN", Boolean.class, false);
    }

    /**
     * 获取druid监控的密码
     */
    public static String getDruidMonitorPassword() {
        return getSysConfigWithDefault("GUNS_DRUID_PASSWORD", String.class, RandomUtil.randomString(10));
    }

    /**
     * 获取druid的账号
     */
    public static String getDruidMonitorUsername() {
        return getSysConfigWithDefault("GUNS_DRUID_USERNAME", String.class, RandomUtil.randomString(10));
    }

    /**
     * 获取放开xss过滤的接口
     */
    public static List<String> getUnXssFilterUrl() {
        String gunsUnXssFilterUrl = getSysConfigWithDefault("GUNS_UN_XSS_FILTER_URL", String.class, null);
        if (ObjectUtil.isEmpty(gunsUnXssFilterUrl)) {
            return CollectionUtil.newArrayList();
        } else {
            return CollectionUtil.toList(gunsUnXssFilterUrl.split(SymbolConstant.COMMA));
        }
    }

    /**
     * 获取演示环境开关是否开启，默认为false
     */
    public static Boolean getDemoEnvFlag() {
        return getSysConfigWithDefault("GUNS_DEMO_ENV_FLAG", Boolean.class, false);
    }

    /**
     * 邮件的配置
     */
    public static EmailConfigs getEmailConfigs() {
        String host = getSysConfig("GUNS_EMAIL_HOST", String.class, true);
        String username = getSysConfig("GUNS_EMAIL_USERNAME", String.class, true);
        String password = getSysConfig("GUNS_EMAIL_PASSWORD", String.class, true);
        Integer port = getSysConfig("GUNS_EMAIL_PORT", Integer.class, true);
        String from = getSysConfig("GUNS_EMAIL_FROM", String.class, true);
        Boolean ssl = getSysConfig("GUNS_EMAIL_SSL", Boolean.class, true);

        EmailConfigs emailConfigs = new EmailConfigs();
        emailConfigs.setHost(host);
        emailConfigs.setUser(username);
        emailConfigs.setPass(password);
        emailConfigs.setPort(port);
        emailConfigs.setFrom(from);
        emailConfigs.setSslEnable(ssl);
        return emailConfigs;
    }

    /**
     * 获取腾讯云短信的配置
     */
    public static TencentSmsConfigs getTencentSmsConfigs() {
        String gunsTencentSmsSecretId = getSysConfig("GUNS_TENCENT_SMS_SECRET_ID", String.class, true);
        String gunsTencentSmsSecretKey = getSysConfig("GUNS_TENCENT_SMS_SECRET_KEY", String.class, true);
        String gunsTencentSmsSdkAppId = getSysConfig("GUNS_TENCENT_SMS_SDK_APP_ID", String.class, true);
        String gunsTencentSmsSign = getSysConfig("GUNS_TENCENT_SMS_SIGN", String.class, true);

        TencentSmsConfigs tencentSmsConfigs = new TencentSmsConfigs();
        tencentSmsConfigs.setSecretId(gunsTencentSmsSecretId);
        tencentSmsConfigs.setSecretKey(gunsTencentSmsSecretKey);
        tencentSmsConfigs.setSdkAppId(gunsTencentSmsSdkAppId);
        tencentSmsConfigs.setSign(gunsTencentSmsSign);
        return tencentSmsConfigs;
    }

    /**
     * 获取阿里云短信的配置
     */
    public static AliyunSmsConfigs getAliyunSmsConfigs() {
        String gunsSmsAccesskeyId = getSysConfig("GUNS_ALIYUN_SMS_ACCESSKEY_ID", String.class, true);
        String gunsSmsAccesskeySecret = getSysConfig("GUNS_ALIYUN_SMS_ACCESSKEY_SECRET", String.class, true);
        String gunsSmsSignName = getSysConfig("GUNS_ALIYUN_SMS_SIGN_NAME", String.class, true);
        String gunsSmsLoginTemplateCode = getSysConfig("GUNS_ALIYUN_SMS_LOGIN_TEMPLATE_CODE", String.class, true);
        String gunsSmsInvalidateMinutes = getSysConfig("GUNS_ALIYUN_SMS_INVALIDATE_MINUTES", String.class, true);

        AliyunSmsConfigs aliyunSmsProperties = new AliyunSmsConfigs();
        aliyunSmsProperties.setAccessKeyId(gunsSmsAccesskeyId);
        aliyunSmsProperties.setAccessKeySecret(gunsSmsAccesskeySecret);
        aliyunSmsProperties.setSignName(gunsSmsSignName);
        aliyunSmsProperties.setLoginTemplateCode(gunsSmsLoginTemplateCode);
        aliyunSmsProperties.setInvalidateMinutes(Convert.toInt(gunsSmsInvalidateMinutes));
        return aliyunSmsProperties;
    }

    /**
     * 获取jwt密钥，默认是32位随机字符串
     */
    public static String getJwtSecret() {
        return getSysConfigWithDefault("GUNS_JWT_SECRET", String.class, RandomUtil.randomString(32));
    }

    /**
     * 获取默认密码
     */
    public static String getDefaultPassWord() {
        return getSysConfigWithDefault("GUNS_DEFAULT_PASSWORD", String.class, CommonConstant.DEFAULT_PASSWORD);
    }

    /**
     * 获取会话过期时间，默认2小时
     */
    public static Long getSessionTokenExpireSec() {
        return getSysConfigWithDefault("GUNS_SESSION_EXPIRE", Long.class, 2 * 60 * 60L);
    }

    /**
     * 获取token过期时间（单位：秒）
     * <p>
     * 默认时间1天
     */
    public static Long getTokenExpireSec() {
        return getSysConfigWithDefault("GUNS_TOKEN_EXPIRE", Long.class, 86400L);
    }

    /**
     * 获取自定义的windows环境本地文件上传路径
     */
    public static String getDefaultFileUploadPathForWindows() {
        return getSysConfigWithDefault("GUNS_FILE_UPLOAD_PATH_FOR_WINDOWS", String.class, "");
    }

    /**
     * 获取自定义的linux环境本地文件上传路径
     */
    public static String getDefaultFileUploadPathForLinux() {
        return getSysConfigWithDefault("GUNS_FILE_UPLOAD_PATH_FOR_LINUX", String.class, "");
    }

    /**
     * 获取是否允许单用户登陆的开关， 默认为false
     */
    public static Boolean getEnableSingleLogin() {
        return getSysConfigWithDefault("GUNS_ENABLE_SINGLE_LOGIN", Boolean.class, false);
    }

    /**
     * 获取阿里云定位接口
     **/
    public static String getIpGeoApi() {
        return getSysConfig("GUNS_IP_GEO_API", String.class, false);
    }

    /**
     * 获取阿里云定位appCode
     **/
    public static String getIpGeoAppCode() {
        return getSysConfig("GUNS_IP_GEO_APP_CODE", String.class, false);
    }

    /**
     * 获取config表中的配置，如果为空返回默认值
     *
     * @param configCode   变量名称，对应sys_config表中的code
     * @param clazz        返回变量值的类型
     * @param defaultValue 如果结果为空返回此默认值
     */
    public static <T> T getSysConfigWithDefault(String configCode, Class<T> clazz, T defaultValue) {
        String configValue = ConstantContext.me().getStr(configCode);
        if (ObjectUtil.isEmpty(configValue)) {
            // 将默认值加入到缓存常量
            log.warn(">>> 系统配置sys_config表中存在空项，configCode为：{}，系统采用默认值：{}", configCode, defaultValue);
            ConstantContext.me().put(configCode, defaultValue);
            return defaultValue;
        } else {
            try {
                return Convert.convert(clazz, configValue);
            } catch (Exception e) {
                return defaultValue;
            }
        }
    }

    /**
     * 获取config表中的配置，如果为空，是否抛出异常
     *
     * @param configCode   变量名称，对应sys_config表中的code
     * @param clazz        返回变量值的类型
     * @param nullThrowExp 若为空是否抛出异常
     */
    public static <T> T getSysConfig(String configCode, Class<T> clazz, Boolean nullThrowExp) {
        String configValue = ConstantContext.me().getStr(configCode);
        if (ObjectUtil.isEmpty(configValue)) {
            if (nullThrowExp) {
                String format = StrUtil.format(">>> 系统配置sys_config表中存在空项，configCode为：{}", configCode);
                log.error(format);
                throw new ServiceException(CONSTANT_EMPTY.getCode(), format);
            } else {
                return null;
            }
        } else {
            try {
                return Convert.convert(clazz, configValue);
            } catch (Exception e) {
                if (nullThrowExp) {
                    String format = StrUtil.format(">>> 系统配置sys_config表中存在格式错误的值，configCode={}，configValue={}", configCode, configValue);
                    log.error(format);
                    throw new ServiceException(CONSTANT_EMPTY.getCode(), format);
                } else {
                    return null;
                }
            }
        }
    }
}
