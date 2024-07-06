package com.manthan.rediskafkaelasticsearch.services;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.manthan.rediskafkaelasticsearch.exceptions.AuthorDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.exceptions.PostDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.models.Post;
import com.manthan.rediskafkaelasticsearch.models.PostES;
import com.manthan.rediskafkaelasticsearch.repositories.elasticsearch.PostESRepository;
import com.manthan.rediskafkaelasticsearch.repositories.jpa.PostRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class PostService {
    private PostRepository postRepository;
    private PostESRepository postESRepository;
    private RedisTemplate<Long, Post> redisTemplate;

    public PostService(PostRepository postRepository, PostESRepository postESRepository, RedisTemplate<Long, Post> redisTemplate)
    {
        this.postRepository = postRepository;
        this.postESRepository = postESRepository;
        this.redisTemplate = redisTemplate;
    }

    public List<Post> getAllPosts(){
        return this.postRepository.findAll();
    }

    // both @Cacheable and this.redisTemplate are doing the same thing here
    // so disabling this.redisTemplate
//    https://premika-17.medium.com/implementing-redis-in-spring-boot-3d2756e5ab69
//    @Cacheable(value = "post")
    public Post getPost(Long postId) throws PostDoesNotExistException, InterruptedException {
        Post post = null;
        post = this.redisTemplate.opsForValue().get(postId);
        if(post != null) return post;

        // intentional 1 minute delay to simulate proper DB call
        Thread.sleep(1 * 1000);
        Optional<Post> postOptional = this.postRepository.findById(postId);
        if(postOptional.isEmpty()) throw new PostDoesNotExistException(postId);
        post = postOptional.get();
        // https://stackoverflow.com/a/64161239
        redisTemplate.opsForValue().set(postId, post, 30, TimeUnit.SECONDS);
        return post;
    }

    public Post addPost(Post post){
        Post savedPost = this.postRepository.save(post);
        this.postESRepository.save(PostES.fromPost(savedPost));
        return savedPost;
    }

    public Post deletePost(Long postId) throws AuthorDoesNotExistException, PostDoesNotExistException, InterruptedException {
        Post post = this.getPost(postId);
        this.postRepository.delete(post);
        return post;
    }

    public void deleteAllPosts(){
        this.postESRepository.deleteAll();
        this.postRepository.deleteAll();
    }

    public Iterable<PostES> searchPostsByKeywords(String keywords){
//        Iterable<PostES> res = this.postESRepository.findAll();
        List<PostES> titleMatching = this.postESRepository.findAllByTitleContaining(keywords);
        List<PostES> contentMatching = this.postESRepository.findAllByContentContaining(keywords);

        titleMatching.addAll(contentMatching);
        Set<PostES> set = new LinkedHashSet<>(titleMatching);

        Iterable<PostES> res = new ArrayList<>(set);

        return res;
    }
}
