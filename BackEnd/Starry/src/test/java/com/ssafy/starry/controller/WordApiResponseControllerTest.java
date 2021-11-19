package com.ssafy.starry.controller;

import static com.ssafy.starry.ApiDocumentUtils.getDocumentRequest;
import static com.ssafy.starry.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.starry.common.utils.notification.NotificationManager;
import com.ssafy.starry.common.utils.rss.FeedMessage;
import com.ssafy.starry.controller.dto.SearchDto;
import com.ssafy.starry.controller.dto.SearchFlowVO;
import com.ssafy.starry.controller.dto.TrendDto;
import com.ssafy.starry.controller.dto.WordVO;
import com.ssafy.starry.controller.dto.WordVO.WordApiResponse;
import com.ssafy.starry.service.WordService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = WordController.class)
class WordApiResponseControllerTest {

    @MockBean
    private WordService wordService;

    @MockBean
    private NotificationManager notificationManager;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .apply(sharedHttpSession())
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }

    @DisplayName("단어 검색 - 성공")
    @Test
    public void searchWord_success() throws Exception {
        //given
        WordVO wordVO = new WordVO();
        List<WordApiResponse> wordApiResponses = new ArrayList<>();
        wordApiResponses.add(WordApiResponse.builder()
            .relKeyword("소고기")
            .monthlyPcQcCnt("12500")
            .monthlyMobileQcCnt("105000")
            .monthlyAvePcClkCnt("83.1")
            .monthlyAveMobileClkCnt("1171.5")
            .monthlyAvePcCtr("0.69")
            .monthlyAveMobileCtr("1.18")
            .plAvgDepth("15")
            .compIdx("높음")
            .build());
        wordVO.setKeywordList(wordApiResponses);
        List<Double> ratios = Arrays.asList(86.08608,
            82.08194,
            85.22112,
            82.23059,
            94.73376,
            88.21551,
            86.95837,
            100.0,
            94.45168);
        List<String> twits = new ArrayList<>();
        twits.add("트윗1");
        twits.add("트윗2");
        twits.add("트윗3");
        SearchDto searchDto = new SearchDto(wordVO, ratios, 100, twits);
        given(wordService.getWordAnalysis(any())).willReturn(searchDto);
        //when
        mockMvc.perform(get("/api/word/search")
            .param("word", "소고기")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())

            .andExpect(status().isOk())
            .andDo(document("WordApi/getAnalysisWord/success",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                    parameterWithName("word").description("검색하려는 단어")
                ),
                responseFields(
                    fieldWithPath("keywordList[].relKeyword").type(JsonFieldType.STRING)
                        .description("연관검색어")
                        .attributes(key("format")
                            .value(
                                "연관 검색어")),
                    fieldWithPath("keywordList[].monthlyPcQcCnt").type(JsonFieldType.STRING)
                        .description(
                            "월간 PC 검색 수")
                        .attributes(key("format")
                            .value(
                                "Sum of PC query counts in recent 30 days. If the query count is less than 10, you get \"<10\".")),
                    fieldWithPath("keywordList[].monthlyMobileQcCnt").type(JsonFieldType.STRING)
                        .description("월간 모바일 검색 수")
                        .attributes(key("format")
                            .value(
                                "Sum of Mobile query counts in recent 30 days. If the query count is less than 10, you get \"<10\".")),
                    fieldWithPath("keywordList[].monthlyAvePcClkCnt").type(JsonFieldType.STRING)
                        .description(
                            "월간 평균 PC 클릭 수(최근 한 달 간 사용자가 해당 키워드를 검색했을 때, 통합검색 영역에 노출된 광고가 받은 평균 클릭수)")
                        .attributes(key("format")
                            .value(
                                "Average PC click counts per keyword's ad in recent 4 weeks. With no data, you get 0.")),
                    fieldWithPath("keywordList[].monthlyAveMobileClkCnt").type(JsonFieldType.STRING)
                        .description(
                            "월간 평균 모바일 클릭 수(최근 한 달 간 사용자가 해당 키워드를 검색했을 때, 통합검색 영역에 노출된 광고가 받은 평균 클릭수)")
                        .attributes(key("format")
                            .value(
                                "recent 4 weeks. With no data, you get 0.")),
                    fieldWithPath("keywordList[].monthlyAvePcCtr").type(JsonFieldType.STRING)
                        .description(
                            "월간 평균 PC 클릭률(클릭수 / 노출수 = 클릭률)")
                        .attributes(key("format")
                            .value(
                                "Click-through rate of PC in recent 4 weeks. With no data, you get 0.")),
                    fieldWithPath("keywordList[].monthlyAveMobileCtr").type(JsonFieldType.STRING)
                        .description(
                            "월간 평균 모바일 클릭률(클릭수 / 노출수 = 클릭률)")
                        .attributes(key("format")
                            .value(
                                "Click-through rate of Mobile in recent 4 weeks. With no data, you get 0.")),
                    fieldWithPath("keywordList[].plAvgDepth").type(JsonFieldType.STRING)
                        .description("최근 한 달 간 사용자가 해당 키워드를 검색했을 때, PC통합검색 영역에 노출된 평균 광고 개수입니다.")
                        .attributes(key("format")
                            .value(
                                "Average depth of PC ad in recent 4 weeks. With no data, you get 0.")),
                    fieldWithPath("keywordList[].compIdx").type(JsonFieldType.STRING)
                        .description(
                            "경쟁정도(최근 한달간 해당 키워드에 대한 경쟁정도를 PC통합검색영역 기준으로 높음/중간/낮음으로 구분한 지표입니다.)")
                        .attributes(key("format")
                            .value(
                                "A competitiveness index based on PC ad. low: low competitiveness, mid: middle competitiveness, high: high competitiveness")),
                    fieldWithPath("keywordList[].compIdx").type(JsonFieldType.STRING)
                        .description(
                            "경쟁정도(최근 한달간 해당 키워드에 대한 경쟁정도를 PC통합검색영역 기준으로 높음/중간/낮음으로 구분한 지표입니다.)")
                        .attributes(key("format")
                            .value(
                                "A competitiveness index based on PC ad. low: low competitiveness, mid: middle competitiveness, high: high competitiveness")),
                    fieldWithPath("timeUnit").type(JsonFieldType.STRING)
                        .description("검색량 추이의 단위입니다")
                        .attributes(key("format")
                            .value(
                                "day, month, year")),
                    fieldWithPath("ratios").type(JsonFieldType.ARRAY)
                        .description("검색량 추이의 월별 리스트입니다.")
                        .attributes(key("format")
                            .value(
                                " 상대값0~100의 수치로 표현됩니다.")),
                    fieldWithPath("rank").type(JsonFieldType.NUMBER)
                        .description("여러 지표를 통해 산출한 키워드 경쟁력 지수입니다")
                        .attributes(key("format")
                            .value(
                                " 0~5까지 0.5 단위로 표시됩니다.")),
                    fieldWithPath("mention").type(JsonFieldType.NUMBER)
                        .description("SNS에 단어가 언급된 언급량입니다. 2021.10.05일 이후의 언급량입니다.")
                        .attributes(key("format")
                            .value(
                                " 숫자로 표시됩니다. 언급량을 확인할 수 없는 경우 0으로 표시됩니다.")),
                    fieldWithPath("twit").type(JsonFieldType.ARRAY)
                        .description("트위터에서 가장 최근에 언급된 5개의 트윗을 가져옵니다.")
                        .attributes(key("format")
                            .value(
                                "5개 이하로 보이는 경우가 있을 수 있습니다."))
                )
            ));
    }

    @DisplayName("일일 인기 검색어 조회 - 성공")
    @Test
    public void getTrend_success() throws Exception {
        List<FeedMessage> feeds = new ArrayList<>();
        feeds.add(new FeedMessage("윈도우11", "윈도우 11, windows 11", "https://...",
            "Tue, 05 Oct 2021 09:00:00 +0900", "20,000+", "MS윈도우11공식출시…10사용자는무료업그레이드",
            "https://www.mk.co.kr/news/it/view/2021/10/941462/", "매일경제"));
        TrendDto trendDto = TrendDto.builder()
            .keywords(feeds)
            .build();

        given(wordService.getTrendKeyword()).willReturn(trendDto);

        mockMvc.perform(get("/api/word/trend")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("WordApi/getTrend/success",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                    fieldWithPath("keywords[].title").type(JsonFieldType.STRING)
                        .description("트렌드 검색어 이름입니다.")
                        .attributes(key("format")
                            .value(
                                "하나, 혹은 두 개의 단어로 구성되어 있습니다.")),
                    fieldWithPath("keywords[].description").type(JsonFieldType.STRING)
                        .description(
                            "단어에 대한 간단한 설명")
                        .attributes(key("format")
                            .value(
                                "단어에 대한 간단한 설명이 기재되어 있습니다. 동의어인 영어 단어가 기재되기도 합니다.")),
                    fieldWithPath("keywords[].link").type(JsonFieldType.STRING)
                        .description("구글 트렌드 센터 링크")
                        .attributes(key("format")
                            .value(
                                "구글 트렌드 센터 링크가 기재되어 있습니다. 뉴스 기사 링크와 다른 점에 유의해주세요.")),
                    fieldWithPath("keywords[].pubdate").type(JsonFieldType.STRING)
                        .description(
                            "기사 발행일")
                        .attributes(key("format")
                            .value(
                                "기사 발행일입니다. 요일, 일, 월, 년, 시의 순서로 기재되어 있습니다. ex) Tue, 05 Oct 2021 09:00:00 +0900")),
                    fieldWithPath("keywords[].traffic").type(JsonFieldType.STRING)
                        .description(
                            "검색 횟수입니다.")
                        .attributes(key("format")
                            .value(
                                "20000+ 등으로 표기되어 있습니다.")),
                    fieldWithPath("keywords[].news_title").type(JsonFieldType.STRING)
                        .description(
                            "관련 뉴스 기사 제목")
                        .attributes(key("format")
                            .value(
                                "관련 뉴스 기사 제목입니다. 공백이나 기타 특수 문자를 제외한 채로 주어집니다.")),
                    fieldWithPath("keywords[].news_url").type(JsonFieldType.STRING)
                        .description(
                            "관련 뉴스 기사 URL")
                        .attributes(key("format")
                            .value(
                                "관련 뉴스 기사 URL입니다.")),
                    fieldWithPath("keywords[].news_source").type(JsonFieldType.STRING)
                        .description("뉴스 기사 출처")
                        .attributes(key("format")
                            .value(
                                "뉴스 기사 출처 정보입니다."))
                )
            ));
    }
}