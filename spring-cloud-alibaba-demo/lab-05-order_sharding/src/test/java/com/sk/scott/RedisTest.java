package com.sk.scott;

import com.sk.scott.redis.RedisConn;
import com.sk.scott.utils.snowflakeSub;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;

public class RedisTest {
    @Test
    public void testRedis(){
      Jedis jc =  new RedisConn().getJedisConn();
      jc.set("test1","33");
      String v = jc.get("test1");
        System.out.println(v);

    }
    @Test
    public void testSub() throws IOException {
        snowflakeSub  snowflakeSub = new snowflakeSub();
        Jedis jedis = new RedisConn().getJedisConn();
       // jedis.subscribe(snowflakeSub,"snowflake-change");
        //System.in.read();
    }
}
