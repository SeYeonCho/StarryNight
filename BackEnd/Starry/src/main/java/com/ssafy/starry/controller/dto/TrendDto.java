package com.ssafy.starry.controller.dto;

import com.ssafy.starry.common.utils.rss.FeedMessage;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TrendDto {

    List<FeedMessage> keywords;

    @Builder
    public TrendDto(List<FeedMessage> keywords) {
        this.keywords = keywords;
    }


}
