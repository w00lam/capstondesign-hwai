package com.hwai.backend.user.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailRequestDto {
    private String email;

    @Builder
    public EmailRequestDto(String email) {
        this.email = email;
    }
}
