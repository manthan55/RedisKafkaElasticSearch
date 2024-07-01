package com.manthan.rediskafkaelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
// https://stackoverflow.com/questions/32878813/how-do-you-use-both-spring-data-jpa-and-spring-data-elasticsearch-repositories-o/32879779#32879779
@EnableJpaRepositories("com.manthan.rediskafkaelasticsearch.repositories.jpa")
@EnableElasticsearchRepositories("com.manthan.rediskafkaelasticsearch.repositories.elasticsearch")
public class RedisKafkaElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisKafkaElasticSearchApplication.class, args);
	}

}
