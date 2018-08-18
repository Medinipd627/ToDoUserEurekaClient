/*package com.bridgelabz.eureka.util;

*//**************************************************************************************
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:Redis configuration class
 ***************************************************************************************//*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.bridgelabz.eureka.model.User;

@Configuration
public class RedisConfiguration {

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("localhost");
		connectionFactory.setPort(6379);
		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory());
		return redisTemplate;
	}
}*/