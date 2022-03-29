package com.construo.ff4j.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

    @Value("${redis.config.host}")
    private String host;

    @Value("${redis.config.port}")
    private int port;

    @Bean
    RedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration(host, port);
        return new JedisConnectionFactory(redisConf);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
