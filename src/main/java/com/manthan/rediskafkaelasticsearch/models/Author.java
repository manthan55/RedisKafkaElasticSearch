package com.manthan.rediskafkaelasticsearch.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author extends BaseModel {
    private String username;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;
}
