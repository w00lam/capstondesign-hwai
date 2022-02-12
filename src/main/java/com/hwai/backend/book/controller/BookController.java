package com.hwai.backend.book.controller;

import com.hwai.backend.book.controller.dto.BookSaveRequestDto;
import com.hwai.backend.book.controller.dto.ChecklistResponseDto;
import com.hwai.backend.book.controller.dto.LendRequestDto;
import com.hwai.backend.common.message.Message;
import com.hwai.backend.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookController {
    private static final String VIEW_CHECK_LIST_SUCCESS_MESSAGE = "체크리스트 불러오기 성공";

    private final BookService bookService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody BookSaveRequestDto bookSaveRequestDto) {
        Message message = bookService.save(bookSaveRequestDto);
        log.info(message.getMessage());
        return message;
    }

    @PatchMapping("/lend")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void lend(@RequestBody LendRequestDto lendRequestDto) {
        Message message = bookService.lend(lendRequestDto);
        log.info(message.getMessage());
    }

    @GetMapping("/checklist")
    @ResponseStatus(HttpStatus.OK)
    public List<ChecklistResponseDto> viewCheckList() {
        List<ChecklistResponseDto> checklistResponseDtoList = bookService.findCheck();
        log.info(VIEW_CHECK_LIST_SUCCESS_MESSAGE);
        return checklistResponseDtoList;
    }
}
