package com.hwai.backend.user.domian;

import com.hwai.backend.book.domain.Book;
import com.hwai.backend.user.controller.dto.PwUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "user")
    @Column
    private List<Book> books = new ArrayList<>();

    @Builder
    public User(String name, String birth, String tel, String email, String pw, boolean admin) {
        this.name = name;
        this.birth = birth;
        this.tel = tel;
        this.email = email;
        this.pw = pw;
        this.admin = admin;
    }

    public void updatePw(PwUpdateRequestDto pwUpdateRequestDto){
        this.pw = pwUpdateRequestDto.getNew_pw();
    }
}
