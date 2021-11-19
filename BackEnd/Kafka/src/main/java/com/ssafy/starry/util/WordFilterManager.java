package com.ssafy.starry.util;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WordFilterManager {

    private final RedisUtil redisUtil;
    private Set<String> searchWords = new HashSet<>();


    public Set<String> getSearchWords() {
        return searchWords;
    }

    public void renewalWords() {
        this.searchWords = redisUtil.get("searchWords").stream().map(object -> Objects
            .toString(object, null)).collect(Collectors.toSet());
    }
}
