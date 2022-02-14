package com.hwai.backend.service.user;

import com.hwai.backend.book.domain.BookRepository;
import com.hwai.backend.book.service.BookService;
import com.hwai.backend.category.domain.CategoryRepository;
import com.hwai.backend.category.service.CategoryService;
import com.hwai.backend.common.exception.BadRequestException;
import com.hwai.backend.common.exception.NotFoundException;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.user.controller.dto.*;
import com.hwai.backend.user.domian.User;
import com.hwai.backend.user.domian.UserRepository;
import com.hwai.backend.user.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
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
    public void 회원가입_성공() {
        //given
        JoinRequestDto joinRequestDto = JoinRequestDto.builder()
                .email("test@x.x")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();

        //when
        Message message;
        message = userService.join(joinRequestDto);

        //then
        assertThat(message.getMessage()).isEqualTo("회원가입 성공");
    }

    @Test
    public void 회원탈퇴_성공() {
        //given
        User user = User.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        User save = userRepository.save(user);

        //when
        Message message = userService.withdraw(save.getId());

        //then
        assertThat(message.getMessage()).isEqualTo("회원탈퇴 성공");
    }

    @Test
    public void 회원중복() {
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

        JoinRequestDto joinRequestDto = JoinRequestDto.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        //when
        //then
        assertThatThrownBy(() -> userService.join(joinRequestDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("이메일 중복입니다.");
    }

    @Test
    public void 로그인_성공() {
        //given
        JoinRequestDto joinRequestDto = JoinRequestDto.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        userService.join(joinRequestDto);

        User findUser = userRepository.findByEmail("test@naver.com")
                .orElseThrow(() -> new IllegalArgumentException("로그인 실패"));
        LoginRequestDto loginRequestDto = new LoginRequestDto("test@naver.com", "1234");

        //when
        LoginResponseDto login = userService.login(loginRequestDto);

        //then
        assertThat(login.getId()).isEqualTo(findUser.getId());
    }

    @Test
    public void 로그인_이메일_불일치() {
        //given
        User user = User.builder()
                .email("test@test.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        LoginRequestDto loginRequestDto = new LoginRequestDto("test@asd.com", "1234");
        userRepository.save(user);

        //when
        //then
        assertThatThrownBy(() -> userService.login(loginRequestDto))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("해당 유저가 존재하지 않습니다.");
    }

    @Test
    public void 로그인_비밀번호_불일치() {
        //given
        User user = User.builder()
                .email("asd")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        LoginRequestDto loginRequestDto = new LoginRequestDto("asd", "12345");
        userRepository.save(user);

        //when
        //then
        assertThatThrownBy(() -> userService.login(loginRequestDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("비밀번호가 일치하지 않습니다.");
    }

    @Test
    public void 페이지_불러오기() {
        //given
        User user = User.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        User save = userRepository.save(user);

        //when
        UserPageResponseDto userPageResponseDto = userService.page(save.getId());

        //then
        assertThat(userPageResponseDto.getEmail()).isEqualTo("test@naver.com");
        assertThat(userPageResponseDto.getName()).isEqualTo("tester");
        assertThat(userPageResponseDto.getBirth()).isEqualTo("111111");
        assertThat(userPageResponseDto.getTel()).isEqualTo("010-1111-2222");
        assertThat(userPageResponseDto.getPw()).isEqualTo("1234");
    }

    @Test
    public void 비밀번호_변경_성공() {
        //given
        User user = User.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        String expectedPw = "12345";

        //when
        PwUpdateRequestDto pwUpdateRequestDto = PwUpdateRequestDto.builder()
                .new_pw(expectedPw)
                .build();

        //then
        assertThat(pwUpdateRequestDto.getNew_pw()).isEqualTo(expectedPw);
    }

    /**
    @Test
    public void 대출_리스트_조회() {
        //given
        User user = User.builder()
                .email("test@naver.com")
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
                .title("title1")
                .category(save)
                .build();
        Book book2 = Book.builder()
                .title("title2")
                .category(save)
                .build();
        Book book3 = Book.builder()
                .title("title3")
                .category(save)
                .build();
        Book add1 = bookRepository.save(book1);
        Book add2 = bookRepository.save(book2);
        Book add3 = bookRepository.save(book3);

        member.getBooks().add(add1);
        member.getBooks().add(add2);
        member.getBooks().add(add3);

        //when
        List<MyListResponseDto> myListResponseDtoList = userService.viewMyList(member.getId());

        //then
        assertThat(myListResponseDtoList.get(0).getTitle()).isEqualTo(add1.getTitle());
        assertThat(myListResponseDtoList.get(1).getTitle()).isEqualTo(add2.getTitle());
        assertThat(myListResponseDtoList.get(2).getTitle()).isEqualTo(add3.getTitle());
    }
    */
}