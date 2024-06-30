package com.manthan.rediskafkaelasticsearch.controllers;

import com.manthan.rediskafkaelasticsearch.dtos.AddAuthorRequestDTO;
import com.manthan.rediskafkaelasticsearch.dtos.AddPostRequestDTO;
import com.manthan.rediskafkaelasticsearch.dtos.AuthorDTO;
import com.manthan.rediskafkaelasticsearch.dtos.PostDTO;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponse;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponseFailure;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponseSuccess;
import com.manthan.rediskafkaelasticsearch.exceptions.AuthorDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.exceptions.PostDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.models.Author;
import com.manthan.rediskafkaelasticsearch.models.Post;
import com.manthan.rediskafkaelasticsearch.services.AuthorService;
import com.manthan.rediskafkaelasticsearch.services.PostService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;
    private AuthorService authorService;

    public PostController(PostService postService, AuthorService authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }


    @GetMapping("/")
    public ResponseEntity<APIResponse> getAllPosts(){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            List<Post> posts = this.postService.getAllPosts();
            List<PostDTO> postDTOs = PostDTO.fromPostEntityList(posts);
            response = new APIResponseSuccess<>(postDTOs);
        }
        catch(Exception ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<APIResponse> getPost(@PathVariable(name = "postId") Long postId){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Post post = this.postService.getPost(postId);
            PostDTO postDTO = PostDTO.fromPostEntity(post);
            response = new APIResponseSuccess<>(postDTO);
        }
        catch(PostDoesNotExistException ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        catch(Exception ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PostMapping("/")
    public ResponseEntity<APIResponse> addPost(@RequestBody AddPostRequestDTO requestDTO){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Author postAuthor = this.authorService.getAuthor(requestDTO.getAuthorId());
            Post post = new Post();
            post.setTitle(requestDTO.getTitle());
            post.setContent(requestDTO.getContent());
            post.setAuthor(postAuthor);

            Post savedPost = this.postService.addPost(post);
            PostDTO postDTO = PostDTO.fromPostEntity(savedPost);
            response = new APIResponseSuccess<>(postDTO);
        }
        catch(AuthorDoesNotExistException ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        catch(Exception ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable(name = "postId") Long postId){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Post post = this.postService.deletePost(postId);
            PostDTO postDTO = PostDTO.fromPostEntity(post);
            response = new APIResponseSuccess<>(postDTO);
        }
        catch(PostDoesNotExistException ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        catch(Exception ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }
}
