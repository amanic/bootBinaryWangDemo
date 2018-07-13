package com.amateur.wanbei.service.impl;

import com.amateur.wanbei.dao.mapper.UserMapper;
import com.amateur.wanbei.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenhaitao on 2018/6/5.
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Autowired
    UserMapper userMapper;

    @Override
    public String hello() {
        return userMapper.selectByPrimaryKey(1).toString();
    }
}
