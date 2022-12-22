package cc.sika.common.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author 吴畅
 * @创建时间 2022/12/20 - 9:34
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ObjectMapper JSONMapper;

    /**
     * 缓存基本对象
     *
     * @param key   缓存的键
     * @param value 缓存的值
     * @param <T>   缓存的类型
     */
    public <T> void setObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 带过期时间缓存基本对象
     *
     * @param key      缓存的键
     * @param value    缓存的值
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     * @param <T>      缓存值的类型
     */
    public <T> void setObjectWithTime(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }


    /**
     * 设置有效时间
     *
     * @param key      要设置时间的键
     * @param timeout  超时时间, long
     * @param timeUnit 时间单位
     * @return 设置成功->true
     */
    public boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, timeUnit));
    }

    /**
     * 设置有效时间, 时间单位为秒
     *
     * @param key     要设置时间的键
     * @param timeout 超时时间, long
     * @return 设置成功->true
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    public Object getObject(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }


    public <T> T getObject(final String key, Class<T> tClass) throws JsonProcessingException {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Object bean = operations.get(key);
        if (bean != null) {
            /* 将对象的'='替换成':', 可以进行类型转换 */
            String obj2Json = JSONMapper.writeValueAsString(bean);
            return JSONMapper.readValue(obj2Json, tClass);
        }
        return null;
    }

    /**
     * 删除单个对象
     *
     * @param key 对象的键
     * @return 删除成功 -> true
     */
    public boolean deleteObject(final String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 删除多个对象
     *
     * @param collection 键的集合
     * @return 删除的数量
     */
    public long deleteList(final Collection<String> collection) {
        return redisTemplate.delete(collection);
    }
}
