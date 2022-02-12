package com.hwai.backend.book.controller.dto;

import com.hwai.backend.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class LendRequestDto {
    private Long user_id;
    private List<Book> bookList;

    @Builder
    public LendRequestDto(Long user_id, List<Book> bookList) {
        this.user_id = user_id;
        this.bookList = bookList;
    }
}
