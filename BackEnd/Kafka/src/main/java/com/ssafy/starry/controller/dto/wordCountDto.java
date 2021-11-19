package com.ssafy.starry.controller.dto;

import java.util.Date;
import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class wordCountDto {

    private String word;

    private long count;

    private Date start;

    private Date end;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WordCount{");
        sb.append("word='").append(word).append('\'');
        sb.append(", count=").append(count);
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append('}');
        return sb.toString();
    }

    @Builder
    public wordCountDto(String word, long count, Date start, Date end) {
        this.word = word;
        this.count = count;
        this.start = start;
        this.end = end;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
