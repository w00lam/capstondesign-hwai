package com.hwai.backend.service;

import com.hwai.backend.book.controller.dto.LendRequestDto;
import com.hwai.backend.book.domain.Book;
import com.hwai.backend.book.domain.BookRepository;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.user.domian.User;
import com.hwai.backend.user.domian.UserRepository;
import com.hwai.backend.book.service.BookService;
import com.hwai.backend.user.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void 책_대출_성공() {
        //given
        User user = User.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        userRepository.save(user);
        Book book1 = Book.builder()
                .title("title")
                .genre("genre")
                .build();
        bookRepository.save(book1);
        Book book2 = Book.builder()
                .title("title")
                .genre("genre")
                .build();
        bookRepository.save(book2);
        Book book3 = Book.builder()
                .title("title")
                .genre("genre")
                .build();
        bookRepository.save(book3);
        List<Long> list = new ArrayList<>();
        list.add(book1.getId());
        list.add(book2.getId());
        list.add(book3.getId());

        //when
        LendRequestDto lendRequestDto = new LendRequestDto(user.getId(), list);
        Message message = bookService.lend(lendRequestDto);

        //then
        assertThat(message.getMessage()).isEqualTo("책 대출 성공");
    }
}
