package com.hwai.backend.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    private String email;
    private String pw;
    private boolean admin;

    @Builder
    public LoginRequestDto(String email, String pw, boolean admin) {
        this.email = email;
        this.pw = pw;
        this.admin = admin;
    }
}
