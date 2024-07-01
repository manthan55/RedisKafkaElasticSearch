package com.manthan.rediskafkaelasticsearch.repositories.jpa;

import com.manthan.rediskafkaelasticsearch.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
