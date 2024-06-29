package com.manthan.rediskafkaelasticsearch.services;

import com.manthan.rediskafkaelasticsearch.exceptions.AuthorDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.exceptions.PostDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.models.Post;
import com.manthan.rediskafkaelasticsearch.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(){
        return this.postRepository.findAll();
    }

    public Post getPost(Long postId) throws AuthorDoesNotExistException, PostDoesNotExistException {
        Optional<Post> postOptional = this.postRepository.findById(postId);
        if(postOptional.isEmpty()) throw new PostDoesNotExistException(postId);
        return postOptional.get();
    }

    public Post addPost(Post post){
        return this.postRepository.save(post);
    }

    public Post deletePost(Long postId) throws AuthorDoesNotExistException, PostDoesNotExistException {
        Post post = this.getPost(postId);
        this.postRepository.delete(post);
        return post;
    }

    public void deleteAllPosts(){
        this.postRepository.deleteAll();
    }
}
