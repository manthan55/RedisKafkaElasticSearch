package com.manthan.rediskafkaelasticsearch.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends BaseModel implements Serializable {
    private String title;
    private String content;

    @ManyToOne()
    private Author author;
}
