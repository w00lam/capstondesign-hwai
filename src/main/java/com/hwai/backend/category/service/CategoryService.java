package com.hwai.backend.category.service;

import com.hwai.backend.category.controller.dto.AddRequestDto;
import com.hwai.backend.category.controller.dto.ShelfResponseDto;
import com.hwai.backend.category.domain.Category;
import com.hwai.backend.category.domain.CategoryRepository;
import com.hwai.backend.common.exception.BadRequestException;
import com.hwai.backend.common.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private static final String GENRE_DUPLICATION_MESSAGE = "장르 중복입니다.";
    private static final String CATEGORY_SAVE_SUCCESS_MESSAGE = "카테고리 저장 성공";

    @Transactional
    public Message add(AddRequestDto addRequestDto) {
        checkDuplicateGenre(addRequestDto.getGenre());
        Category category = addRequestDto.ToEntity();
        categoryRepository.save(category);
        return new Message(CATEGORY_SAVE_SUCCESS_MESSAGE);
    }

    @Transactional(readOnly = true)
    public List<ShelfResponseDto> viewShelf() {
        return categoryRepository.viewShelf().stream()
                .map(ShelfResponseDto::new)
                .collect(Collectors.toList());
    }

    private void checkDuplicateGenre(String genre) {
        if (categoryRepository.existsByGenre(genre)) {
            throw new BadRequestException(GENRE_DUPLICATION_MESSAGE);
        }
    }
}
