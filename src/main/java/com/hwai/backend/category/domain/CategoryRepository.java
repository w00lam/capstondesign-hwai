package com.hwai.backend.category.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByGenre(String genre);
}
