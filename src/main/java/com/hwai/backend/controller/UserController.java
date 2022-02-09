package com.hwai.backend.controller;

import com.hwai.backend.controller.dto.LoginRequestDto;
import com.hwai.backend.controller.dto.LoginResponseDto;
import com.hwai.backend.controller.dto.JoinRequestDto;
import com.hwai.backend.domain.message.Message;
import com.hwai.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final String LOGIN_SUCCESS_MESSAGE = "로그인 성공";

    private final UserService userService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public Message joinUser(@RequestBody JoinRequestDto joinRequestDto){
        Message message = userService.join(joinRequestDto);
        log.info(message.getMessage());
        return message;
    }

    @DeleteMapping("/withdraw/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable(value = "id") Long id) {
        Message withdrawSuccess = userService.withdraw(id);
        log.info(withdrawSuccess.getMessage());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto findById(@RequestBody LoginRequestDto loginRequestDto) {
        log.info(LOGIN_SUCCESS_MESSAGE);
        return userService.login(loginRequestDto);
    }

    @GetMapping("/logout/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@PathVariable(value = "id") Long id) {
        Message logoutSuccess = userService.logout(id);
        log.info(logoutSuccess.getMessage());
    }
}
