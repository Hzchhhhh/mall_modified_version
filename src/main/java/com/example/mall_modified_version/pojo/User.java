package com.example.mall_modified_version.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import com.example.mall_modified_version.enums.RoleEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable, UserDetails {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;

    private Date createTime;

    private Date updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Assert.notNull(this.role, "role不能为null");
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getRole() == this.role) {
                return roleEnum.getAuthorities();
            }
        }
        return new ArrayList<>(0);
    }

    //必须为true！
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //必须为true！
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //必须为true！
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //必须为true！
    @Override
    public boolean isEnabled() {
        return true;
    }
}