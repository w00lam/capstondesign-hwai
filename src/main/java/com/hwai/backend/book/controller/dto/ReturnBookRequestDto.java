package com.hwai.backend.book.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ReturnBookRequestDto {
    private String shelfId;
    private String bookId;

    @Builder
    public ReturnBookRequestDto(String shelfId, String bookId) {
        this.shelfId = shelfId;
        this.bookId = bookId;
    }
}
