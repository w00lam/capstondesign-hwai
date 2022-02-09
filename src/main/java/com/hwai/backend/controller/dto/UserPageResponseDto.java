package com.hwai.backend.controller.dto;

import com.hwai.backend.domain.message.Message;
import com.hwai.backend.domain.user.User;
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
        this.name = getName();
        this.birth = getBirth();
        this.tel = getTel();
        this.email = getEmail();
        this.pw = getBirth();
        this.message = message.getMessage();
    }
}
