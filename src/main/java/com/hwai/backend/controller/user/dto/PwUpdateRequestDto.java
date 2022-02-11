package com.hwai.backend.controller.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PwUpdateRequestDto {
    private Long id;
    private String new_pw;

    @Builder
    public PwUpdateRequestDto(Long id, String new_pw) {
        this.id = id;
        this.new_pw = new_pw;
    }
}
