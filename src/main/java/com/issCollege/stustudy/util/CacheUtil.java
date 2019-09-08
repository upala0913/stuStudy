package com.issCollege.stustudy.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/*********************
 * @author TeacherXue
 * @time 2018��11��29�� ����10:58:41
 * @version V1.0
 **********************/
public class CacheUtil {
	private static final Logger LOG = Logger.getLogger(CacheUtil.class);

	private static RedisTemplate<String, Object> redisTemplate = CacheContextUtil.getBean("redisTemplate",
			RedisTemplate.class);

	private static StringRedisTemplate stringRedisTemplate = CacheContextUtil.getBean("stringRedisTemplate",
			StringRedisTemplate.class);

	private static String CACHE_PREFIX;

	private static boolean CACHE_CLOSED;

	private CacheUtil() {

	}

	@SuppressWarnings("rawtypes")
	private static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			String str = obj.toString();
			if ("".equals(str.trim())) {
				return true;
			}
			return false;
		}
		if (obj instanceof List) {
			List<Object> list = (List<Object>) obj;
			if (list.isEmpty()) {
				return true;
			}
			return false;
		}
		if (obj instanceof Map) {
			Map map = (Map) obj;
			if (map.isEmpty()) {
				return true;
			}
			return false;
		}
		if (obj instanceof Set) {
			Set set = (Set) obj;
			if (set.isEmpty()) {
				return true;
			}
			return false;
		}
		if (obj instanceof Object[]) {
			Object[] objs = (Object[]) obj;
			if (objs.length <= 0) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * ��������keyֵ
	 * 
	 * @param key
	 *            ����key
	 * @return
	 */
	private static String buildKey(String key) {
		if (CACHE_PREFIX == null || "".equals(CACHE_PREFIX)) {
			return key;
		}
		return CACHE_PREFIX + ":" + key;
	}

	/**
	 * ���ػ����ǰ׺
	 * 
	 * @return CACHE_PREFIX_FLAG
	 */
	public static String getCachePrefix() {
		return CACHE_PREFIX;
	}

	/**
	 * ���û����ǰ׺	 * 
	 * @param cachePrefix
	 */
	public static void setCachePrefix(String cachePrefix) {
		if (cachePrefix != null && !"".equals(cachePrefix.trim())) {
			CACHE_PREFIX = cachePrefix.trim();
		}
	}

	/**
	 * �رջ���
	 * 
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean close() {
		LOG.debug(" cache closed ! ");
		CACHE_CLOSED = true;
		return true;
	}

	/**
	 * �򿪻���
	 * 
	 * @return true:���� false:������
	 */
	public static boolean openCache() {
		CACHE_CLOSED = false;
		return true;
	}

	/**
	 * ��黺���Ƿ���
	 * 
	 * @return true:�ѹر� false:�ѿ���
	 */
	public static boolean isClose() {
		return CACHE_CLOSED;
	}

	/**
	 * �ж�keyֵ�Ƿ����
	 * 
	 * @param key
	 *            �����key
	 * @return true:���� false:������
	 */
	public static boolean hasKey(String key) {
		LOG.debug(" hasKey key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return false;
			}
			key = buildKey(key);
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ƥ����������key
	 * 
	 * @param patternKey
	 * @return key�ļ���
	 */
	public static Set<String> keys(String patternKey) {
		LOG.debug(" keys key :" + patternKey);
		try {
			if (isClose() || isEmpty(patternKey)) {
				return Collections.emptySet();
			}
			return redisTemplate.keys(patternKey);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return Collections.emptySet();
	}

	/**
	 * ����keyɾ������
	 * 
	 * @param key
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean del(String... key) {
		LOG.debug(" delete key :" + key.toString());
		try {
			if (isClose() || isEmpty(key)) {
				return false;
			}
			Set<String> keySet = new HashSet<>();
			for (String str : key) {
				keySet.add(buildKey(str));
			}
			redisTemplate.delete(keySet);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ����keyɾ������
	 * 
	 * @param key
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean delPattern(String key) {
		LOG.debug(" delete Pattern keys :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.delete(redisTemplate.keys(key));
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ɾ��һ��keyֵ
	 * 
	 * @param keys
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean del(Set<String> keys) {
		LOG.debug(" delete keys :" + keys.toString());
		try {
			if (isClose() || isEmpty(keys)) {
				return false;
			}
			Set<String> keySet = new HashSet<>();
			for (String str : keys) {
				keySet.add(buildKey(str));
			}
			redisTemplate.delete(keySet);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ���ù���ʱ��
	 * 
	 * @param key
	 *            ����key
	 * @param seconds
	 *            ��������
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean setExp(String key, long seconds) {
		LOG.debug(" setExp key :" + key + "\tsconds:" + seconds);
		try {
			if (isClose() || isEmpty(key) || seconds > 0) {
				return false;
			}
			key = buildKey(key);
			return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ��ѯ����ʱ��
	 * 
	 * @param key
	 *            ����key
	 * @return ����
	 */
	public static Long getExpire(String key) {
		LOG.debug(" getExpire key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return 0L;
			}
			key = buildKey(key);
			return redisTemplate.getExpire(key, TimeUnit.SECONDS);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return 0L;
	}

	/**
	 * �������key-value
	 * 
	 * @param key
	 *            �����
	 * @param value
	 *            ����ֵ
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean setString(String key, String value) {
		LOG.debug(" setString key : " + key + ":\t" + value);
		try {
			if (isClose() || isEmpty(key) || isEmpty(value)) {
				return false;
			}
			key = buildKey(key);
			stringRedisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * �������key-value
	 * 
	 * @param key
	 *            �����
	 * @param value
	 *            ����ֵ
	 * @param seconds
	 *            ����
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean setString(String key, String value, long seconds) {
		LOG.debug(" setString key :" + key + "value:" + value + "timeout" + seconds);
		try {
			if (isClose() || isEmpty(key) || isEmpty(value)) {
				return false;
			}
			key = buildKey(key);
			stringRedisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ����keyȡ��String value
	 * 
	 * @param key
	 *            ����keyֵ
	 * @return String �����String
	 */
	public static String getString(String key) {
		LOG.debug(" getString key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return null;
			}
			key = buildKey(key);
			return stringRedisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * ȥ�Ļ����е����ֵ��+1
	 * 
	 * @param key
	 *            ����keyֵ
	 * @return long �����е����ֵ+1
	 */
	public static long incr(String key) {
		LOG.debug(" incr key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return 0;
			}
			key = buildKey(key);
			return redisTemplate.opsForValue().increment(key, 1);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * �����д������л���Object����
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param obj
	 *            ��������л�����
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean set(String key, Object obj) {
		LOG.debug(" set key :" + key + "\tvalue:" + obj);
		try {
			if (isClose() || isEmpty(key) || isEmpty(obj)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForValue().set(key, obj);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * �����д������л���Object����
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param obj
	 *            ��������л�����
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean setObj(String key, Object obj, long seconds) {
		LOG.debug(" set key :" + key + "\tvalue:" + obj + "\tseconds:" + seconds);
		try {
			if (isClose() || isEmpty(key) || isEmpty(obj)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForValue().set(key, obj);
			if (seconds > 0) {
				redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ȡ�������д洢�����л�����
	 * 
	 * @param key
	 *            ����key
	 * @param clazz
	 *            ������
	 * @return <T> ���л�����
	 */
	public static <T> T getObj(String key, Class<T> clazz) {
		LOG.debug(" get key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return null;
			}
			key = buildKey(key);
			return (T) redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * ����Map����
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param map
	 *            ����map
	 * @return true:�ɹ� false:ʧ��
	 */
	public static <T> boolean setMap(String key, Map<String, T> map) {
		try {
			if (isClose() || isEmpty(key) || isEmpty(map)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ȡ�������map
	 * 
	 * @param key
	 *            ����key
	 * @return map �����map
	 */
	@SuppressWarnings("rawtypes")
	public static Map getMap(String key) {
		LOG.debug(" getMap key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return null;
			}
			key = buildKey(key);
			return redisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * ��ѯ�����map�ļ��ϴ�С
	 * 
	 * @param key
	 *            ����key
	 * @return int ����map�ļ��ϴ�С
	 */
	public static long getMapSize(String key) {
		LOG.debug(" getMap key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return 0;
			}
			key = buildKey(key);
			return redisTemplate.opsForHash().size(key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * ����key�Լ�hashKeyȡ����Ӧ��Object����
	 * 
	 * @param key
	 *            ����key
	 * @param hashKey
	 *            ��Ӧmap��key
	 * @return object map�еĶ���
	 */
	public static Object getMapKey(String key, String hashKey) {
		LOG.debug(" getMapkey :" + key + "\thashKey:" + hashKey);
		try {
			if (isClose() || isEmpty(key) || isEmpty(hashKey)) {
				return null;
			}
			key = buildKey(key);
			return redisTemplate.opsForHash().get(key, hashKey);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * ȡ��������map������keyֵ
	 * 
	 * @param key
	 *            ����key
	 * @return Set<String> map��keyֵ�ϼ�
	 */
	public static Set<Object> getMapKeys(String key) {
		LOG.debug(" getMapKeys key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return null;
			}
			key = buildKey(key);
			return redisTemplate.opsForHash().keys(key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * ɾ��map��ָ����keyֵ
	 * 
	 * @param key
	 *            ����key
	 * @param hashKey
	 *            map��ָ����hashKey
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean delMapKey(String key, String hashKey) {
		LOG.debug(" delMapKey key :" + key + "\thashKey:" + hashKey);
		try {
			if (isClose() || isEmpty(key) || isEmpty(hashKey)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForHash().delete(key, hashKey);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ����Map����
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param map
	 *            ����map
	 * @param seconds
	 *            ����
	 * @return true:�ɹ� false:ʧ��
	 */
	public static <T> boolean setMapExp(String key, Map<String, T> map, long seconds) {
		LOG.debug(" setMapExp key :" + key + "\tvalue:" + map + "\tseconds" + seconds);
		try {
			if (isClose() || isEmpty(key) || isEmpty(map)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForHash().putAll(key, map);
			redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * map�м����µ�key
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param hashKey
	 *            map��Keyֵ
	 * @param value
	 *            map��valueֵ
	 * @return true:�ɹ� false:ʧ��
	 */
	public static <T> boolean addMap(String key, String hashKey, T value) {
		LOG.debug(" addMap key :" + key + "\thashKey:" + hashKey + "\tvalue:" + value);
		try {
			if (isClose() || isEmpty(key) || isEmpty(hashKey) || isEmpty(value)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForHash().put(key, hashKey, value);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * �������List
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param list
	 *            ����List
	 * @return true:�ɹ� false:ʧ��
	 */
	public static <T> boolean setList(String key, List<T> list) {
		LOG.debug(" setList key :" + key + "\tlist:" + list);
		try {
			if (isClose() || isEmpty(key) || isEmpty(list)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForList().leftPushAll(key, list.toArray());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ����keyֵȡ����Ӧ��list�ϼ�
	 * 
	 * @param key
	 *            ����key
	 * @return List<Object> �����ж�Ӧ��list�ϼ�
	 */
	public static <V> List<V> getList(String key) {
		LOG.debug(" getList key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return null;
			}
			key = buildKey(key);
			return (List<V>) redisTemplate.opsForList().range(key, 0, -1);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * ����keyֵ��ȡ��Ӧ��list�ϼ�
	 * 
	 * @param key
	 *            ����key
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            ����λ��
	 * @return
	 */
	public static void trimList(String key, int start, int end) {
		LOG.debug(" trimList key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return;
			}
			key = buildKey(key);
			redisTemplate.opsForList().trim(key, start, end);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * ȡ��list�ϼ���ָ��λ�õĶ���
	 * 
	 * @param key
	 *            ����key
	 * @param index
	 *            ����λ��
	 * @return Object listָ������λ�õĶ���
	 */
	public static Object getIndexList(String key, int index) {
		LOG.debug(" getIndexList key :" + key + "\tindex:" + index);
		try {
			if (isClose() || isEmpty(key) || index < 0) {
				return null;
			}
			key = buildKey(key);
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Object����List
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param value
	 *            List�е�ֵ
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean addList(String key, Object value) {
		LOG.debug(" addList key :" + key + "value" + value);
		try {
			if (isClose() || isEmpty(key) || isEmpty(value)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForList().leftPush(key, value);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * �������List
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param list
	 *            ����List
	 * @param seconds
	 *            ����
	 * @return true:�ɹ� false:ʧ��
	 */
	public static <T> boolean setList(String key, List<T> list, long seconds) {
		LOG.debug(" setList key :" + key + "\tvalue:" + list + "\tseconds:" + seconds);
		try {
			if (isClose() || isEmpty(key) || isEmpty(list)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForList().leftPushAll(key, list.toArray());
			if (seconds > 0) {
				redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * set���ϴ��뻺��
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param set
	 *            ����set����
	 * @return true:�ɹ� false:ʧ��
	 */
	public static <T> boolean setSet(String key, Set<T> set) {
		LOG.debug(" setSet key :" + key + "\tvalue:" + set);
		try {
			if (isClose() || isEmpty(key) || isEmpty(set)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForSet().add(key, set.toArray());
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * set����������value
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param value
	 *            ���ӵ�value
	 * @return true:�ɹ� false:ʧ��
	 */
	public static boolean addSet(String key, Object value) {
		LOG.debug(" addSet key :" + key + "\tvalue" + value);
		try {
			if (isClose() || isEmpty(key) || isEmpty(value)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForSet().add(key, value);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * set���ϴ��뻺��
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @param set
	 *            ����set����
	 * @param seconds
	 *            ����
	 * @return true:�ɹ� false:ʧ��
	 */
	public static <T> boolean setSet(String key, Set<T> set, long seconds) {
		LOG.debug(" setSet key :" + key + "\tvalue:" + set + "\tseconds:" + seconds);
		try {
			if (isClose() || isEmpty(key) || isEmpty(set)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForSet().add(key, set.toArray());
			if (seconds > 0) {
				redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * ȡ�������ж�Ӧ��set�ϼ�
	 * 
	 * @param <T>
	 * @param key
	 *            ����key
	 * @return Set<Object> �����е�set�ϼ�
	 */
	public static <T> Set<T> getSet(String key) {
		LOG.debug(" getSet key :" + key);
		try {
			if (isClose() || isEmpty(key)) {
				return null;
			}
			key = buildKey(key);
			return (Set<T>) redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * ���򼯺ϴ�����ֵ
	 * 
	 * @param key
	 *            ����key
	 * @param value
	 *            ����value
	 * @param score
	 *            ����
	 * @return
	 */
	public static boolean addZSet(String key, Object value, double score) {
		LOG.debug(" addZSet key :" + key + "\tvalue:" + value + "\tsconds" + score);
		try {
			if (isClose() || isEmpty(key) || isEmpty(value)) {
				return false;
			}
			key = buildKey(key);
			return redisTemplate.opsForZSet().add(key, value, score);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * �����򼯺���ɾ��ָ��ֵ
	 * 
	 * @param key
	 *            ����key
	 * @param value
	 *            ����value
	 * @return
	 */
	public static boolean removeZSet(String key, Object value) {
		LOG.debug(" removeZSet key :" + key + "\tvalue:" + value);
		try {
			if (isClose() || isEmpty(key) || isEmpty(value)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForZSet().remove(key, value);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * �����򼯺���ɾ��ָ��λ�õ�ֵ
	 * 
	 * @param key
	 *            ����key
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            ����Ϊֹ
	 * @return
	 */
	public static boolean removeZSet(String key, long start, long end) {
		LOG.debug(" removeZSet key :" + key + "\tstart:" + start + "\tend:" + end);
		try {
			if (isClose() || isEmpty(key)) {
				return false;
			}
			key = buildKey(key);
			redisTemplate.opsForZSet().removeRange(key, start, end);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * �����򼯺��л�ȡָ��λ�õ�ֵ
	 * 
	 * @param key
	 *            ����key
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            ����Ϊֹ
	 * @return
	 */
	public static <T> Set<T> getZSet(String key, long start, long end) {
		LOG.debug(" getZSet key :" + key + "\tstart:" + start + "\tend:" + end);
		try {
			if (isClose() || isEmpty(key)) {
				return Collections.emptySet();
			}
			key = buildKey(key);
			return (Set<T>) redisTemplate.opsForZSet().range(key, start, end);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return Collections.emptySet();
	}
}
