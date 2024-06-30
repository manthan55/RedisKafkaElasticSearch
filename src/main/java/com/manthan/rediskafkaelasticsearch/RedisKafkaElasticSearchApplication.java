package com.manthan.rediskafkaelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisKafkaElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisKafkaElasticSearchApplication.class, args);
	}

}
