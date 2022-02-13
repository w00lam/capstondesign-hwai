package com.hwai.backend.category.controller.dto;

import com.hwai.backend.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddRequestDto {
    private String genre;
    private String shelf;

    @Builder
    public AddRequestDto(String genre, String shelf) {
        this.genre = genre;
        this.shelf = shelf;
    }

    public Category ToEntity() {
        return Category.builder()
                .genre(genre)
                .shelf(shelf)
                .build();
    }
}
