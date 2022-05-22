package com.hwai.backend.book.service;

import com.hwai.backend.book.controller.dto.*;
import com.hwai.backend.category.domain.Category;
import com.hwai.backend.category.domain.CategoryRepository;
import com.hwai.backend.common.exception.BadRequestException;
import com.hwai.backend.common.exception.NotFoundException;
import com.hwai.backend.book.domain.Book;
import com.hwai.backend.book.domain.BookRepository;
import com.hwai.backend.common.message.Message;
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
    private static final String STACK_IS_FULL = "대출가능한 권수를 초과했습니다.";
    private static final String RETURN_SUCCESS_MESSAGE = "책 반납 성공";
    private static final String DISCORD_ID_MESSAGE = "책 반납 실패";

    @Transactional
    public Message save(BookSaveRequestDto bookSaveRequestDto) {
        Category category = findCategoryById(bookSaveRequestDto.getCategoryId());
        Book book = bookSaveRequestDto.toEntity(category);
        bookRepository.save(book);
        return new Message(BOOK_SAVE_MESSAGE);
    }

    @Transactional
    public Message lend(LendRequestDto lendRequestDto) {
        User user = findUserById(lendRequestDto.getUserId());
        checkStack(user);
        Book book = findBookById(lendRequestDto.getBookId());
        book.lend(user);
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

    @Transactional
    public Message returnBook(ReturnBookRequestDto returnBookRequestDto) {
        Book book = findBookById(Long.parseLong(returnBookRequestDto.getBookId()));
        Category category = book.getCategory();
        User user = book.getUser();
        if (returnBookRequestDto.getShelfId() == category.getShelf()) {
            book.returnBook(user, returnBookRequestDto.getShelfId());
            bookRepository.returnBook(book.getId());
            return new Message(RETURN_SUCCESS_MESSAGE);
        }
        else{
            return new Message(DISCORD_ID_MESSAGE);
        }
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    private Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_MESSAGE));
    }

    private void checkStack(User user) {
        if (user.getBooks().size() >= 3) {
            throw new BadRequestException(STACK_IS_FULL);
        }
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_MESSAGE));
    }
}