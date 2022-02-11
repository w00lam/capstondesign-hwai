package com.hwai.backend.book.controller;

import com.hwai.backend.book.controller.dto.BookSaveRequestDto;
import com.hwai.backend.book.controller.dto.LendRequestDto;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping("/api/v1/books/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody BookSaveRequestDto bookSaveRequestDto) {
        Message message = bookService.save(bookSaveRequestDto);
        log.info(message.getMessage());
        return message;
    }

    @PutMapping("/lend")
    @ResponseStatus(HttpStatus.OK)
    public void lend(@RequestBody LendRequestDto lendRequestDto) {
        Message message = bookService.lend(lendRequestDto);
        log.info(message.getMessage());
    }
}
