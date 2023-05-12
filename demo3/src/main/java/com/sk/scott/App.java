package com.sk.scott;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello World!");
        final List<Thread> threads = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            threads.add(new Thread(
//                            new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("current thread is:#"+Thread.currentThread());
//                    latch.countDown();
//                }
//            }
                    () -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("current thread is:#" + Thread.currentThread());
                        latch.countDown();
                    }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        latch.await();
        System.out.println("finished");

         Stream<String> stream = Stream.of("1","2","3","5");

         stream.forEach(App::print);

         Stream<String> stream1 =List.of(" 1dd"," 2dd"," 344").stream();
         stream1.map(String::trim).map(String::toUpperCase).forEach(System.out::println);

         Stream<String> stream2 = List.of("k1=v1","k2=v2","scott=134345").stream();
        Map<String,String> map = stream2.map((kv)->{
            String[] ss = kv.split("\\=",2);
            return Map.of(ss[0],ss[1]);
         }).reduce(new HashMap<>(),(m,kv)->{
             m.putAll(kv);
             return m;
        });

        map.forEach((k,v)->System.out.println(k+"="+v));
    }

    public  static void print(String s){
        System.out.println(s);
    }

}
