package com.sk.scott;

import com.sk.scott.utils.EtcdUtils;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.WatchOption;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EtcdTest {

    @Test
    public void testEtcd() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        WatchOption watchOption = WatchOption.newBuilder().isPrefix(true).build();
        executor.execute(()-> EtcdUtils.watchKey("test",watchOption, (type, key, value) -> {
            System.out.println(type);
            System.out.println(key);
            System.out.println(value);
        }));
        EtcdUtils.putValueByKey("test3","aaa11");
        GetOption getOption = GetOption.newBuilder().isPrefix(true).build();
        List<String> test = EtcdUtils.getValuesByKey("test",getOption);
        System.out.println(test);
        test.forEach(System.out::println);
        EtcdUtils.deleteValueByKey("test");
        Thread.sleep(4000);

    }
}
