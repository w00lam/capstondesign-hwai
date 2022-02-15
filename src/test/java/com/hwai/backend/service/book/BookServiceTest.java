package com.hwai.backend.service.book;

import com.hwai.backend.book.controller.dto.BookSaveRequestDto;
import com.hwai.backend.book.controller.dto.LendRequestDto;
import com.hwai.backend.book.domain.Book;
import com.hwai.backend.book.domain.BookRepository;
import com.hwai.backend.category.domain.Category;
import com.hwai.backend.category.domain.CategoryRepository;
import com.hwai.backend.category.service.CategoryService;
import com.hwai.backend.common.exception.NotFoundException;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.user.domian.User;
import com.hwai.backend.user.domian.UserRepository;
import com.hwai.backend.book.service.BookService;
import com.hwai.backend.user.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
        bookRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void 책_저장_성공() {
        //given
        Category category = Category.builder()
                .genre("genre")
                .shelf("1")
                .build();
        Category save = categoryRepository.save(category);

        Book book = Book.builder()
                .title("title")
                .category(save)
                .build();
        Book add = bookRepository.save(book);

        //when
        BookSaveRequestDto bookSaveRequestDto = new BookSaveRequestDto("genre", save.getId());
        Message message = bookService.save(bookSaveRequestDto);

        //then
        assertThat(message.getMessage()).isEqualTo("책 저장 성공");
    }

    @Test
    public void 책_대출_성공() {
        //given
        User user = User.builder()
                .email("test@test.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        User member = userRepository.save(user);

        Category category = Category.builder()
                .genre("genre")
                .shelf("1")
                .build();
        Category save = categoryRepository.save(category);

        Book book1 = Book.builder()
                .title("title")
                .category(save)
                .build();
        Book book2 = Book.builder()
                .title("title2")
                .category(save)
                .build();
        Book add1 = bookRepository.save(book1);
        Book add2 = bookRepository.save(book2);

        List<Book> bookList = new ArrayList<>();

        bookList.add(add1);
        bookList.add(add2);

        //when
        LendRequestDto lendRequestDto = new LendRequestDto(member.getId(), add1.getId());
        Message message = bookService.lend(lendRequestDto);

        //then
        assertThat(message.getMessage()).isEqualTo("책 대출 성공");
    }
}
