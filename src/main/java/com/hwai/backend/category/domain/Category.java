package com.hwai.backend.category.domain;

import com.hwai.backend.book.domain.Book;
import com.hwai.backend.category.controller.dto.ChangeRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String shelf;

    @Builder
    public Category(String genre, String shelf) {
        this.genre = genre;
        this.shelf = shelf;
    }

    public void change(ChangeRequestDto changeRequestDto) {
        this.shelf = changeRequestDto.getShelf();
    }
}
