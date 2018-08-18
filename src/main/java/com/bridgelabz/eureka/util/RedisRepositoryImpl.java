/*package com.bridgelabz.eureka.util;
*//***********************************************************************************
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:Redis implementation for the ToDo application
 ************************************************************************************//*

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgelabz.eureka.model.User;

@Repository
public class RedisRepositoryImpl implements RedisRepository<String, User> {

	private static final String key = "TOKEN";
	@Autowired
	private JWToken tokenUtility;
	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	private HashOperations<String, String, String> hashOps;

	RedisRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOps = redisTemplate.opsForHash();
	}

	*//**
	 * @param token
	 *//*
	@Override
	public void saveInRedis(String token) {
		String userId = tokenUtility.verifyToken(token).getIssuer();
		hashOps.put(key, userId, token);
	}

	*//**
	 * @param userId
	 *//*
	@Override
	public void getFromRedis(String userId) {
		hashOps.get(key, userId);
	}
}
*/