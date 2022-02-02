package com.hwai.backend.domain.user;

import com.hwai.backend.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String birth;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String pwd;

    @Column
    private boolean admin;

    @Builder
    public User(String name, String birth, String phone, String email, String pwd, boolean admin){
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.pwd = pwd;
        this.admin = admin;
    }
}
