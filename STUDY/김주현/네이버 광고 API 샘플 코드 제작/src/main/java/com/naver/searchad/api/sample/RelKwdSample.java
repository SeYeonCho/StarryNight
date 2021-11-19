package com.naver.searchad.api.sample;

import com.naver.searchad.api.model.RelKwdStat;
import com.naver.searchad.api.model.estimate.BulkItem;
import com.naver.searchad.api.model.estimate.Device;
import com.naver.searchad.api.model.estimate.IDType;
import com.naver.searchad.api.model.estimate.KeyAndPosition;
import com.naver.searchad.api.model.estimate.PeriodType;
import com.naver.searchad.api.model.estimate.RequestAveragePositionBid;
import com.naver.searchad.api.model.estimate.RequestBidByStatisticsDistribution;
import com.naver.searchad.api.model.estimate.RequestPerformance;
import com.naver.searchad.api.model.estimate.RequestPerformanceBulk;
import com.naver.searchad.api.model.estimate.ResponseAveragePositionBid;
import com.naver.searchad.api.model.estimate.ResponseBidByStatisticsDistribution;
import com.naver.searchad.api.model.estimate.ResponsePerformance;
import com.naver.searchad.api.model.estimate.ResponsePerformanceBulk;
import com.naver.searchad.api.rest.Estimate;
import com.naver.searchad.api.rest.RelKwdStats;
import com.naver.searchad.api.util.PropertiesLoader;
import com.naver.searchad.api.util.RestClient;
import java.util.Collections;
import java.util.Properties;

public class RelKwdSample {

    public static void main(String[] args) {
        try {
//            Properties properties = PropertiesLoader.fromResource("sample.properties");
//            String baseUrl = properties.getProperty("BASE_URL");
//            String apiKey = properties.getProperty("API_KEY");
//            String secretKey = properties.getProperty("SECRET_KEY");
//            long customerId = Long.parseLong(properties.getProperty("CUSTOMER_ID"));
            String baseUrl = "https://api.naver.com";
            String apiKey = "0100000000cfc97ee769249f01d372a729defe420b33fc70f0d4a171edc40e440e41519518";
            String secretKey = "AQAAAADPyX7naSSfAdNypyne/kILQw31LMN5pW/ClK+vmKNYlA==";
            long customerId = 2348879;
            RestClient rest = RestClient.of(baseUrl, apiKey, secretKey);

            RelKwdStat relKwdStat = RelKwdStats.list(rest, customerId, "소고기");
            System.out.println(relKwdStat.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
