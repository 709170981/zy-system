package com.zy.common.context;

/**
 * 临时保存参数id字段值，用于唯一性校验
 */
public class RequestParamIdContext {

    private static final ThreadLocal<Long> PARAM_ID_HOLDER = new ThreadLocal<>();

    /**
     * 设置id
     */
    public static void set(Long id) {
        PARAM_ID_HOLDER.set(id);
    }

    /**
     * 获取id
     */
    public static Long get() {
        return PARAM_ID_HOLDER.get();
    }

    /**
     * 清除缓存id
     */
    public static void clear() {
        PARAM_ID_HOLDER.remove();
    }

}
