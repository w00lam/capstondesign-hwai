package com.hwai.backend.user.controller.dto;

import com.hwai.backend.common.message.Message;
import com.hwai.backend.user.domian.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserPageResponseDto {
    private String name;
    private String birth;
    private String tel;
    private String email;
    private String pw;
    private String message;

    public UserPageResponseDto(User entity, Message message) {
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.tel = entity.getTel();
        this.email = entity.getEmail();
        this.pw = entity.getPw();
        this.message = message.getMessage();
    }
}
