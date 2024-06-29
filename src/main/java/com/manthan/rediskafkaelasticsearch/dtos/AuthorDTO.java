package com.manthan.rediskafkaelasticsearch.dtos;

import com.manthan.rediskafkaelasticsearch.models.Author;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String username;

    public static AuthorDTO fromAuthorEntity(Author author){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setUsername(author.getUsername());
        return authorDTO;
    }

    public static List<AuthorDTO> fromAuthorEntityList(List<Author> authors){
        List<AuthorDTO> authorDTOs = new ArrayList<>();
        for (Author author : authors){
            authorDTOs.add(fromAuthorEntity(author));
        }
        return authorDTOs;
    }
}
