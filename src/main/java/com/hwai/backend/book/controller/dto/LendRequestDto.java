package com.hwai.backend.book.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
