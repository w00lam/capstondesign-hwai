package com.hwai.backend.book.controller.dto;

import com.hwai.backend.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LendAbleListResponseDto {
    private Long id;
    private String title;
    private String current;

    @Builder
    public LendAbleListResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.current = book.getCurrent();
    }
}
