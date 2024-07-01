package com.manthan.rediskafkaelasticsearch.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "posts")
public class PostES {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "title")
    private String title;
    @Field(type = FieldType.Text, name = "content")
    private String content;
    @Field(type = FieldType.Text, name = "author")
    private String author;

    public static PostES fromPost(Post post){
        PostES postES = new PostES();
        postES.setId(post.getId().toString());
        postES.setAuthor(post.getAuthor().getUsername());
        postES.setTitle(post.getTitle());
        postES.setContent(post.getContent());
        return postES;
    }
}
