package com.hwai.backend.category.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeRequestDto {
    private String genre;
    private String shelf;

    @Builder
    public ChangeRequestDto(String genre, String shelf) {
        this.genre = genre;
        this.shelf = shelf;
    }
}
