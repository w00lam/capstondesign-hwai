package com.hwai.backend.user.controller;

import com.hwai.backend.book.controller.dto.BookSaveRequestDto;
import com.hwai.backend.book.service.BookService;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.user.controller.dto.*;
import com.hwai.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private static final String LOGIN_SUCCESS_MESSAGE = "로그인 성공";
    private static final String VIEW_MY_PAGE_SUCCESS_MESSAGE = "마이페이지 조회 성공";

    private final UserService userService;
    private final BookService bookService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public Message signUp(@RequestBody JoinRequestDto joinRequestDto){
        Message message = userService.join(joinRequestDto);
        log.info(message.getMessage());
        return message;
    }

    @DeleteMapping("/withdraw/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable(value = "id") Long id) {
        Message message = userService.withdraw(id);
        log.info(message.getMessage());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto findById(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        log.info(LOGIN_SUCCESS_MESSAGE);
        return loginResponseDto;
    }

    @GetMapping("/logout/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@PathVariable(value = "id") Long id) {
        Message message = userService.logout(id);
        log.info(message.getMessage());
    }

    @GetMapping("/page/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserPageResponseDto findById(@PathVariable(value = "id") Long id){
        UserPageResponseDto userPageResponseDto = userService.page(id);
        log.info(VIEW_MY_PAGE_SUCCESS_MESSAGE);
        return userPageResponseDto;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody PwUpdateRequestDto pwUpdateRequestDto) {
        Message message = userService.updatePw(pwUpdateRequestDto);
        log.info(message.getMessage());
    }
}
