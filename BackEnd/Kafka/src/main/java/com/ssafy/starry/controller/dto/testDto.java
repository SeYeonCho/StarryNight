package com.ssafy.starry.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class testDto {

    String key1;
    String key2;

    @Builder
    public testDto(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }
}
