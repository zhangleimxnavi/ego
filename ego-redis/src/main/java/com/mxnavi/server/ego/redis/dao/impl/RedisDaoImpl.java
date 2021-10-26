package com.mxnavi.server.ego.redis.dao.impl;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;

import org.springframework.stereotype.Repository;

import com.mxnavi.server.ego.redis.dao.RedisDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Repository
public class RedisDaoImpl implements RedisDao{

	@Resource
	private JedisPool jedisPool;
	
	/**
	 * 从 连接池中 获取jedis
	 */
	public Jedis getJedis(JedisPool jedisPool){
		return jedisPool.getResource();
	}
	
	
	/**
	 * IS EXISTS ??
	 */
	@Override
	public boolean exists(String key) {
		
		Jedis jedis = this.getJedis(jedisPool);
		boolean exists = jedis.exists(key);
		jedis.close();
		return exists;
		
	}
	
	
	/**
	 * SET
	 */
	@Override
	public String set(String key, String value) {
		Jedis jedis = this.getJedis(jedisPool);
		String result = jedis.set(key, value);
		jedis.close();
		return result;

	}
	
	
	/**
	 * GET
	 */
	@Override
	public String get(String key) {
		Jedis jedis = this.getJedis(jedisPool);
		String result = jedis.get(key);
		jedis.close();
		return result;

	}

	/**
	 * DEL
	 */
	@Override
	public long del(String key) {
		Jedis jedis = this.getJedis(jedisPool);
		long index = jedis.del(key);
		jedis.close();
		return index;
	}

}
