package com.hwai.backend.category.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String genre;

    @Column
    private String shelf;

    @Builder
    public Category(String genre, String shelf) {
        this.genre = genre;
        this.shelf = shelf;
    }
}
