package com.hwai.backend.book.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class LendRequestDto {
    private Long userId;
    private List<Long> bookIdList = new ArrayList<>();

    @Builder
    public LendRequestDto(Long userId, List<Long> bookIdList) {
        this.userId = userId;
        this.bookIdList = bookIdList;
    }
}
