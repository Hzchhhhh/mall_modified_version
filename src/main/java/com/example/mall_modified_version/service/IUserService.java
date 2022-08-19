package com.example.mall_modified_version.service;

import com.example.mall_modified_version.pojo.User;
import com.example.mall_modified_version.vo.ResponseVo;

public interface IUserService {
    //注册
    ResponseVo register(User user);
    //登录
    User login(String username, String password);
}
