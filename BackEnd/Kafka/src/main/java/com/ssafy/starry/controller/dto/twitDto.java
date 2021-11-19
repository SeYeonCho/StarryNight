package com.ssafy.starry.controller.dto;


import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class twitDto {

    String text;

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "twitDto{" +
            "text='" + text + '\'' +
            '}';
    }

    @Builder
    public twitDto(String text) {
        this.text = text;
    }
}
