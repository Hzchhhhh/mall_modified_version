package com.example.mall_modified_version.utils;

import com.example.mall_modified_version.MallModifiedVersionApplicationTests;
import com.example.mall_modified_version.enums.RoleEnum;
import com.example.mall_modified_version.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author: hzc
 * @date: 2022/8/18-7:27
 */
@Slf4j
public class JwtUtilTest extends MallModifiedVersionApplicationTests {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void getTokenFromUser() {
    }

    @Test
    public void getUserFromToken() {
    }
}