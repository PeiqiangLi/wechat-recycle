package com.wechat.recycle;

import com.wechat.recycle.entity.User;
import com.wechat.recycle.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void selectOne() {
        User user = userMapper.selectOne(1);
        System.out.println(user.getId());
    }
}