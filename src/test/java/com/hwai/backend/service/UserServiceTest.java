package com.hwai.backend.service;

import com.hwai.backend.common.exception.BadRequestException;
import com.hwai.backend.common.exception.NotEqualsException;
import com.hwai.backend.common.exception.NotFoundException;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.user.controller.dto.*;
import com.hwai.backend.user.domian.User;
import com.hwai.backend.user.domian.UserRepository;
import com.hwai.backend.user.service.UserService;
import org.junit.After;
import org.junit.Test;
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
    private UserService userService;

    @After
    public void cleanup() {
        userRepository.deleteAll();
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
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        LoginRequestDto loginRequestDto = new LoginRequestDto("test@navar.com", "1234");
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
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        LoginRequestDto loginRequestDto = new LoginRequestDto("test@naver.com", "12345");
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
}