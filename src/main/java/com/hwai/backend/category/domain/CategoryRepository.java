package com.hwai.backend.category.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByGenre(String genre);

    boolean existsByGenre(String genre);
}
