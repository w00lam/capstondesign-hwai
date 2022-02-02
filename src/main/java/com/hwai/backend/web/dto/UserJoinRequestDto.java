package com.hwai.backend.web.dto;

import com.hwai.backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequestDto {

    private String name;
    private String birth;
    private String tel;
    private String email;
    private String pwd;
    private boolean admin;

    public UserJoinRequestDto(String name, String birth, String tel, String email, String pwd, boolean admin) {
        this.name = name;
        this.birth = birth;
        this.tel = tel;
        this.email = email;
        this.pwd = pwd;
        this.admin = admin;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .birth(birth)
                .tel(tel)
                .email(email)
                .pwd(pwd)
                .admin(admin)
                .build();
    }
}
