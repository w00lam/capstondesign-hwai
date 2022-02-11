package com.hwai.backend.book.controller.dto;

import com.hwai.backend.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSaveRequestDto {

    private String title;
    private String genre;

    @Builder
    public BookSaveRequestDto(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .genre(genre)
                .build();
    }
}
