package com.hwai.backend.category.controller;

import com.hwai.backend.category.controller.dto.AddRequestDto;
import com.hwai.backend.category.controller.dto.ShelfResponseDto;
import com.hwai.backend.category.service.CategoryService;
import com.hwai.backend.common.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@RestController
public class CategoryController {
    private static final String VIEW_SHELF_SUCCESS_MESSAGE = "책장 조회 성공";

    private final CategoryService categoryService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Message signUp(@RequestBody AddRequestDto addRequestDto){
        Message message = categoryService.add(addRequestDto);
        log.info(message.getMessage());
        return message;
    }

    @GetMapping("/shelf")
    @ResponseStatus(HttpStatus.OK)
    public List<ShelfResponseDto> viewShelf() {
        List<ShelfResponseDto> shelfResponseDtoList = categoryService.viewShelf();
        log.info(VIEW_SHELF_SUCCESS_MESSAGE);
        return shelfResponseDtoList;
    }
}
