package com.hwai.backend.user.domian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE USER u SET u.pw = :newPw WHERE u.email = :userEmail", nativeQuery = true)
    int setpw(String newPw, String userEmail);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
