package com.manthan.rediskafkaelasticsearch.services;

import com.manthan.rediskafkaelasticsearch.exceptions.AuthorDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.exceptions.PostDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.models.Post;
import com.manthan.rediskafkaelasticsearch.repositories.PostRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class PostService {
    private PostRepository postRepository;
    private RedisTemplate<Long, Post> redisTemplate;

    public PostService(PostRepository postRepository, RedisTemplate<Long, Post> redisTemplate) {
        this.postRepository = postRepository;
        this.redisTemplate = redisTemplate;
    }

    public List<Post> getAllPosts(){
        return this.postRepository.findAll();
    }

    // both @Cacheable and this.redisTemplate are doing the same thing here
    // so disabling this.redisTemplate
//    https://premika-17.medium.com/implementing-redis-in-spring-boot-3d2756e5ab69
//    @Cacheable(value = "post")
    public Post getPost(Long postId) throws AuthorDoesNotExistException, PostDoesNotExistException {
        Post post = null;
        post = this.redisTemplate.opsForValue().get(postId);
        if(post != null) return post;

        Optional<Post> postOptional = this.postRepository.findById(postId);
        if(postOptional.isEmpty()) throw new PostDoesNotExistException(postId);
        post = postOptional.get();
        redisTemplate.opsForValue().set(postId, post, 20, TimeUnit.SECONDS);
        return post;
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
