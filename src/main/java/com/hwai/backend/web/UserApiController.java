package com.hwai.backend.web;

import com.hwai.backend.service.user.UserService;
import com.hwai.backend.web.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public Long signUp(@RequestBody UserJoinRequestDto requestDto){

        return userService.save(requestDto);
    }
}
