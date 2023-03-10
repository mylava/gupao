package com.gupao.edu.common.cache;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gupao.edu.common.cache.key.KeyPrefix;
import com.gupao.edu.common.context.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * comment: 缓存工具类
 *
 * @author: lipengfei
 * @date: 26/07/2019
 */
@Slf4j
public class CacheUtil {
    private static final String EMPT_STRING = "";
    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) SpringContextUtil.getApplicationContext().getBean("redisTemplate");

    /**
     * --------------- ---------------
     * String
     * --------------- ---------------
     */

    /**
     * 存数据
     *
     * @param prefix
     * @param key
     * @param value
     * @return
     */

    public static void set(KeyPrefix prefix, String key, String value) {
        redisTemplate.opsForValue().set(prefix.getPrefix() + key, value);
    }


    /**
     * 存数据,设置过期时间
     *
     * @param prefix
     * @param key
     * @param value
     * @return
     */
    public static void setex(KeyPrefix prefix, String key, int expire, String value) {
        redisTemplate.opsForValue().set(prefix.getPrefix() + key, value, expire <= 0 ? prefix.getExpire() : expire, TimeUnit.SECONDS);
    }

    /**
     * 取数据
     *
     * @param prefix
     * @param key
     * @return
     */
    public static String get(KeyPrefix prefix, String key) {
        return (String) redisTemplate.opsForValue().get(prefix.getPrefix() + key);
    }

    /**
     * 取数据
     *
     * @param prefix
     * @param key
     * @param
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(prefix.getPrefix() + key);
    }

    /**
     * 取数据
     *
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(KeyPrefix prefix, String key, Class<T> clazz) {
        JSONArray jsonArray = (JSONArray) redisTemplate.opsForValue().get(prefix.getPrefix() + key);
        List<T> list = jsonArray.toJavaList(clazz);
//        return JSONObject.parseObject(o, new TypeReference<List<T>>(clazz) {
//            });
        return list;
    }


    /**
     * 存数据
     *
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public static <T> void set(KeyPrefix prefix, String key, T value) {
//        String str = bean2JsonString(value);
        redisTemplate.opsForValue().set(prefix.getPrefix() + key, value);
    }


    /**
     * --------------- ---------------
     * 原子操作
     * --------------- ---------------
     */
    /**
     * 递增
     *
     * @param prefix
     * @param key
     * @return
     */
    public static Long incr(KeyPrefix prefix, String key, long delta) {
        return redisTemplate.opsForValue().increment(prefix.getPrefix() + key, delta);
    }

    /**
     * 递减
     *
     * @param prefix
     * @param key
     * @return
     */
//    public static Long decr(KeyPrefix prefix, String key, long delta) {
//        return redisTemplate.opsForValue().(prefix.getPrefix() + key, delta);
//    }

    /**
     * --------------- ---------------
     * 通用操作
     * --------------- ---------------
     */

    /**
     * 判断key是否存在
     *
     * @param prefix
     * @param key
     * @return
     */
    public static Boolean exists(KeyPrefix prefix, String key) {
        return redisTemplate.hasKey(prefix.getPrefix() + key);
    }

    /**
     * 删除指定key
     *
     * @param key
     * @return
     */
    public static Boolean del(KeyPrefix prefix, String key) {
        if (exists(prefix, key)) {
            return redisTemplate.delete(prefix.getPrefix() + key);
        }
        return false;
    }

    /**
     * 更新过期时间
     *
     * @param prefix
     * @param key
     * @return
     */
    public static void expire(KeyPrefix prefix, String key, long second) {
        if (exists(prefix, key)) {
            redisTemplate.expire(prefix.getPrefix() + key, second, TimeUnit.SECONDS);
        }
    }

    /**
     * 返回指定Key的剩余生存时间
     *
     * @param prefix
     * @param key
     * @return
     */
    public static Long getExpireTime(KeyPrefix prefix, String key) {
        if (exists(prefix, key)) {
            return redisTemplate.getExpire(prefix.getPrefix() + key, TimeUnit.SECONDS);
        }
        return null;
    }

    /**
     * --------------- ---------------
     * hash
     * --------------- ---------------
     */
    /**
     * 存hash
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static <T> void hset(KeyPrefix prefix, String key, String field, T value) {
//        redisTemplate.opsForHash().put(prefix.getPrefix() + key, field, bean2JsonString(value));
        redisTemplate.opsForHash().put(prefix.getPrefix() + key, field, value);
    }

    /**
     * 取Hash
     *
     * @param key
     * @param field
     * @return
     */
    public static <T> T hget(KeyPrefix prefix, String key, String field, Class<T> clazz) {
        return (T) redisTemplate.opsForHash().get(prefix.getPrefix() + key, field);
    }

    /**
     * 删除hash 中对应的多个field
     *
     * @param key
     * @param field
     * @return
     */
    public static Long hdel(KeyPrefix prefix, String key, String... field) {
        return redisTemplate.opsForHash().delete(prefix.getPrefix() + key, field);
    }

    /**
     * hash中是否存在field
     *
     * @param key
     * @param field
     * @return
     */
    public static Boolean hexists(KeyPrefix prefix, String key, String field) {
        return redisTemplate.opsForHash().hasKey(prefix.getPrefix() + key, field);
    }

    /**
     * 获取hash中指定key的所有值
     *
     * @param prefix
     * @param key
     * @return
     */
    public static Map<Object, Object> hgetAll(KeyPrefix prefix, String key) {
        return redisTemplate.opsForHash().entries(prefix.getPrefix() + key);
    }

    /**
     * 获取hash中指定key的所有值
     *
     * @param prefix
     * @param key
     * @return
     */
    public static <T> Map<String, T> hgetAll(KeyPrefix prefix, String key, Class<T> clazz) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(prefix.getPrefix() + key);
        Map<String, T> result = new HashMap<>();
        for (Object s : entries.keySet()) {
//            result.put((String) s, jsonString2Bean((String) entries.get(s), clazz));
            result.put((String) s, (T) entries.get(s));
        }
        return result;
    }

    /**
     * 获取hash中指定key的所有 field
     *
     * @param prefix
     * @param key
     * @return
     */
    public static Set<Object> hkeys(KeyPrefix prefix, String key) {
        return redisTemplate.opsForHash().keys(prefix.getPrefix() + key);
    }

    /**
     * 获取hash中指定key的所有 value
     *
     * @param prefix
     * @param key
     * @return
     */
    public static List<Object> hvals(KeyPrefix prefix, String key) {
        return redisTemplate.opsForHash().values(prefix.getPrefix() + key);
    }

    /**
     * --------------- ---------------
     * List
     * --------------- ---------------
     */

    /**
     * 将一个或多个值 value 插入到 list 的表头
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空list mylist 执行命令 LPUSH mylist a b c ，list的值将是 c b a ，
     * 这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
     *
     * @param prefix
     * @param key
     * @return
     */
    public static <T> void lpush(KeyPrefix prefix, String key, List<T> values) {
        for (T value : values) {
            /*String str = bean2JsonString(value);
            if (mapper == str) {
                str = EMPT_STRING;
            }*/
            redisTemplate.opsForList().leftPush(prefix.getPrefix() + key, value);
        }
    }

    /**
     * 将一个或多个值 value 插入到 list 的表头
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空list mylist 执行命令 LPUSH mylist a b c ，list的值将是 c b a ，
     * 这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
     *
     * @param prefix
     * @param key
     * @return
     */
    public static <T> void lpush(KeyPrefix prefix, String key, T value) {
//        String str = bean2JsonString(value);
        redisTemplate.opsForList().leftPush(prefix.getPrefix() + key, value);
    }


    /**
     * 返回list 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示list的第一个元素，以 1 表示list的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示list的最后一个元素， -2 表示list的倒数第二个元素，以此类推。
     *
     * @param prefix
     * @param key
     * @return
     */
    public static <T> List<T> lrange(KeyPrefix prefix, String key, Integer start, Integer end, Class<T> clazz) {
        List<Object> range = redisTemplate.opsForList().range(prefix.getPrefix() + key, start, end);
        List<T> result = new ArrayList<>();
        for (Object s : range) {
//            result.add(jsonString2Bean((String)s, clazz));
            result.add((T) s);
        }
        return result;
    }

    /**
     * 移除并返回list 的头元素。
     *
     * @param prefix
     * @param key
     * @return
     */
    public static <T> T lpop(KeyPrefix prefix, String key, Class<T> clazz) {
        return (T) redisTemplate.opsForList().leftPop(prefix.getPrefix() + key);
    }

    /**
     * 移除并返回list 的尾元素。
     *
     * @param prefix
     * @param key
     * @return
     */
    public static <T> T rpop(KeyPrefix prefix, String key, Class<T> clazz) {
        return (T) redisTemplate.opsForList().rightPop(prefix.getPrefix() + key);
    }

    /**
     * 通过前缀获取多个value
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> multiGet(KeyPrefix prefix, String key, Class<T> clazz) {
        String keys = prefix.getPrefix() + key + "*";
        Set<String> keySet = redisTemplate.keys(keys);
        return (List<T>) redisTemplate.opsForValue().multiGet(keySet);
    }

    /**
     * 使用setnx 锁定key
     *
     * @param key
     * @param seconds
     *            锁定的时间
     * @return 成功true,失败false
     */
    public static boolean lockKey(KeyPrefix prefix, String key, int seconds) {
        boolean getLockSuccess = false;
        int i = 0;
        BoundValueOperations op = redisTemplate.boundValueOps(prefix.getPrefix()+key);
        while (!getLockSuccess) {
            i++;
            getLockSuccess = op.setIfAbsent(1);
            // 拿到锁，则返回,如果超过10次未能获取到锁，则不再去获取
            if (getLockSuccess || i > 10) {
                break;
            } else {
                // 过20毫秒再来拿一次
                log.info("can not lock key:" + key + ", will be get it next 20ms");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (getLockSuccess) {
            op.expire(seconds, TimeUnit.SECONDS);
        } else {
            log.info("can not lock key:" + key);
        }
        return getLockSuccess;

    }


    public static boolean lock(KeyPrefix prefix, String key, String value, int seconds) {
        boolean getLockSuccess = false;
        int i = 0;
        BoundValueOperations op = redisTemplate.boundValueOps(prefix.getPrefix()+key);
        op.set(value);
        while (!getLockSuccess) {
            i++;
            getLockSuccess = op.setIfAbsent(1);
            // 拿到锁，则返回,如果超过10次未能获取到锁，则不再去获取
            if (getLockSuccess || i > 10) {
                break;
            } else {
                // 过20毫秒再来拿一次
                log.info("can not lock key:" + key + ", will be get it next 20ms");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (getLockSuccess) {
            op.expire(seconds, TimeUnit.SECONDS);
        } else {
            log.info("can not lock key:" + key);
        }
        return getLockSuccess;

    }


    public static void unLock(KeyPrefix prefix, String key, String value) {

        try {
            String curVal = (String) redisTemplate.opsForValue().get(prefix.getPrefix() + key);
            if (!StringUtils.isEmpty(curVal) && curVal.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.info(prefix.getPrefix() + key+"解锁失败");
            e.printStackTrace();
        }
    }


    private <T> String bean2JsonString(T value) {
        if (null == value) {
            return null;
        }
        return JSONObject.toJSONString(value);
    }

    private <T> T jsonString2Bean(String str, Class<T> clazz) {
        if (null == str || str.length() <= 0 || null == clazz) {
            return null;
        }
        if (clazz == String.class) {
            return (T) str;
        }
        return JSONObject.parseObject(str, clazz);
    }

}
