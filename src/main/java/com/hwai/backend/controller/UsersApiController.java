package com.hwai.backend.controller;

import com.hwai.backend.controller.dto.LoginRequestDto;
import com.hwai.backend.controller.dto.LoginResponseDto;
import com.hwai.backend.controller.dto.UsersJoinRequestDto;
import com.hwai.backend.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public Long signUp(@RequestBody UsersJoinRequestDto requestDto){
        return usersService.signUp(requestDto);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto findById(@RequestBody LoginRequestDto loginRequestDto) {
        return usersService.login(loginRequestDto);
    }
}
