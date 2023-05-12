package com.sk.scott.utils;


import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.WatchOption;
import io.etcd.jetcd.watch.WatchEvent;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
public class EtcdUtils implements snowflakeUtils {

    private static final int maxEvents = Integer.MAX_VALUE;
    //etcd客户端链接
    private static Client client = null;

    /**
     * 根据指定的配置名称获取对应的value
     * @param key 配置项
     * @return
     * @throws Exception
     */
    public static String getValueByKey(String key, GetOption option) {
        List<KeyValue> kvs = null;
        try {
            kvs = EtcdUtils.getClient().getKVClient().get(ByteSequence.from(key, StandardCharsets.UTF_8),option).get().getKvs();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(kvs.size()+"  ====   kvs size =========");
        kvs.forEach(System.out::println);
        if(kvs.size()>0){
            return kvs.get(0).getValue().toString(StandardCharsets.UTF_8);
        }
        else {
            return null;
        }
    }

    public static List<String> getValuesByKey(String key, GetOption option) {
        List<KeyValue> kvs = null;
        try {
            kvs = EtcdUtils.getClient().getKVClient().get(ByteSequence.from(key, StandardCharsets.UTF_8),option).get().getKvs();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(kvs.size()+"  ====   kvs size =========");
        kvs.forEach(System.out::println);
//        if(kvs.size()>0){
//            return kvs.get(0).getValue().toString(StandardCharsets.UTF_8);
//        }
       return   kvs.stream().map(kv->kv.getValue().toString(StandardCharsets.UTF_8)).collect(Collectors.toList());
    }

    public  List<kv> getKVByKey(String key) {
        List<KeyValue> kvs = null;
        try {
            kvs = EtcdUtils.getClient().getKVClient().get(ByteSequence.from(key, StandardCharsets.UTF_8),GetOption.newBuilder().isPrefix(true).build()).get().getKvs();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(kvs.size()+"  ====   kvs size =========");
        kvs.forEach(System.out::println);

        return   kvs.stream().map(kv->new kv(kv.getKey().toString(StandardCharsets.UTF_8),kv.getValue().toString(StandardCharsets.UTF_8))).collect(Collectors.toList());
    }

    public static String getValueByKey(String key)  {
       return getValueByKey(key,GetOption.DEFAULT);
    }

    /**
     * 新增或者修改指定的配置
     * @param key
     * @param value
     * @return
     */
    public static void putValueByKey(String key, String value){
        EtcdUtils.getClient().getKVClient().put(ByteSequence.from(key,StandardCharsets.UTF_8),ByteSequence.from(value.getBytes(StandardCharsets.UTF_8)));
    }
    /**
     * 删除指定的配置
     * @param key key
     * @return
     */
    public static void deleteValueByKey(String key){
        EtcdUtils.getClient().getKVClient().delete(ByteSequence.from(key,StandardCharsets.UTF_8));
    }

    public  void watchKey(String keyString,Watchable watchable){
        watchKey(keyString,WatchOption.newBuilder().isPrefix(true).build(),watchable);
    }

    /**
     * 监听指定key的变化
     * @param keyString key
     */
    public static void watchKey(String keyString,WatchOption watchOption,Watchable watchable){

        CountDownLatch latch = new CountDownLatch(maxEvents);
        ByteSequence key = ByteSequence.from(keyString, StandardCharsets.UTF_8);
        Watch.Listener listener = Watch.listener(response -> {
            log.info("Watching for key={}", key);

            for (WatchEvent event : response.getEvents()) {
                log.info("type={}, key={}, value={}", event.getEventType(),
                        Optional.ofNullable(event.getKeyValue().getKey()).map(bs -> bs.toString(StandardCharsets.UTF_8)).orElse(""),
                        Optional.ofNullable(event.getKeyValue().getValue()).map(bs -> bs.toString(StandardCharsets.UTF_8)).orElse(""));
                watchable.handle(event.getEventType(),
                        Optional.ofNullable(event.getKeyValue().getKey()).map(bs -> bs.toString(StandardCharsets.UTF_8)).orElse(""),
                        Optional.ofNullable(event.getKeyValue().getValue()).map(bs -> bs.toString(StandardCharsets.UTF_8)).orElse(""));

            }

            latch.countDown();
        });
        try (Watch watch = getClient().getWatchClient();

             Watch.Watcher watcher = watch.watch(key,watchOption, listener) ) {

            latch.await();
        } catch (Exception e) {
            log.error("Watching Error {}", e);
           // System.exit(1);
        }
    }


    //链接初始化 单例模式
    private static synchronized Client getClient(){
        if(null == client){
            client = Client.builder().endpoints(YamlHelper.getURI()).build();
        }
        return client;
    }
    private static String getConfig(List<KeyValue> kvs){
        if(kvs.size()>0){
            String config = kvs.get(0).getKey().toString(StandardCharsets.UTF_8);
            String value = kvs.get(0).getValue().toString(StandardCharsets.UTF_8);
            log.info("etcd 's config 's config key is :{},value is:{}",config,value);
            return value;
        }
        else {
            return null;
        }
    }

}