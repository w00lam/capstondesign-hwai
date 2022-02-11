package com.hwai.backend.user.service;

import com.hwai.backend.common.exception.BadRequestException;
import com.hwai.backend.common.exception.NotFoundException;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.user.controller.dto.*;
import com.hwai.backend.user.domian.User;
import com.hwai.backend.user.domian.UserRepository;
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
        User user = joinRequestDto.toEntity();
        userRepository.save(user);
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
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE));
        validatePasswordForEqual(loginRequestDto, findUser.getPw());
        LoginResponseDto loginResponseDto = new LoginResponseDto(findUser.getId(), findUser.getName(), findUser.isAdmin(),
                123456, new Message(LOGIN_SUCCESS_MESSAGE));
        return loginResponseDto;
    }

    @Transactional(readOnly = true)
    public Message logout(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE));
        return new Message(LOGOUT_SUCCESS_MESSAGE);
    }

    @Transactional(readOnly = true)
    public UserPageResponseDto page(Long id) {
        User user = findUserById(id);
        UserPageResponseDto userPageResponseDto = new UserPageResponseDto(user, new Message(USER_INFO_MESSAGE));
        return userPageResponseDto;
    }

    @Transactional
    public Message updatePw(PwUpdateRequestDto pwUpdateRequestDto) {
        User user = userRepository.findById(pwUpdateRequestDto.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE));
        validatePasswordForNotEqual(pwUpdateRequestDto, user.getPw());
        user.updatePw(pwUpdateRequestDto);
        return new Message(UPDATE_PW_MESSAGE);
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException(EMAIL_DUPLICATION_MESSAGE);
        }
    }

    private void validatePasswordForEqual(LoginRequestDto loginRequestDto, String pw) {
        if (!(loginRequestDto.getPw()).equals(pw)) {
            throw new BadRequestException(PASSWORD_NOT_EQUAL_MESSAGE);
        }
    }

    private void validatePasswordForNotEqual(PwUpdateRequestDto pwUpdateRequestDto, String pw) {
        if ((pwUpdateRequestDto.getNew_pw()).equals(pw)) {
            throw new BadRequestException(PASSWORD_EQUAL_MESSAGE);
        }
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_MESSAGE));
    }
}
