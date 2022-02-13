package com.hwai.backend.category.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM category c GROUP_CONCAT(c.shelf) GROUP BY c.shelf", nativeQuery = true)
    List<Category> viewShelf();

    boolean existsByGenre(String genre);
}
