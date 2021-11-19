package com.ssafy.starry.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private String Error;
    private String message;

    @Builder
    ExceptionResponse(String Error, String message) {
        this.Error = Error;
        this.message = message;
    }
}