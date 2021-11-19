package com.ssafy.starry.api.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnumViewController {

    @GetMapping("/docs/commonResponse")
    public Map<String, String> commonResponse() {

        Map<String, String> commonResponse = new HashMap<>();
        commonResponse.put("statusCode", "HTTP STATUS");
        commonResponse.put("message", "메시지");
        commonResponse.put("data", "데이터");

        return commonResponse;
    }

    @GetMapping("/docs/apiResponseCode")
    public Map<String, String> apiResponseCode() {

        Map<String, String> commonResponse = new HashMap<>();
        commonResponse.put("VALIDATION_FAILED", "유효성 조건을 만족하지 못한 경우 발생하는 에러입니다.");
        commonResponse.put("NOT_VALID_PARAM", "파라미터가 유효하지 않을 때 발생하는 에러입니다.");
        commonResponse.put("OK", "요청이 성공하였습니다.");

        return commonResponse;
    }

}
