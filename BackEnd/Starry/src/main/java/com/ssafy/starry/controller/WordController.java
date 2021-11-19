package com.ssafy.starry.controller;

import com.ssafy.starry.controller.dto.SearchDto;
import com.ssafy.starry.controller.dto.TrendDto;
import com.ssafy.starry.exception.valid.ForbiddenWordException;
import com.ssafy.starry.exception.valid.WordNotValidException;
import com.ssafy.starry.service.WordService;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/word")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/search")
    public ResponseEntity<SearchDto> analysisWord(@RequestParam String word) {
        if ("searchWords".equals(word)) {
            throw new ForbiddenWordException("'searchWords' is forbidden word");
        }
        if (word == null || word.length() > 10) {
            throw new WordNotValidException("Word not valid: not null && 1 <= word.length <= 10");
        }

        log.info("analysisWord input word : " + word);
        SearchDto words = wordService.getWordAnalysis(word);
        return ResponseEntity.ok(words);
    }

    @GetMapping("/trend")
    public ResponseEntity<TrendDto> getTrendKeyword() {
        log.info("트렌드 요청 Controller");
        TrendDto trendKeywords = wordService.getTrendKeyword();
        return ResponseEntity.ok(trendKeywords);
    }

}
