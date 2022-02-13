package com.hwai.backend.service;

import com.hwai.backend.category.controller.dto.AddRequestDto;
import com.hwai.backend.category.domain.Category;
import com.hwai.backend.category.domain.CategoryRepository;
import com.hwai.backend.category.service.CategoryService;
import com.hwai.backend.common.message.Message;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @After
    public void cleanUp() {
        categoryRepository.deleteAll();
    }

    @Test
    public void 카테고리_추가() {
        //given
        AddRequestDto addRequestDto = AddRequestDto.builder()
                .genre("genre")
                .shelf("shelf")
                .build();

        //when
        Message message = categoryService.add(addRequestDto);

        //then
        assertThat(message.getMessage()).isEqualTo("카테고리 저장 성공");
    }
}
