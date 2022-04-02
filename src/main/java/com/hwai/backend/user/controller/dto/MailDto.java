package com.hwai.backend.user.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MailDto {
    private String address;
    private String title;
    private String message;
}