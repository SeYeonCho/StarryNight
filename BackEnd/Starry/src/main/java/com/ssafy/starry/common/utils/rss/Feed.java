package com.ssafy.starry.common.utils.rss;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Feed {

    final String title;

    final String link;

    final String description;

    final String language;

    final String copyright;

    final String pubDate;


    final List<FeedMessage> entries = new ArrayList<FeedMessage>();


    public List<FeedMessage> getMessages() {
        return entries;
    }

    @Override
    public String toString() {
        return "Feed{" +
            "title='" + title + '\'' +
            ", link='" + link + '\'' +
            ", description='" + description + '\'' +
            ", language='" + language + '\'' +
            ", copyright='" + copyright + '\'' +
            ", pubDate='" + pubDate + '\'' +
            ", entries=" + entries +
            '}';
    }
}
