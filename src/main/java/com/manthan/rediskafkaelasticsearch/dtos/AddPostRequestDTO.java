package com.manthan.rediskafkaelasticsearch.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPostRequestDTO {
    private String title;
    private String content;
    private Long authorId;
}
