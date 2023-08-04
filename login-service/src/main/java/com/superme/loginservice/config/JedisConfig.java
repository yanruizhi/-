package com.superme.loginservice.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    Logger logger = LogManager.getLogger(RedisConfig.class);


    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;


    @Bean
    public JedisPool jedisPool() {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }





    //使用jedisPool注册jedis实例
    @Bean
    public Jedis getJedis() {
        if (jedisPool() != null) {
            return jedisPool().getResource();
        } else {
            return null;
        }
    }

    //直接注册jedis实例
//    @Bean
//    public Jedis getJedis() {
//        Jedis jedis = new Jedis(host, port);
//        jedis.auth(password); //设置密码,否则无权限操作redis库
//        return jedis;
//    }
}
