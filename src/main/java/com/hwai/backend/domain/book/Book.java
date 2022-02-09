package com.hwai.backend.domain.book;

import com.hwai.backend.domain.user.User;
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
    private Long book_id;

    @Column(nullable = false)
    private String title;

    private LocalDateTime due_date;

    private String current;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Book(Long book_id, String title, LocalDateTime due_date, String current) {
        this.book_id = book_id;
        this.title = title;
        this.due_date = due_date;
        this.current = current;
        setUser(user);
    }

    private void setUser(User user) {
        this.user = user;
        user.getBooklist().add(this);
    }
}
