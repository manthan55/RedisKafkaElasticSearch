package com.manthan.rediskafkaelasticsearch.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDoesNotExistException extends Exception {
    public PostDoesNotExistException(Long postId) {
        super("Post with id:"+postId+" was not found");
    }
}
