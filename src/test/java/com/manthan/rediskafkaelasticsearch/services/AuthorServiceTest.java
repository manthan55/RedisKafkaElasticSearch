package com.manthan.rediskafkaelasticsearch.services;

import com.manthan.rediskafkaelasticsearch.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// this is a dummy test used to insert sample data into database
@SpringBootTest
public class AuthorServiceTest {
    @Autowired
    AuthorService authorService;
}
