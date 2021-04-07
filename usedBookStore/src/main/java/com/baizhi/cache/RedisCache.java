package com.baizhi.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class RedisCache implements Cache {

    @Autowired
    private static JedisConnectionFactory jedisConnectionFactory;

    private String id;

    public RedisCache(String id) throws IllegalAccessException {
        if (id==null){
            throw new IllegalAccessException("id is null");
        }
        this.id = id;
    }

    public RedisCache() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    public JedisConnection getConnection(){
//        JedisConnection connection = null;
        JedisConnection connection = jedisConnectionFactory.getConnection();
        return connection;
    }

    @Override
    public void putObject(Object key, Object value) {
        JedisConnection connection = null;
        try {
            connection = getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.set(serializer.serialize(key),serializer.serialize(value));

            connection.lPush(serializer.serialize(id), serializer.serialize(key));
            System.out.println("写入缓存：" + key + "," + value);
        } catch (SerializationException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public Object getObject(Object key) {
        Object res = null;
        JedisConnection con = null;
        try {
            con = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            res = serializer.deserialize(con.get(serializer.serialize(key)));
            if (res != null) {
                System.out.println("获取的缓存：" + res.toString());
            } else {
                System.out.println("当前没有缓存：" + key);
            }
        } catch (SerializationException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return res;
    }

    @Override //删除指定缓存信息
    public Object removeObject(Object key) {
        Object res = null;
        JedisConnection conn = null;
        try {
            conn = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            res = conn.expire(serializer.serialize(key), 0);
            conn.lRem(serializer.serialize(id),0,serializer.serialize(key));
            System.out.println("删除缓存：" + key);
        } catch (SerializationException e) {
            e.printStackTrace();
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return res;
    }

    @Override
    public void clear() {
        log.info("clear all cache info..");
        JedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            long length = connection.lLen(serializer.serialize(id)); // 返回列表的长度
            if (0 == length){
                return;
            }
            //获取列表值
            List<byte[]> keys = connection.lRange(serializer.serialize(id),0,length);
            for (byte[] key : keys) {
                connection.expire(key,0);
                System.out.println("删除缓存:"+serializer.deserialize(key).toString());
            }
            connection.expireAt(serializer.serialize(id),0);
            keys.clear();
        } catch (SerializationException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.close();
            }
        }

    }

    @Override
    public int getSize() {
        int result = 0;
        JedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            result = Integer.parseInt(connection.dbSize().toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }
}
