package com.wechat.recycle;

import com.wechat.recycle.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecycleApplicationTests {

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getOrders() {
        System.out.println(orderMapper.getOrderabc());
    }

}

