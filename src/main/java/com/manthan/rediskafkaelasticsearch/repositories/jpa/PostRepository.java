package com.manthan.rediskafkaelasticsearch.repositories.jpa;

import com.manthan.rediskafkaelasticsearch.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
