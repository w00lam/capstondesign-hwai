package com.hwai.backend.book.controller.dto;

import com.hwai.backend.book.domain.Book;
import com.hwai.backend.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSaveRequestDto {
    private String title;
    private Category category;

    @Builder
    public BookSaveRequestDto(String title, Category category) {
        this.title = title;
        this.category = category;
    }

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .category(category)
                .build();
    }
}
