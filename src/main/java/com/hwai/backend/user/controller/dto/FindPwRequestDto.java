package com.hwai.backend.user.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindPwRequestDto {
    private String name;
    private String birth;
    private String email;

    @Builder
    public FindPwRequestDto(String name, String birth, String email) {
        this.name = name;
        this.birth = birth;
        this.email = email;
    }
}
