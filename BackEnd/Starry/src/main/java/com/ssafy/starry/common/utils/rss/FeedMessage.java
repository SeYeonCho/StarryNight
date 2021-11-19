package com.ssafy.starry.common.utils.rss;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedMessage {

    /**
     * 아이템 이름
     */
    String title;

    /**
     * 아이템 설명
     */
    String description;

    /**
     * 아이템 link url
     */
    String link;

    /**
     * 작성시간
     */
    String pubdate;

    String traffic;
    String news_title;
    String news_url;
    String news_source;


}

