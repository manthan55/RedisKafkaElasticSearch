package com.manthan.rediskafkaelasticsearch.services;

import com.manthan.rediskafkaelasticsearch.exceptions.AuthorDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.models.Author;
import com.manthan.rediskafkaelasticsearch.repositories.jpa.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        return this.authorRepository.findAll();
    }

    public Author getAuthor(Long authorId) throws AuthorDoesNotExistException {
        Optional<Author> authorOptional = this.authorRepository.findById(authorId);
        if(authorOptional.isEmpty()) throw new AuthorDoesNotExistException(authorId);
        return authorOptional.get();
    }

    public Author addAuthor(Author author){
        return this.authorRepository.save(author);
    }

    public Author deleteAuthor(Long authorId) throws AuthorDoesNotExistException {
        Author author = this.getAuthor(authorId);
        this.authorRepository.delete(author);
        return author;
    }

    public void deleteAllAuthors(){
        this.authorRepository.deleteAll();
    }
}
