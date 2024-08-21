package com.example.springtestnew;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final SightRepository sightRepository;
    private final KeelungSightsCrawler crawler;

    public DataInitializer(SightRepository sightRepository, KeelungSightsCrawler crawler) {
        this.sightRepository = sightRepository;
        this.crawler = crawler;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // 檢查資料庫是否已經有資料，避免重複插入
            System.out.println("當前 MongoDB URI: " + System.getenv("SPRING_DATA_MONGODB_URI"));
            if (sightRepository.count() == 0) {
                System.out.println("資料庫無資料，開始抓資料...");

                try {
                    // 爬取資料
                    Sight[] sights = crawler.getItems();

                    // 保存到MongoDB
                    sightRepository.saveAll(List.of(sights));

                    System.out.println("資料已成功保存到MongoDB。");
                } catch (IOException e) {
                    System.err.println("抓資料時發生錯誤：" + e.getMessage());
                }
            } else {
                System.out.println("資料庫中已有資料。");
            }
        } catch (Exception e) {
            System.err.println("初始化資料時發生錯誤：" + e.getMessage());
        }
    }
}
