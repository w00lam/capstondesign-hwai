package com.hwai.backend.user.controller.dto;

import com.hwai.backend.user.domian.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindPwResponseDto {
    private String pw;

    public FindPwResponseDto(User user) {
        this.pw = user.getPw();
    }
}
