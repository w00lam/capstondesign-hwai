package com.hwai.backend.service.user;

import com.hwai.backend.domain.user.UserRepository;
import com.hwai.backend.web.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserJoinRequestDto joinRequestDto) {
        return userRepository.save(joinRequestDto.toEntity()).getId();
    }
}
