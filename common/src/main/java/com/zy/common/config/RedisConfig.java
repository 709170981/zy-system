package com.zy.common.config;

import com.zy.common.models.login.SysLoginUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;

/**
 * @Author: lc
 * @Date: Created in 2020/12/1
 * @Description:
 */
@Configuration
@ConditionalOnClass({RedisConnectionFactory.class})
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public RedisTemplate<String, SysLoginUser> sysLoginUserRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, SysLoginUser> userRedisTemplate = new RedisTemplate<>();
        userRedisTemplate.setConnectionFactory(factory);
        userRedisTemplate.setKeySerializer(new StringRedisSerializer());
        userRedisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
        userRedisTemplate.afterPropertiesSet();
        return userRedisTemplate;
    }

    @Bean
    public RedisTemplate<String, Map<String,Object>> mapRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Map<String,Object>> userRedisTemplate = new RedisTemplate<>();
        userRedisTemplate.setConnectionFactory(factory);
        userRedisTemplate.setKeySerializer(new StringRedisSerializer());
        userRedisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer<>(Map.class));
        userRedisTemplate.afterPropertiesSet();
        return userRedisTemplate;
    }
}
