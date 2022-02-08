package com.hwai.backend.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponseDto {
    private Long id;
    private String name;
    private boolean admin;
    int pinNum;

    public LoginResponseDto(Long id, String name, boolean admin, int pinNum) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.pinNum = pinNum;
    }
}
