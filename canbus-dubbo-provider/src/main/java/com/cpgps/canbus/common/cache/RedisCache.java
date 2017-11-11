package com.cpgps.canbus.common.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
public interface RedisCache {
	/**
	 * 保存值 key - value
	 * 
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	void saveOrUpdate(String key, String value) throws Exception;

	/**
	 * 要存储的Key-Value集合
	 * 
	 * @param values
	 *            Map集合
	 * @throws Exception
	 */
	void saveOrUpdate(HashMap<String, String> values) throws Exception;

	/**
	 * 根据Key获取值
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	String getValue(String key) throws Exception;
	
	/**
	 * Set Key
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	void setValue(String key,String value) throws Exception;


	/**
	 * 同时获取多组key-value
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	List<String> getValues(Collection<String> keys) throws Exception;

	/**
	 * 根据Key删除值
	 * 
	 * @param key
	 * @throws Exception
	 */
	void delete(String key) throws Exception;

	/**
	 * 删除指定的内容
	 * 
	 * @param keys
	 *            List集合
	 * @throws Exception
	 */
	void delete(Collection<String> keys) throws Exception;

	/**
	 * 
	 * 保存为redis的set集合
	 * 
	 * @param key
	 * @param values
	 * @throws Exception
	 */
	void addSetValues(String key, String... values) throws Exception;
	
	/**
	 * 
	 * 保存为redis的set集合，通过管道的方式
	 * 
	 * @param key
	 * @param values
	 * @throws Exception
	 */
	void addSetValuesPipelined(String[] keys, String value) throws Exception;
	
	/**
	 * 
	 * 删除redis的set集合中的某个值，通过管道的方式
	 * 
	 * @param key
	 * @param values
	 * @throws Exception
	 */
	void delSetValuesPipelined(String[] keys, String value) throws Exception;
	
	/**
	 * 删除redis的Hash集合中的某条记录
	 * */
	void delHashByKey(String redisKey, String mapkey) throws Exception;


	/**
	 * 根据Key获取Set集合
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Set<String> getSetValues(String key) throws Exception;
	/**
	 * 根据Key获取Set集合中随机一个值
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	String getSetRandomMember(String key) throws Exception;

	/**
	 * 从集合中删除数据
	 * 
	 * @param key
	 * @param values
	 * @throws Exception
	 */
	void delSetValues(String key, Object... values) throws Exception;

	/**
	 * 添加散列数据
	 * 
	 * @param key
	 * @param args
	 * @throws Exception
	 */
	void addHashSet(String key, HashMap<String, String> args) throws Exception;

	/**
	 * 添加散列数据
	 * @param redisKey
	 * @param mapKey
	 * @param mapValue
	 * @throws Exception
	 */
	void addHashSet(String redisKey, String mapKey,String mapValue) throws Exception;

	/**
	 * 获取散列数据
	 * 
	 * @param key
	 * @throws Exception
	 */
	Map<String, String> getHashSet(String key) throws Exception;

	/**
	 * 从散列表中删除数据
	 * 
	 * @param key
	 * @param values
	 * @throws Exception
	 */
	void delHashSetValues(String key, Object... values) throws Exception;

	/**
	 * 往有序集合里面添加数据
	 * 
	 * @param key
	 * @param value
	 * @param score
	 * @throws Exception
	 */
	void addZset(String key, String value, double score) throws Exception;

	/**
	 * 从有序集合里面获取数据
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Set<String> getZsetValues(String key) throws Exception;
	/**
	 * 从有序集合里面获取数据总数量
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Long getZsetValuesCount(String key) throws Exception;

	/**
	 * 从有序集合里面删除数据
	 * 
	 * @param key
	 * @param values
	 * @throws Exception
	 */
	void delZsetValues(String key, Object... values) throws Exception;

	/**
	 * 从特定的map里面获取特定key对应的值
	 * 
	 * @param redisKey
	 * @param mapKey
	 * @return
	 * @throws Exception
	 */
	String getHashByKey(String redisKey, String mapKey) throws Exception;
	
	/**
	 * 从set中获取
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Set<String> getSet(String key)throws Exception;
	
	Boolean hasKey(String key) throws Exception;
}
