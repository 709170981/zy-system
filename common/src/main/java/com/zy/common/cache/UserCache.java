package com.zy.common.cache;

import com.zy.common.models.login.SysLoginUser;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 登录用户的缓存，存储了当前登录的用户
 * <p>
 * 一般用于在线用户的查看和过滤器检测用户是否登录
 * <p>
 * key为用户的唯一id，value为LoginUser对象
 *
 */
public class UserCache extends AbstractRedisCacheOperator<SysLoginUser> {

    /**
     * 登录用户缓存前缀
     */
    public static final String LOGIN_USER_CACHE_PREFIX = "LOGIN_USER_";

//    public UserCache(TimedCache<String, SysLoginUser> timedCache) {
//        super(timedCache);
//    }

    public UserCache(RedisTemplate<String, SysLoginUser> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public String getCommonKeyPrefix() {
        return LOGIN_USER_CACHE_PREFIX;
    }
}

