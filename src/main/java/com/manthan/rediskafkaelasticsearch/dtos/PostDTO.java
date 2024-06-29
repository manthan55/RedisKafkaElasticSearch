package com.manthan.rediskafkaelasticsearch.dtos;

import com.manthan.rediskafkaelasticsearch.models.Author;
import com.manthan.rediskafkaelasticsearch.models.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private AuthorDTO author;

    public static PostDTO fromPostEntity(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setAuthor(AuthorDTO.fromAuthorEntity(post.getAuthor()));
        return postDTO;
    }

    public static List<PostDTO> fromPostEntityList(List<Post> posts){
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts){
            postDTOs.add(fromPostEntity(post));
        }
        return postDTOs;
    }
}
