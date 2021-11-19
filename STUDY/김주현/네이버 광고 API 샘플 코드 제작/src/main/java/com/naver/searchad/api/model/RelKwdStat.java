package com.naver.searchad.api.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RelKwdStat {

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
