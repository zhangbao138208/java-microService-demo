package com.sk.scott;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sk.scott.mapper.orderMapper;
import com.sk.scott.model.order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class shardingTest {

    @Autowired
    private orderMapper orderMapper;

    @Test
    public void testOrder() throws InterruptedException {
      //  CountDownLatch latch = new CountDownLatch(6*5);
        for (int i = 0; i < 6; i++) {
            order order = new order();
            order.setRemark("order"+String.valueOf(i));
            order.setUserId(Long.valueOf(i));
            orderMapper.insert(order);
        }

      //  latch.await();
    }

    @Test
    public void query(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("order_id",816217930822975488L,
                816217933289226240L,
                816217935377989632L
                );
        List<order> orders = orderMapper.selectList(queryWrapper);
        orders.forEach(System.out::println);
    }
}
