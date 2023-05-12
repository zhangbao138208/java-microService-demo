package com.sk.scott.utils;


import io.etcd.jetcd.watch.WatchEvent;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class ZooKeeperUtils implements snowflakeUtils {

    private static ZooKeeper client = null;

    public  void watchKey(String keyString, Watchable watchable){
        // 对/abc进行watch
        try {
            ZooKeeperUtils.getClient().getChildren(keyString,
                    watchedEvent -> {

                        String path = watchedEvent.getPath();
                        Event.KeeperState stat =  watchedEvent.getState();

                        System.out.println("path:" + path);
                        System.out.println("KeeperState:" + stat);
                        System.out.println("EventType:" + watchedEvent.getType());
                        WatchEvent.EventType type = WatchEvent.EventType.UNRECOGNIZED;
                        switch (watchedEvent.getType()){
                            case NodeCreated:
                            case NodeDataChanged:
                            case NodeChildrenChanged:
                                type = WatchEvent.EventType.PUT;
                                watchable.handle(type,path,getVal(path));
                                break;
                            case NodeDeleted:
                                type = WatchEvent.EventType.DELETE;
                                break;
                            default:
                                break;
                        }

                    },
                    null);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<kv> getKVByKey(String key) {
        try {
           List<String> keys = ZooKeeperUtils.getClient().getChildren(key,false);
           keys.forEach(System.out::println);
           return keys.stream().map(k-> new kv(k,getVal(key+"/"+k))).collect(Collectors.toList());
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getVal(String path){
        try {
            System.out.println(path);
            Stat stat = new Stat();
            byte[] Data =  ZooKeeperUtils.getClient().getData(path,false,stat);
            System.out.println(new String(Data, StandardCharsets.UTF_8)+"===data");
            return new String(Data, StandardCharsets.UTF_8);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setData(String key,String val){
        try {
            ZooKeeperUtils.getClient().setData(key, val.getBytes(StandardCharsets.UTF_8), -1);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createNode(String key,String val){
        try {
            ZooKeeperUtils.getClient().create(key, val.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Del(String key){
        try {
            ZooKeeperUtils.getClient().delete(key,-1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        }
    }

    //链接初始化 单例模式
    private static synchronized ZooKeeper getClient()  {
        if(null == client){
            try {
                 client = new ZooKeeper(
                         YamlHelper.getURI(),
                         20000,
                         new Watcher() {
                             @Override
                             public void process(WatchedEvent event) {
                                 System.out.println(event);
                             }
                         }
                 );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        return client;
    }
}
