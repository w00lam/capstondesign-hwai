package com.hwai.backend.book.domain;

import com.hwai.backend.user.domian.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    private LocalDateTime due_date;

    @Column(nullable = false)
    private String origin;

    private String current;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Book(Long id, String title, String genre, String origin) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.origin = origin;
    }

    public void lend(User user) {
        this.user = user;
        user.getBooks().add(this);
        this.due_date = LocalDateTime.now().plusDays(14);
        this.current = "대출중";
    }
}