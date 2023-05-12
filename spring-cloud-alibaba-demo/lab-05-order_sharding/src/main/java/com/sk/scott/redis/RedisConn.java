package com.sk.scott.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisConn {
    private    String host = System.getenv("shardingSphere-proxy-snowflake-redis-host")
            ==null?"16.163.97.13":
            System.getenv("shardingSphere-proxy-snowflake-redis-host");
    private  int port = 6379;
    private  String password="";
    private  int databaseNo = 5;
    private  Jedis jedisConn = null;
   // private static JedisPool pool = null;

   static {
       // Set<String> sentinels = new HashSet<>();
       //sentinels.add("127.0.0.1:6379");
      // sentinels.add("172.18.18.208:26379");
//       JedisPoolConfig config = new JedisPoolConfig();
//       config.setMaxIdle(5);
//       config.setMaxTotal(20);
//
//       JedisPool pool = new JedisPool(config, host, port, 2000);
//       jedisConn = pool.getResource();
//       System.out.println("init_redis1----- redis");
//       JedisPoolConfig config = new JedisPoolConfig();
//       config.setMaxIdle(5);
//       config.setMaxTotal(20);
//       JedisPool pool = new JedisPool(config, host, port, 2000);
//       jedisConn = pool.getResource();
//       System.out.println("init_redis1====== redis finished");

   }

   public  Jedis getJedisConn(){
       try {
           //不刷新多线程有问题
           refresh();
           return jedisConn;
       }catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   public RedisConn(){
       refresh();
   }

   public  void refresh(){
       System.out.println("redis_refresh");
       if (jedisConn != null) {
           close();
       }

       JedisPoolConfig config = new JedisPoolConfig();
       config.setMaxIdle(5);
       config.setMaxTotal(20);

       JedisPool pool = new JedisPool(config, host, port, 2000);
       jedisConn = pool.getResource();
       System.out.println("redis_refresh finished");
   }

   public  void close(){
       if (jedisConn!=null) {
           jedisConn.close();
       }
   }

}
