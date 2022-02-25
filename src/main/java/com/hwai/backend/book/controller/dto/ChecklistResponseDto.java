package com.hwai.backend.book.controller.dto;

import com.hwai.backend.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChecklistResponseDto {
    private String title;
    private LocalDate due_date;
    private String current;
    private String shelf;

    @Builder
    public ChecklistResponseDto(Book book) {
        this.title = book.getTitle();
        this.due_date = book.getDue_date();
        this.current = book.getCurrent();
        this.shelf = book.getCategory().getShelf();
    }
}
