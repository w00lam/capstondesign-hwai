package com.hwai.backend.service;

import com.hwai.backend.controller.dto.JoinRequestDto;
import com.hwai.backend.controller.dto.LoginRequestDto;
import com.hwai.backend.controller.dto.LoginResponseDto;
import com.hwai.backend.domain.message.Message;
import com.hwai.backend.domain.user.User;
import com.hwai.backend.domain.user.UserRepository;
import com.hwai.backend.service.user.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    void 회원가입_성공() {
        //given
        JoinRequestDto joinRequestDto = JoinRequestDto.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();

        //when
        Message message = userService.join(joinRequestDto);

        //then
        assertThat(message.getMessage()).isEqualTo("회원가입 성공");
    }

    @Test
    void 회원탈퇴_성공() {
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
    void 회원중복() {
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
        assertThatThrownBy(() -> userService.join(joinRequestDto)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이메일 중복입니다.");
    }

    @Test
    void 로그인_성공() {
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
                .orElseThrow(() -> new IllegalArgumentException("회원가입 실패"));
        LoginRequestDto LoginRequestDto = new LoginRequestDto("test@naver.com", "1234");

        //when
        LoginResponseDto login = userService.login(LoginRequestDto);

        //then
        assertThat(login.getId()).isEqualTo(findUser.getId());
    }

    @Test
    void 로그인_이메일_불일치() {
        //given
        User user = User.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        LoginRequestDto LoginRequestDto = new LoginRequestDto("test@navar.com", "1234");
        userRepository.save(user);

        //when
        //then
        assertThatThrownBy(() -> userService.login(LoginRequestDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 유저가 존재하지 않습니다.");
    }

    @Test
    void 로그인_비밀번호_불일치() {
        //given
        User user = User.builder()
                .email("test@naver.com")
                .name("tester")
                .birth("111111")
                .tel("010-1111-2222")
                .pw("1234")
                .admin(false)
                .build();
        LoginRequestDto LoginRequestDto = new LoginRequestDto("test@naver.com", "12346");
        userRepository.save(user);
        //when
        //then
        assertThatThrownBy(() -> userService.login(LoginRequestDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호가 일치하지 않습니다.");
    }
}