package com.hwai.backend.user.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PwUpdateRequestDto {
    private String new_pw;

    @Builder
    public PwUpdateRequestDto(String new_pw) {
        this.new_pw = new_pw;
    }
}
