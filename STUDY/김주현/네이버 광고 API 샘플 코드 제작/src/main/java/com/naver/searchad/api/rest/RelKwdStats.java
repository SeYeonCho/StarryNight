package com.naver.searchad.api.rest;

import com.mashape.unirest.http.HttpResponse;
import com.naver.searchad.api.model.Keyword;
import com.naver.searchad.api.model.RelKwdStat;
import com.naver.searchad.api.util.RestClient;

public class RelKwdStats {

    static String RelKwdPath = "/keywordstool";

    public static RelKwdStat list(RestClient rest, long customerId, String hintKeywords)
        throws Exception {
        HttpResponse<String> response =
            rest.get(RelKwdPath, customerId)
                .queryString("hintKeywords", hintKeywords)
                .asString();
        return rest.asObject(response, RelKwdStat.class);
    }

}
