package com.hwai.backend.book.domain;

import com.hwai.backend.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book b WHERE user_id==id ORDER BY b.due_date ASC", nativeQuery = true)
    List<Book> findByUserId(@Param("id") Long id);
}
