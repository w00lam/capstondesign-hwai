package com.hwai.backend.book.controller.dto;

import com.hwai.backend.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class LendRequestDto {
    private Long userId;
    private Long bookId;

    @Builder
    public LendRequestDto(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
