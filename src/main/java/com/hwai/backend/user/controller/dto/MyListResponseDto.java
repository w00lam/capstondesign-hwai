package com.hwai.backend.user.controller.dto;

import com.hwai.backend.book.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyListResponseDto {
    private String title;
    private LocalDateTime due_date;
    private String origin;

    public MyListResponseDto(Book book) {
        this.title = book.getTitle();
        this.due_date = book.getDue_date();
        this.origin = book.getOrigin();
    }
}
