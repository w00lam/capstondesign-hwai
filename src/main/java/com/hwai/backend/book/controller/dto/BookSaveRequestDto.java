package com.hwai.backend.book.controller.dto;

import com.hwai.backend.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSaveRequestDto {

    private Long id;
    private String title;
    private String genre;

    @Builder
    public BookSaveRequestDto(Long id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public Book toEntity() {
        return Book.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .build();
    }
}
