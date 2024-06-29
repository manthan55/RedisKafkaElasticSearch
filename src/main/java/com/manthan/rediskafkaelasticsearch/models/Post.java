package com.manthan.rediskafkaelasticsearch.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends BaseModel {
    private String title;
    private String content;

    @ManyToOne()
    private Author author;
}
