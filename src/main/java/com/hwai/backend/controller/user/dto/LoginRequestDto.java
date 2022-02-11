package com.hwai.backend.controller.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    private String email;
    private String pw;

    @Builder
    public LoginRequestDto(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }
}
