package com.hwai.backend.domain.message;

import lombok.Getter;

@Getter
public class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }
}
