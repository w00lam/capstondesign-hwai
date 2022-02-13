package com.hwai.backend.book.domain;

import com.hwai.backend.category.domain.Category;
import com.hwai.backend.user.domian.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private LocalDate due_date;

    private String current;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Book(String title, Category category) {
        this.title = title;
    }

    public void lend(User user) {
        this.user = user;
        user.getBooks().add(this);
        this.due_date = LocalDate.now().plusDays(14);
        this.current = "대출중";
    }
}