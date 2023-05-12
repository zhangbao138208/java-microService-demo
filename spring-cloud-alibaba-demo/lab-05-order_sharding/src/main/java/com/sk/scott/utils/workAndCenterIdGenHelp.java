package com.sk.scott.utils;

import com.sk.scott.redis.RedisConn;
import io.etcd.jetcd.watch.WatchEvent;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class workAndCenterIdGenHelp {
    private  static List<String> members;


    private static List<kv> kvMembers;

    private final static String snowflakeList = "shardingSphere-snowflakeList";
    private final static String snowflakeChange = "shardingSphere-snowflakeChange";
    private final static int interval = 60*10;

    private static Long offset = 0L;
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock ();

    private final static String snowflakeC6W8Prefix = "/shardingSphere/snowflake/c6w8";

    //防止饿死
    private static long lastUpdateTime = System.currentTimeMillis();

    private static RedisConn redisConn ;

    private static snowflakeUtils _snowflakeUtils;

    static {
        try {
            _snowflakeUtils = (snowflakeUtils) Class.forName("com.sk.scott.utils."+YamlHelper.getType()+"Utils").getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("init_wcg wcg");
//        refresh();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                snowflakeSub  snowflakeSub = new snowflakeSub();
//                Jedis jedis = redisConn.getJedisConn();
//                jedis.subscribe(snowflakeSub,snowflakeChange);
//            }
//        }).start();
//        System.out.println("init_wcg wcg finished");
        System.out.println("init_wcg wcg");
        kvMembers = _snowflakeUtils.getKVByKey(snowflakeC6W8Prefix);
        System.out.println("init_wcg wcg finished");

       new Thread(() -> _snowflakeUtils.watchKey(snowflakeC6W8Prefix,
               (type, key, value) -> {
                   try {
                       workAndCenterIdGenHelp.class.getMethod(YamlHelper.getType()+"Handler",
                               WatchEvent.EventType.class,
                               String.class,String.class).invoke(null,type,key,value);
                   } catch (IllegalAccessException e) {
                       throw new RuntimeException(e);
                   } catch (InvocationTargetException e) {
                       throw new RuntimeException(e);
                   } catch (NoSuchMethodException e) {
                       throw new RuntimeException(e);
                   }
               })).start();
    }

    public static void ZooKeeperHandler(WatchEvent.EventType type, String key, String value){
        try {
            lock.writeLock().lock();
            kvMembers = _snowflakeUtils.getKVByKey(snowflakeC6W8Prefix);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    public static void EtcdHandler(WatchEvent.EventType type, String key, String value){
        System.out.println("=============update========="+type);
        switch (type){
            case PUT:
                try {
                    lock.writeLock().lock();
                    if(kvMembers.stream().filter(kv->kv.getKey().equals(key)).count()<=0){
                        System.out.println(key+"===p==="+value);
                        kvMembers.add(new kv(key,value));
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.writeLock().unlock();
                }
                break;
            case DELETE:
                try {
                    lock.writeLock().lock();
                  Iterator<kv> iterator = kvMembers.iterator();
                  while (iterator.hasNext()){
                      kv kv = iterator.next();
                      if (kv.getKey().equals(key)){
                          iterator.remove();
                          System.out.println(key+"===r==="+value);
                      }
                  }

                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.writeLock().unlock();
                }
                break;
            case UNRECOGNIZED:
                System.out.println("UNRECOGNIZED");
                break;
            default:
               new Exception("UNRECOGNIZED key");
        }
    }

    public static int[] dynamicGen(){


        if (kvMembers.stream().count()<=0)
            return null;
        lock.readLock().lock();
        try {
            kv ret = kvMembers.get((int) ((offset++)%kvMembers.stream().count()));
            String[] snows = ret.getVal().split(":");
            if (snows.length!=2)
                return null;
            System.out.println(snows[0]+"========="+snows[1]);
            return new int[]{
                    Integer.parseInt(snows[0]),
                    Integer.parseInt(snows[1])
            };
        }finally {
            lock.readLock().unlock();
        }

    }

    public static void refresh() {
        //防止多线程错误
        redisConn = new RedisConn();
        System.out.println("wcg refresh");
        redisConn.refresh();
        System.out.println("wcg refresh1");
        Jedis jedis = redisConn.getJedisConn();
        if (jedis == null){
            System.out.println("jedis null");
            return;
        }
        System.out.println("wcg lock");

        lock.writeLock().lock();
        try {
            System.out.println("wcg get list");
            members = jedis.lrange(snowflakeList,0,-1);
            System.out.println("wcg get list finished");
            members.forEach(System.out::println);
        }finally {
            lock.writeLock().unlock();
        }

    }

    public static int[] gen(){
       if ((System.currentTimeMillis()-lastUpdateTime)/1000 >= interval) {
          refresh();
          lastUpdateTime = System.currentTimeMillis();
       }

       if (members.stream().count()<=0)
           return null;
       lock.readLock().lock();
       try {
           String ret = members.get((int) ((offset++)%members.stream().count()));
           String[] snows = ret.split(":");
           if (snows.length!=2)
               return null;
           System.out.println(snows[0]+"========="+snows[1]);
         return new int[]{
                 Integer.parseInt(snows[0]),
                 Integer.parseInt(snows[1])
         };
       }finally {
           lock.readLock().unlock();
       }

    }
}
