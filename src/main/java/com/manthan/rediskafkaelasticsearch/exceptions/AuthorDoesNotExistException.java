package com.manthan.rediskafkaelasticsearch.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDoesNotExistException extends Exception {
    public AuthorDoesNotExistException(Long authorId) {
        super("Author with id:"+authorId+" was not found");
    }
}
