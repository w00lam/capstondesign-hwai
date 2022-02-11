package com.hwai.backend.book.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class LendRequestDto {
    private Long user_id;
    public List<Long> book_id = new ArrayList<>();

    @Builder
    public LendRequestDto(Long user_id, List<Long> book_id) {
        this.user_id = user_id;
        for(Long id : book_id){
            this.book_id.add(id);
        }
    }
}
