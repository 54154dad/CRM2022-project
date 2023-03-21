package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.model.User;

import java.util.List;
import java.util.Map;

/**
 * @Classname UserService
 * @Date 2022/12/20
 * @Created by YQ
 */
public interface UserService {
    User selectUserByLoginActAndPwd(Map<String,Object> map);

    List<User> selectUserAll();
}
