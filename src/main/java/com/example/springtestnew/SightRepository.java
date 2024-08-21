package com.example.springtestnew;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SightRepository extends MongoRepository<Sight, String> {
    // 根據區域查找景點
    List<Sight> findByZone(String zone);
}
