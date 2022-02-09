package com.hwai.backend.controller.dto;

import com.hwai.backend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequestDto {
    private String name;
    private String birth;
    private String tel;
    private String email;
    private String pw;
    private boolean admin;

    @Builder
    public JoinRequestDto(String name, String birth, String tel, String email, String pw, boolean admin) {
        this.name = name;
        this.birth = birth;
        this.tel = tel;
        this.email = email;
        this.pw = pw;
        this.admin = admin;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .birth(birth)
                .tel(tel)
                .email(email)
                .pw(pw)
                .admin(admin)
                .build();
    }
}
