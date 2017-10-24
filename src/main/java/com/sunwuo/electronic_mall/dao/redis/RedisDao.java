package com.sunwuo.electronic_mall.dao.redis;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.sunwuo.electronic_mall.vo.RedisModel;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by acy on 17/08/17.
 */
public class RedisDao {
    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<RedisModel> schema = RuntimeSchema.createFrom(RedisModel.class);


    public RedisModel getRedisModel(String key) {
        //redis操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                //并没有实现哪部序列化操作
                //采用自定义序列化
                //protostuff: pojo.
                byte[] bytes = jedis.get(key.getBytes());
                //缓存重获取到
                if (bytes != null) {
                    RedisModel redisModel = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, redisModel, schema);
                    //反序列化
                    return redisModel;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String putRedisModel(RedisModel redisModel) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = redisModel.getObjectKey();
                byte[] bytes = ProtostuffIOUtil.toByteArray(redisModel, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout = 60 * 60;//1小时
                return jedis.setex(key.getBytes(), timeout, bytes);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
