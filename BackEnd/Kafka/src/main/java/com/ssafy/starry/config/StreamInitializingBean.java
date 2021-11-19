package com.ssafy.starry.config;

import com.ssafy.starry.util.RedisUtil;
import com.ssafy.starry.util.WordFilterManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamInitializingBean implements InitializingBean, DisposableBean {

    private final RedisUtil redisUtil;
    private final WordFilterManager wfm;
    protected KafkaStreams kafkaStreams;

    @Override
    public void afterPropertiesSet() throws Exception {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> textLines = streamsBuilder
            .stream("twit", Consumed.with(Serdes.String(), Serdes.String()));

//        Set<String> searchWords = redisUtil.get("searchWords").stream().map(object -> Objects
//            .toString(object, null)).collect(Collectors.toSet());
//        for (String word : searchWords) {
//            log.info("word text : " + word);
//        }
        KTable<String, Long> wordCounts = textLines
            .flatMapValues(value -> {
                List<String> words = new ArrayList<>();
                wfm.renewalWords();
                for (String filter : wfm.getSearchWords()) {
                    System.out.println(filter);
                }
                int vLen = value.length();
                for (int i = 0; i < vLen; i++) {
                    for (int j = Math.min(10, vLen - i); j >= 1; j--) {
                        String s = value.substring(i, i + j);

                        if (wfm.getSearchWords().contains(s)) {
                            words.add(s);
                            setWord(s,value);
                            i += (j - 1);// 인덱스 이동
                            break;
                        }
                    }
                }
                return words;
            })
            .groupBy((key, word) -> word, Grouped.with(Serdes.String(), Serdes.String()))
            .count();

        wordCounts.toStream()
            .foreach((w, c) -> {
//                System.out.println("word: " + w + " -> " + c);
                log.info("word: " + w + " -> " + c);
                redisUtil.set(w, c + "");
//                twitWordCountDto twitCount = (twitWordCountDto) redisUtil.getTwitCount(w);
//                if(twitCount == null){
//                    twitCount = new twitWordCountDto(c);
//                    twitCount.addPreview(textLines);
//                    redisUtil.setTwitCount(w,twitCount);
//                }else{
//                    twitCount.addCount(c);
//                    twitCount.addPreview(textLines);
//                    redisUtil.setTwitCount(w,twitCount);
//                }
            });

        Topology topology = streamsBuilder.build();
        this.kafkaStreams = new KafkaStreams(topology, getStreamConfig());
        kafkaStreams.start();
    }

    @Override
    public void destroy() throws Exception {
        this.kafkaStreams.close();
    }

    private Properties getStreamConfig() {
//        Map<String, Object> props = new HashMap<>();
        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "twit-word-count");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "3.35.214.129:9092");
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
            Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
            Serdes.String().getClass().getName());
        return streamsConfiguration;
    }
    private void setWord(String word, String value){
        redisUtil.setTwit(word+"@twitText", value);
//        twitWordCountDto twitCount = (twitWordCountDto) redisUtil.getTwitCount(word);
//        if(twitCount == null){
//            twitCount = new twitWordCountDto(1);
//            twitCount.addPreview(value);
//            redisUtil.setTwitCount(word,twitCount);
//        }else{
//            twitCount.addCount(1);
//            twitCount.addPreview(value);
//            redisUtil.setTwitCount(word,twitCount);
//        }
    }
}
