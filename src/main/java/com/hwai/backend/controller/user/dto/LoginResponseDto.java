package com.hwai.backend.controller.user.dto;

import com.hwai.backend.domain.message.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponseDto {
    private Long id;
    private String name;
    private boolean admin;
    int pinNum;
    private String message;

    public LoginResponseDto(Long id, String name, boolean admin, int pinNum, Message message) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.pinNum = pinNum;
        this.message = message.getMessage();
    }
}
