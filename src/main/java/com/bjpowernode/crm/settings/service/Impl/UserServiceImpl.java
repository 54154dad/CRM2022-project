package com.bjpowernode.crm.settings.service.Impl;

import com.bjpowernode.crm.settings.mapper.UserMapper;
import com.bjpowernode.crm.settings.model.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * @Classname UserServiceImpl
 * @Date 2022/12/20
 * @Created by YQ
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }

    @Override
    public List<User> selectUserAll() {
        return userMapper.selectUserAll();
    }
}
