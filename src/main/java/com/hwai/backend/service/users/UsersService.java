package com.hwai.backend.service.users;

import com.hwai.backend.controller.dto.LoginRequestDto;
import com.hwai.backend.controller.dto.LoginResponseDto;
import com.hwai.backend.controller.dto.JoinRequestDto;
import com.hwai.backend.domain.users.Users;
import com.hwai.backend.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Transactional
    public Long join(JoinRequestDto joinRequestDto) {
        checkDuplicateEmail(joinRequestDto.getEmail());
        return usersRepository.save(joinRequestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Users findUser = usersRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일이 없습니다. email="+
                        loginRequestDto.getEmail()));
        return new LoginResponseDto(findUser.getId(), findUser.getName(), findUser.isAdmin());
    }

    private void checkDuplicateEmail(String email) {
        if (usersRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("중복 이메일입니다.");
        }
    }
}
