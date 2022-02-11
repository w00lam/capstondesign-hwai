package com.hwai.backend.dto;

import com.hwai.backend.controller.book.dto.LendRequestDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LendRequestDtoTest {

    @Test
    public void 대출_요청() {
        //given
        Long user_id = 0l;
        List<Long> book_id = new ArrayList<>();
        book_id.add(0l);
        book_id.add(1l);
        book_id.add(2l);

        //when
        LendRequestDto lendRequestDto = new LendRequestDto(user_id, book_id);

        //then
        assertThat(lendRequestDto.getUser_id()).isEqualTo(user_id);
        assertThat(lendRequestDto.getBook_id().get(0).equals(0l));
        assertThat(lendRequestDto.getBook_id().get(1).equals(1l));
        assertThat(lendRequestDto.getBook_id().get(2).equals(2l));
    }
}
