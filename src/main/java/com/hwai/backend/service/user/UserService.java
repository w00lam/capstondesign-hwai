package com.hwai.backend.service.user;

import com.hwai.backend.controller.dto.*;
import com.hwai.backend.domain.message.Message;
import com.hwai.backend.domain.user.User;
import com.hwai.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private static final String SIGN_UP_SUCCESS_MESSAGE = "회원가입 성공";
    private static final String WITHDRAW_SUCCESS_MESSAGE = "회원탈퇴 성공";
    private static final String LOGIN_SUCCESS_MESSAGE = "로그인 성공";
    private static final String LOGOUT_SUCCESS_MESSAGE = "로그아웃 성공";
    private static final String USER_NOT_FOUND_MESSAGE = "해당 유저가 존재하지 않습니다.";
    private static final String EMAIL_DUPLICATION_MESSAGE = "이메일 중복입니다.";
    private static final String PASSWORD_NOT_EQUAL_MESSAGE = "비밀번호가 일치하지 않습니다.";
    private static final String USER_INFO_MESSAGE = "회원 정보 불러오기 성공";
    private static final String UPDATE_PW_MESSAGE = "비밀번호 변경 완료";
    private static final String PASSWORD_EQUAL_MESSAGE = "이전 비밀번호와 동일합니다.";

    @Transactional
    public Message join(JoinRequestDto joinRequestDto) {
        checkDuplicateEmail(joinRequestDto.getEmail());
        userRepository.save(joinRequestDto.toEntity());
        return new Message(SIGN_UP_SUCCESS_MESSAGE);
    }

    @Transactional
    public Message withdraw(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
        return new Message(WITHDRAW_SUCCESS_MESSAGE);
    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User findUser = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_MESSAGE));
        validatePassword_for_equal(loginRequestDto, findUser.getPw());
        LoginResponseDto loginResponseDto = new LoginResponseDto(findUser.getId(), findUser.getName(), findUser.isAdmin(),
                123456, new Message(LOGIN_SUCCESS_MESSAGE));
        return loginResponseDto;
    }

    @Transactional(readOnly = true)
    public Message logout(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_MESSAGE));
        return new Message(LOGOUT_SUCCESS_MESSAGE);
    }

    @Transactional(readOnly = true)
    public UserPageResponseDto page(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_MESSAGE));
        UserPageResponseDto userPageResponseDto = new UserPageResponseDto(findUser, new Message(USER_INFO_MESSAGE));
        return userPageResponseDto;
    }

    @Transactional
    public Message update(PwUpdateRequestDto pwUpdateRequestDto){
        User findUser = userRepository.findById(pwUpdateRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_MESSAGE));
        validatePassword_for_not_equal(pwUpdateRequestDto, findUser.getPw());
        findUser.update(pwUpdateRequestDto.getNew_pw());
        return new Message(UPDATE_PW_MESSAGE);
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(EMAIL_DUPLICATION_MESSAGE);
        }
    }

    private void validatePassword_for_equal(LoginRequestDto loginRequestDto, String pw) {
        if (!(loginRequestDto.getPw()).equals(pw)) {
            throw new IllegalArgumentException(PASSWORD_NOT_EQUAL_MESSAGE);
        }
    }

    private void validatePassword_for_not_equal(PwUpdateRequestDto pwUpdateRequestDto, String pw) {
        if ((pwUpdateRequestDto.getNew_pw()).equals(pw)) {
            throw new IllegalArgumentException(PASSWORD_EQUAL_MESSAGE);
        }
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_MESSAGE));
    }
}
