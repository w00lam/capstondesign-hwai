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
    private Long categoryId;

    @Builder
    public BookSaveRequestDto(String title, Long categoryId) {
        this.title = title;
        this.categoryId = categoryId;
    }

    public Book toEntity(Category category) {
        return Book.builder()
                .title(title)
                .category(category)
                .build();
    }
}
