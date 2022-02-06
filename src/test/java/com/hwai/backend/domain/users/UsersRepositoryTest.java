package com.hwai.backend.domain.users;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @After
    public void cleanup() {usersRepository.deleteAll();}

    @Test
    public void 사용자_회원가입() {
        //given
        usersRepository.save(Users.builder()
                .name("이름")
                .birth("생일")
                .tel("번호")
                .email("이메일")
                .pw("비번")
                .admin(false)
                .build());

        //when
        List<Users> postsList = usersRepository.findAll();

        //then
        Users posts = postsList.get(0);
    }
}
