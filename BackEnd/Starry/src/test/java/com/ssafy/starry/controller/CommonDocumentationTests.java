package com.ssafy.starry.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.starry.CustomResponseFieldsSnippet;
import com.ssafy.starry.api.controller.EnumViewController;
import com.ssafy.starry.common.utils.notification.NotificationManager;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


@ExtendWith(RestDocumentationExtension.class) // JUnit 5 ????????? ?????? ????????? ?????????
@WebMvcTest(controllers = EnumViewController.class)
class CommonDocumentationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationManager notificationManager;

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

    @Test
    public void commonResponse() throws Exception {

        mockMvc.perform(get("/docs/commonResponse"))
            .andDo(print())
            .andExpect(status().isOk()) // 201 isCreated()
            .andDo(
                document(
                    "common/commonResponse",
                    customResponseFields("custom-response", null, // (1)
                        attributes(key("title").value("????????????")),
                        fieldWithPath("statusCode").type(JsonFieldType.STRING).description("????????????"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("???????????????"),
                        subsectionWithPath("data").description("?????????"))
                ));

    }

    @Test
    public void apiResponseCode() throws Exception {

        mockMvc.perform(get("/docs/apiResponseCode"))
            .andDo(print())
            .andExpect(status().isOk()) // 201 isCreated()
            .andDo(
                document(
                    "common/apiResponseCode",
                    customResponseFields("custom-response", null, // (1)
                        attributes(key("title").value("????????????")),
                        fieldWithPath("VALIDATION_FAILED").type(JsonFieldType.STRING)
                            .description("????????? ????????? ???????????? ?????? ?????? ???????????? ???????????????."),
                        fieldWithPath("NOT_VALID_PARAM").type(JsonFieldType.STRING)
                            .description("??????????????? ???????????? ?????? ??? ???????????? ???????????????."),
                        fieldWithPath("OK").type(JsonFieldType.STRING)
                            .description("????????? ??????????????? ??????????????? ??? ???????????? ???????????????.")

                    )));

    }

    public static CustomResponseFieldsSnippet customResponseFields(String type,
        PayloadSubsectionExtractor<?> subsectionExtractor,
        Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(type, subsectionExtractor,
            Arrays.asList(descriptors), attributes
            , true);
    }


}