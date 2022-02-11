package com.hwai.backend.controller.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class LendRequestDto {
    private Long user_id;
    private List<Long> book_id;

    @Builder
    public LendRequestDto(Long user_id, List<Long> book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }
}
