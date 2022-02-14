package com.hwai.backend.book.service;

import com.hwai.backend.book.controller.dto.BookSaveRequestDto;
import com.hwai.backend.book.controller.dto.ChecklistResponseDto;
import com.hwai.backend.book.controller.dto.LendAbleListResponseDto;
import com.hwai.backend.category.domain.Category;
import com.hwai.backend.category.domain.CategoryRepository;
import com.hwai.backend.common.exception.NotFoundException;
import com.hwai.backend.book.controller.dto.LendRequestDto;
import com.hwai.backend.book.domain.Book;
import com.hwai.backend.book.domain.BookRepository;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.user.controller.dto.MyListResponseDto;
import com.hwai.backend.user.domian.User;
import com.hwai.backend.user.domian.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private static final String BOOK_SAVE_MESSAGE = "책 저장 성공";
    private static final String LEND_BOOK_MESSAGE = "책 대출 성공";
    private static final String USER_NOT_FOUND_MESSAGE = "해당 유저가 존재하지 않습니다.";
    private static final String CATEGORY_NOT_FOUND_MESSAGE = "해당 카테고리가 존재하지 않습니다.";
    private static final String BOOK_NOT_FOUND_MESSAGE = "해당 책이 존재하지 않습니다.";

    @Transactional
    public Message save(BookSaveRequestDto bookSaveRequestDto) {
        Category category = categoryRepository.findById(bookSaveRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_MESSAGE));
        Book book = bookSaveRequestDto.toEntity(category);
        bookRepository.save(book);
        return new Message(BOOK_SAVE_MESSAGE);
    }

    @Transactional
    public Message lend(LendRequestDto lendRequestDto) {
        User findUser = userRepository.findById(lendRequestDto.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE));
        for(Long bookId : lendRequestDto.getBookIdList()) {
            Book findBook = bookRepository.findById(bookId)
                    .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_MESSAGE));
            findBook.lend(findUser);
        }
        return new Message(LEND_BOOK_MESSAGE);
    }

    @Transactional(readOnly = true)
    public List<ChecklistResponseDto> findCheck() {
        return bookRepository.findCheck().stream()
                .map(ChecklistResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LendAbleListResponseDto> findLendAble() {
        return bookRepository.findLendAble().stream()
                .map(LendAbleListResponseDto::new)
                .collect(Collectors.toList());
    }
}