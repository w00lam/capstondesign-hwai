package com.hwai.backend.service.category;

import com.hwai.backend.category.controller.dto.AddRequestDto;
import com.hwai.backend.category.domain.Category;
import com.hwai.backend.category.domain.CategoryRepository;
import com.hwai.backend.category.service.CategoryService;
import com.hwai.backend.common.exception.BadRequestException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @AfterEach
    void cleanUp() {
        categoryRepository.deleteAll();
    }

    @Test
    public void 카테고리_추가_실패() {
        //given
        Category category = Category.builder()
                .shelf("1")
                .genre("genre")
                .build();
        Category save = categoryRepository.save(category);

        AddRequestDto addRequestDto = AddRequestDto.builder()
                .genre("genre")
                .shelf("1")
                .build();

        //when
        //then
        assertThatThrownBy(() -> categoryService.add(addRequestDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("장르 중복입니다.");
    }
}
