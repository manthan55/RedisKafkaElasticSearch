package com.manthan.rediskafkaelasticsearch.controllers;

import com.manthan.rediskafkaelasticsearch.dtos.AddAuthorRequestDTO;
import com.manthan.rediskafkaelasticsearch.dtos.AuthorDTO;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponse;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponseFailure;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponseSuccess;
import com.manthan.rediskafkaelasticsearch.exceptions.AuthorDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.models.Author;
import com.manthan.rediskafkaelasticsearch.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/")
    public ResponseEntity<APIResponse> getAllAuthors(){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            List<Author> authors = this.authorService.getAllAuthors();
            List<AuthorDTO> authorDTOs = AuthorDTO.fromAuthorEntityList(authors);
            response = new APIResponseSuccess<>(authorDTOs);
        }
        catch(Exception ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<APIResponse> getAuthor(@PathVariable(name = "authorId") Long authorId){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Author author = this.authorService.getAuthor(authorId);
            AuthorDTO authorDTO = AuthorDTO.fromAuthorEntity(author);
            response = new APIResponseSuccess<>(authorDTO);
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

    @PostMapping("/")
    public ResponseEntity<APIResponse> addAuthor(@RequestBody AddAuthorRequestDTO requestDTO){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Author author = new Author();
            author.setUsername(requestDTO.getUsername());
            Author savedAuthor = this.authorService.addAuthor(author);
            AuthorDTO authorDTO = AuthorDTO.fromAuthorEntity(savedAuthor);
            response = new APIResponseSuccess<>(authorDTO);
        }
        catch(Exception ex){
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<APIResponse> deleteAuthor(@PathVariable(name = "authorId") Long authorId){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Author author = this.authorService.deleteAuthor(authorId);
            AuthorDTO authorDTO = AuthorDTO.fromAuthorEntity(author);
            response = new APIResponseSuccess<>(authorDTO);
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
}
