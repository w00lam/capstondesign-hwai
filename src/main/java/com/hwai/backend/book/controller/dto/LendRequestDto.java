package com.hwai.backend.book.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class LendRequestDto {
    private Long userId;
    private List<Long> bookId = new ArrayList<>();

    @Builder
    public LendRequestDto(Long userId, List<Long> bookId) {
        this.userId = userId;
        this.bookId.addAll(bookId);
    }
}
