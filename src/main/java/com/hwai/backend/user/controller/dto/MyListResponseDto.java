package com.hwai.backend.user.controller.dto;

import com.hwai.backend.book.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MyListResponseDto {
    private String title;
    private LocalDate due_date;
    private String shelf;

    public MyListResponseDto(Book book) {
        this.title = book.getTitle();
        this.due_date = book.getDue_date();
        this.shelf = book.getCategory().getShelf();
    }
}
