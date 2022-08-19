package com.example.mall_modified_version.service.impl;

import com.example.mall_modified_version.annotation.Idempotent;
import com.example.mall_modified_version.dao.UserMapper;
import com.example.mall_modified_version.enums.ResponseEnum;
import com.example.mall_modified_version.enums.RoleEnum;
import com.example.mall_modified_version.exception.BusinessException;
import com.example.mall_modified_version.pojo.User;
import com.example.mall_modified_version.service.IUserService;
import com.example.mall_modified_version.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * PasswordEncoder用于用户注册，把用户密码加密后，也即是passwordEncoder.encode()之后，
     * 再存入数据库，不应该存储密码明文到数据库
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    //注册
    @Override
    public ResponseVo<User> register(User user) {
        User iuser = userMapper.selectByUsername(user.getUsername());
        if (iuser != null) {
            throw new BusinessException(ResponseEnum.USERNAME_EXIST, "用户名" + user.getUsername() + "已存在");
        }
        try {
            user.setRole(1);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userMapper.insertSelective(user);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ResponseEnum.USERNAME_EXIST, "用户名" + user.getUsername() + "已存在");
        }
        return ResponseVo.success();
    }

    @Override
    //防止接口重复请求
    @Idempotent
    public User login(String username, String password) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            log.warn("[登录失败]  尝试登录失败，失败原因：{}", e.getMessage());
            throw new BusinessException(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        return (User) authentication.getPrincipal();
    }
}
