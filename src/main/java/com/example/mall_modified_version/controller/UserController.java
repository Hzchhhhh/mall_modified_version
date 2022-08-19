package com.example.mall_modified_version.controller;

import com.example.mall_modified_version.consts.MallConst;
import com.example.mall_modified_version.dao.UserMapper;
import com.example.mall_modified_version.enums.ResponseEnum;
import com.example.mall_modified_version.enums.RoleEnum;
import com.example.mall_modified_version.exception.BusinessException;
import com.example.mall_modified_version.form.UserLoginForm;
import com.example.mall_modified_version.form.UserRegisterForm;
import com.example.mall_modified_version.pojo.User;
import com.example.mall_modified_version.service.IUserService;
import com.example.mall_modified_version.service.impl.UserServiceImpl;
import com.example.mall_modified_version.utils.JwtUtil;
import com.example.mall_modified_version.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	//@RequestBody: json方式请求
	@PostMapping("/user/register")
	public ResponseVo register(@Valid @RequestBody UserRegisterForm userRegisterForm) {
		//@Valid 注解通常用于对象属性字段的规则检测
		User user = new User();
		BeanUtils.copyProperties(userRegisterForm, user);
		//dto
		return userServiceImpl.register(user);
	}

	@PostMapping("/user/login")
	public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
								  HttpServletResponse response) {
		User user = userServiceImpl.login(userLoginForm.getUsername(), userLoginForm.getPassword());
		if (RoleEnum.ORDINARY_USER.getRole() != user.getRole()) {
			throw new BusinessException(ResponseEnum.USERNAME_OR_PASSWORD_ERROR, "非普通用户账户无法在此登录");
		}
		String token = jwtUtil.getTokenFromUser(user);
		response.setHeader("token", token);
		return ResponseVo.success(user);
	}

	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseVo<User> userInfo(@AuthenticationPrincipal User user) {
		User iuser = userMapper.selectByPrimaryKey(user.getId());
		return ResponseVo.success(iuser);
	}

	/**
	 * {@link TomcatServletWebServerFactory} getSessionTimeoutInMinutes
	 */
	@PostMapping("/user/logout")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseVo logout(HttpServletResponse response) {
		response.setHeader("token", "");
		return ResponseVo.success();
	}
}
