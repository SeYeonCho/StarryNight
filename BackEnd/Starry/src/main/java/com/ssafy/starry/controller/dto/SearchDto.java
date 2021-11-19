package com.ssafy.starry.controller.dto;

import com.ssafy.starry.controller.dto.WordVO.WordApiResponse;
import java.util.ArrayList;
import java.util.List;
import com.ssafy.starry.controller.dto.SearchFlowVO.Data;
import lombok.Getter;


@Getter
public class SearchDto {

    private List<WordApiResponse> keywordList;
    private String timeUnit;
    private List<Double> ratios;
    private double rank;
    private long mention;
    private List<String> twit;


    public SearchDto(WordVO wordVO, SearchFlowVO searchFlowVO, long mention, List<String> twit) {
        keywordList = wordVO.getKeywordList();
        timeUnit = searchFlowVO.getTimeUnit();
        ratios = new ArrayList<Double>();
        this.mention = mention;
        int month = 1;
        for (Data d : searchFlowVO.results.get(0).data) {
            String m = d.period.substring(5, 7);
            int mon = Integer.parseInt(m);
            while (mon > month) {
                ratios.add((double) 0);
                month++;
            }
            ratios.add(d.ratio);
            month++;
        }
        while (month < 9) {
            ratios.add((double) 0);
            month++;
        }

        double sum = ratios.stream().mapToDouble(i -> i).sum();
        double average = sum / ratios.size();
        rank = 0;
        if (average >= 70) {
            rank += 3;
        } else if (average >= 30) {
            rank += 2;
        } else {
            rank += 1;
        }
        String comp = keywordList.get(0).getCompIdx();
        if (comp.equals("높음")) {
            rank += 2;
        } else if (comp.equals("중간")) {
            rank += 1.5;
        } else {
            rank += 1;
        }
        if (average > ratios.get(ratios.size() - 1)) {
            rank -= 0.5;
        } else {
            double diff = ratios.get(ratios.size() - 1) - average;
            if (diff > 30) {
                rank += 2;
            } else if (diff > 20) {
                rank += 1;
            } else {
                rank += 0.5;
            }
        }
        if (rank > 5) {
            rank = 5;
        }
        this.twit = twit;
    }


    public SearchDto(WordVO wordVO, List<Double> ratios, long mention, List<String> twit) {
        keywordList = wordVO.getKeywordList();
        timeUnit = "month";
        this.ratios = ratios;
        this.rank = 5.0;
        this.mention = mention;
        this.twit = twit;
    }


    public SearchDto() {
        keywordList = new ArrayList<>();
        ratios = new ArrayList<>();
        twit = new ArrayList<>();
    }


}
