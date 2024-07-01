package com.manthan.rediskafkaelasticsearch.repositories.elasticsearch;

import com.manthan.rediskafkaelasticsearch.models.PostES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostESRepository extends ElasticsearchRepository<PostES, Long> {
    List<PostES> findAllByTitleContaining(String title);
    List<PostES> findAllByContentContaining(String content);
}
