package com.hwai.backend.category.domain;

import com.hwai.backend.book.domain.Book;
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

    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH)
    private List<Book> bookList = new ArrayList<Book>();

    @Builder
    public Category(String genre, String shelf) {
        this.genre = genre;
        this.shelf = shelf;
    }
}
