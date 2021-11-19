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
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlowVO {

    String startDate;
    String endDate;
    String timeUnit;
    List<Result> results;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class Result {

        String title;
        List<String> keywords;
        List<Data> data;

    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class Data {

        String period;
        double ratio;
    }

    @Override
    public String toString() {
        return "SearchFlowDto{" +
            "startDate='" + startDate + '\'' +
            ", endDate='" + endDate + '\'' +
            ", timeUnit='" + timeUnit + '\'' +
            ", results=" + results +
            '}';
    }
}
