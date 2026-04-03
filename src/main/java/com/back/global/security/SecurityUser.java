package com.back.global.security;

import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class SecurityUser extends User { // User로 SecurityUser 생성.

    // 기존 3개 필드 외에 id와 nickname을 추가.
    private int id;
    private String nickname;

    public SecurityUser(int id, String username, @Nullable String password, String nickname, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.nickname = nickname;
    }
}