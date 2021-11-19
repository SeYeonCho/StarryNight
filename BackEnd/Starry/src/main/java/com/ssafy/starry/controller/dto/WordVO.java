package com.ssafy.starry.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class WordVO {

    List<WordApiResponse> keywordList;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class WordApiResponse {

        private String relKeyword;
        private String monthlyPcQcCnt;
        private String monthlyMobileQcCnt;
        private String monthlyAvePcClkCnt;
        private String monthlyAveMobileClkCnt;
        private String monthlyAvePcCtr;
        private String monthlyAveMobileCtr;
        private String plAvgDepth;
        private String compIdx;

    }

}
