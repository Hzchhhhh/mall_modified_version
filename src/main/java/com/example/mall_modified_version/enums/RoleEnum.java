package com.example.mall_modified_version.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

/**
 * 角色0-管理员,1-普通用户
 */
@Getter
public enum RoleEnum {
	/**
	 * 普通用户
	 */
	ORDINARY_USER(1, AuthorityUtils.createAuthorityList("ROLE_USER")),
	/**
	 * 管理员
	 */
	ADMINISTRATOR(0, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));

	private final int role;

	private final List<GrantedAuthority> authorities;

	RoleEnum(int role, List<GrantedAuthority> authorities) {
		this.role = role;
		this.authorities = authorities;
	}

	public int getRole() {
		return role;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
