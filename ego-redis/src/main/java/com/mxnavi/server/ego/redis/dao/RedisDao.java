package com.mxnavi.server.ego.redis.dao;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public interface RedisDao {
	
/*	void jedisLogin();*/
	
	boolean exists(String key);
	
	long del(String key);
	
	String set(String key,String value);
	
	
	String get(String key);
	
	Jedis getJedis(JedisPool jedisPool);

	long expire(String key, int seconds);
	
}
