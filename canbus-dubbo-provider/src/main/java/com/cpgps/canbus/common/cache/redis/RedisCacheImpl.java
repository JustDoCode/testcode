package com.cpgps.canbus.common.cache.redis;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.cpgps.canbus.common.cache.RedisCache;

/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
@Component
public class RedisCacheImpl implements RedisCache {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void saveOrUpdate(HashMap<String, String> values) throws Exception {
		ValueOperations<String, String> valueOps = stringRedisTemplate
				.opsForValue();
		valueOps.multiSet(values);
	}

	@Override
	public void saveOrUpdate(String key, String value) throws Exception {
		ValueOperations<String, String> valueOps = stringRedisTemplate
				.opsForValue();
		valueOps.set(key, value);
	}

	@Override
	public String getValue(String key) throws Exception {
		ValueOperations<String, String> valueOps = stringRedisTemplate
				.opsForValue();
		return valueOps.get(key);
	}
	
	@Override
	public void setValue(String key,String value) throws Exception {
		ValueOperations<String, String> valueOps = stringRedisTemplate
				.opsForValue();
		valueOps.set(key, value);
	}

	@Override
	public List<String> getValues(Collection<String> keys) throws Exception {
		ValueOperations<String, String> valueOps = stringRedisTemplate
				.opsForValue();
		return valueOps.multiGet(keys);
	}

	@Override
	public void delete(String key) throws Exception {
		stringRedisTemplate.delete(key);
	}

	@Override
	public void delete(Collection<String> keys) throws Exception {
		stringRedisTemplate.delete(keys);
	}

	@Override
	public void addSetValues(String key, String... values) throws Exception {
		SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
		setOps.add(key, values);
	}

	@Override
	public Set<String> getSetValues(String key) throws Exception {
		SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
		return setOps.members(key);
	}
	
	@Override
	public String getSetRandomMember(String key) throws Exception{
		SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
		return setOps.randomMember(key);
	}

	@Override
	public void delSetValues(String key, Object... values) throws Exception {
		SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
		setOps.remove(key, values);
	}
	
	@Override
	public Long getZsetValuesCount(String key) throws Exception{
		return stringRedisTemplate.opsForSet().size(key);
	}

	@Override
	public void addHashSet(String key, HashMap<String, String> args)
			throws Exception {
		HashOperations<String, String, String> hashsetOps = stringRedisTemplate
				.opsForHash();
		hashsetOps.putAll(key, args);
	}

	@Override
	public Map<String, String> getHashSet(String key) throws Exception {
		HashOperations<String, String, String> hashsetOps = stringRedisTemplate
				.opsForHash();
		return hashsetOps.entries(key);
	}

	@Override
	public void delHashSetValues(String key, Object... values) throws Exception {
		HashOperations<String, String, String> hashsetOps = stringRedisTemplate
				.opsForHash();
		hashsetOps.delete(key, values);
	}

	@Override
	public void addZset(String key, String value, double score)
			throws Exception {
		ZSetOperations<String, String> zSetOps = stringRedisTemplate
				.opsForZSet();
		zSetOps.add(key, value, score);
	}

	@Override
	public Set<String> getZsetValues(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delZsetValues(String key, Object... values) throws Exception {
		ZSetOperations<String, String> zSetOps = stringRedisTemplate
				.opsForZSet();
		zSetOps.remove(key, values);
	}

	@Override
	public String getHashByKey(String redisKey, String mapKey) throws Exception {
		HashOperations<String, String, String> hashsetOps =stringRedisTemplate.opsForHash();
		return hashsetOps.get(redisKey, mapKey);
	}

	@Override
	public void addHashSet(String redisKey, String mapKey, String mapValue)
			throws Exception {
		stringRedisTemplate.opsForHash().put(redisKey, mapKey, mapValue);
	}


	@Override
	public Set<String> getSet(String key) throws Exception {
		SetOperations<String, String> setOperations=stringRedisTemplate.opsForSet();
		return setOperations.members(key);
		
	}

	@Override
	public void addSetValuesPipelined(final String[] keys, final String value) throws Exception {
		stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
		    public Object doInRedis(RedisConnection connection) {
		        StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
		        for(int i=0; i< keys.length; i++) {
		            stringRedisConn.sAdd(keys[i], value);
		        }
		        return null;//必须返回null
		    }
		});
	}
	
	@Override
	public void delSetValuesPipelined(final String[] keys, final String value) throws Exception {
		stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
		    public Object doInRedis(RedisConnection connection) {
		        StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
		        for(int i=0; i< keys.length; i++) {
		            stringRedisConn.sRem(keys[i], value);
		        }
		        return null;//必须返回null
		    }
		});
	}

	@Override
	public void delHashByKey(String redisKey, String mapKey) throws Exception {
		HashOperations<String, String, String> hashMapOps =stringRedisTemplate.opsForHash();
		hashMapOps.delete(redisKey, mapKey);
	}
	@Override
	public Boolean hasKey(String key) throws Exception{
		return stringRedisTemplate.hasKey(key);
	}
	
}
