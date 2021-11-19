package com.ssafy.starry.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.ssafy.starry.common.utils.DataLabHttp;
import com.ssafy.starry.common.utils.RedisUtil;
import com.ssafy.starry.common.utils.RestClient;
import com.ssafy.starry.controller.dto.SearchFlowVO;
import com.ssafy.starry.controller.dto.WordVO;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CachedWordService {

    static String RelKwdPath = "/keywordstool";
    static String DataLabPath = "https://openapi.naver.com/v1/datalab/search";

    @Cacheable(key = "#hintKeywords", value = "list")
    public WordVO list(RestClient rest, long customerId, String hintKeywords)
        throws Exception {
        HttpResponse<String> response =
            rest.get(RelKwdPath, customerId)
                .queryString("showDetail", 1)
                .queryString("hintKeywords", hintKeywords)
                .asString();
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper
            .readValue(responseBody, WordVO.class);
    }

    @Cacheable(key = "#mainWord", value = "trend")
    public SearchFlowVO getDataTrend(String mainWord, String clientId,
        String clientSecret) throws JsonProcessingException {
        log.info("mainWord : " + mainWord);
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        requestHeaders.put("Content-Type", "application/json;charset=UTF-8");
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("startDate", "2021-01-01");
        requestBody.addProperty("endDate", "2021-09-30");
        requestBody.addProperty("timeUnit", "month");
        JsonArray keywordList = new JsonArray();
        // MainKeyword ADD
        keywordList.add(mainWord);
        JsonObject keywordGroup = new JsonObject();
        keywordGroup.addProperty("groupName", mainWord);
        keywordGroup.add("keywords", keywordList);
        JsonArray keywordGroups = new JsonArray();
        keywordGroups.add(keywordGroup);
        requestBody.add("keywordGroups", keywordGroups);
        String request = requestBody.toString();
        log.info("request Body : " + request);
        String responseBody = DataLabHttp.post(DataLabPath, requestHeaders, request);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, SearchFlowVO.class);
    }
}
