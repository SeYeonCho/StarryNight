package com.ssafy.starry.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.ssafy.starry.common.utils.DataLabHttp;
import com.ssafy.starry.common.utils.PropertiesLoader;
import com.ssafy.starry.common.utils.RedisUtil;
import com.ssafy.starry.common.utils.RestClient;
import com.ssafy.starry.common.utils.rss.Feed;
import com.ssafy.starry.common.utils.rss.FeedMessage;
import com.ssafy.starry.common.utils.rss.RSSFeedParser;
import com.ssafy.starry.controller.dto.SearchDto;
import com.ssafy.starry.controller.dto.SearchFlowVO;
import com.ssafy.starry.controller.dto.TrendDto;
import com.ssafy.starry.controller.dto.WordVO;
import com.ssafy.starry.controller.dto.WordVO.WordApiResponse;
import com.ssafy.starry.exception.externalApi.DataTrendNullPointerException;
import com.ssafy.starry.exception.externalApi.ListNullPointerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordService {

    private final RedisUtil redisUtil;
    static String feedURL = "https://trends.google.co.kr/trends/trendingsearches/daily/rss?geo=KR";

    private final CachedWordService cachedWordService;

    public SearchDto getWordAnalysis(String word) {
        word = word.replaceAll(" ", "");
        word = word.toLowerCase();
        redisUtil.addSet("searchWords", word);
        SearchDto searchDto = null;
        WordVO words = null;
        try {
            Properties properties = PropertiesLoader.fromResource("secret.properties");
            String baseUrl = properties.getProperty("BASE_URL");
            String apiKey = properties.getProperty("API_KEY");
            String secretKey = properties.getProperty("SECRET_KEY");
            String clientId = properties.getProperty("CLIENT_ID");
            String clientSecret = properties.getProperty("CLIENTSECRET");
            long customerId = Long.parseLong(properties.getProperty("CUSTOMER_ID"));
            RestClient rest = RestClient.of(baseUrl, apiKey, secretKey);

            words = cachedWordService.list(rest, customerId, word);
            if (words == null) {
                throw new ListNullPointerException("API connection failed: LIST_NPE");
            }

//            log.info("네이버 API에서 돌려받은 WordDto : " + words.toString());
            if (words.getKeywordList().size() > 20) {
                words.setKeywordList(words.getKeywordList().subList(0, 20));
            }

            SearchFlowVO searchFlowVO = cachedWordService.getDataTrend(word,
                clientId,
                clientSecret);
            if (searchFlowVO == null) {
                throw new DataTrendNullPointerException("API connection failed: DATA_TREND_NPE");
            }
//            log.info("검색량 추이에 대한 데이터 API Return " + searchFlowVO);

            log.info("redis word 값 : " + redisUtil.get(word));
            List<String> twit = redisUtil.getTwit(word + "@twitText").stream()
                .map(object -> Objects.toString(object, null)).collect(
                    Collectors.toList());
//            System.out.println(twit);
            long mention =
                redisUtil.get(word) == null ? 0 : Long.parseLong((String) redisUtil.get(word));
            searchDto = new SearchDto(words, searchFlowVO, mention, twit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchDto;
    }


    public TrendDto getTrendKeyword() {
        RSSFeedParser parser = new RSSFeedParser(feedURL);
        Feed feed = parser.readFeed();
        return TrendDto.builder()
            .keywords(feed.getMessages())
            .build();
    }

}
