package com.sk.scott;

import com.sk.scott.config.SnowflakeKeyGenerator;
import com.sk.scott.utils.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


public class SnowflakeKeyGeneratorTest {

    @Test
    public void testGenerateId() throws InterruptedException {
        //CountDownLatch latch = new CountDownLatch(100);
       // SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(10,4);
        for (int i = 0; i < 100; i++) {
//         new Thread(new Runnable() {
//             @Override
//             public void run() {
//                 Long id = SnowflakeIdWorker.generateId();
//                 System.out.println(StringUtils.leftPad(Long.toBinaryString(id),64,'0'));
//                 //0 00010110101001000100011101000110100101101 00100 01010 000000000000
//                // System.out.println(id);
//                 latch.countDown();
//             }
//         }).start();
           //Thread.sleep(1000);
            Long id = SnowflakeIdWorker.generateId();
            System.out.println(StringUtils.leftPad(Long.toBinaryString(id),64,'0'));
            //0 00010110101001000100011101000110100101101 00100 01010 000000000000
          //  Thread.sleep(500);
        }

       // latch.await();
    }
    @Test
    public void testGenerateKey(){
        System.out.println(System.getenv("shardingSphere-proxy-snowflake-redis-host"));
        System.out.println(System.getenv("shardingSphere-proxy-snowflake-redis-host")==null);
        SnowflakeKeyGenerator snowflakeKeyGenerator = new SnowflakeKeyGenerator();
        var id = snowflakeKeyGenerator.generateKey();
        System.out.println(id);
        System.out.println(StringUtils.leftPad(Long.toBinaryString((Long)id ),64,'0'));
    }

    @Test
    public void testLong2Bin(){
        //0 00010110101001001011111100011111011101110 00100 01010 000000000000
        //0 00010110101001001011111100011111000110111 00100 01010 000000000000
        //0 00010110101001010000110000111010110011101 00010 00010 000000000100
        //0 00010110101001010000110000111010110011101 00001 00001 000000000011
        //0 00010110101001010001011100010100000100111 00100 01010 000000000000
        System.out.println(StringUtils.leftPad(Long.toBinaryString(816121515723591680L),
                64,'0'));

    }
    @Test
    public void testMask(){
        int mask1 = 0b11111111;

        int mask2 = 0b111111;

        Long val = 697580132499456L;
        System.out.println(mask1 & val>>9);
        System.out.println(mask2 & val>>17);

    }
}
