package com.hwai.backend.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponseDto {
    private Long id;
    private String name;
    private boolean admin;

    public LoginResponseDto(Long id, String name, boolean admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
    }
}
