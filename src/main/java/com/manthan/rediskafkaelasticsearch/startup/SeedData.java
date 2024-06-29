package com.manthan.rediskafkaelasticsearch.startup;

import com.manthan.rediskafkaelasticsearch.models.Author;
import com.manthan.rediskafkaelasticsearch.models.Post;
import com.manthan.rediskafkaelasticsearch.services.AuthorService;
import com.manthan.rediskafkaelasticsearch.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


// https://www.baeldung.com/running-setup-logic-on-startup-in-spring#:~:text=Spring%20Boot%20provides%20a%20CommandLineRunner,Spring%20application%20context%20is%20instantiated.
@Component
public class SeedData implements CommandLineRunner {
    private static final Logger LOG =
            LoggerFactory.getLogger(SeedData.class);

    private AuthorService authorService;
    private PostService postService;

    public SeedData(AuthorService authorService, PostService postService)
    {
        this.authorService = authorService;
        this.postService = postService;
    }

    @Override
    public void run(String...args) throws Exception {
        LOG.info("Deleting existing data");
        this.authorService.deleteAllAuthors();
        this.postService.deleteAllPosts();
        LOG.info("Existing data deleted");

        LOG.info("Seeding sample data");

        List<Post> author1Posts = new ArrayList<>();
        List<Post> author2Posts = new ArrayList<>();
        List<Post> author3Posts = new ArrayList<>();

        Author author1 = new Author("author1",author1Posts);
        Author author2 = new Author("author2",author2Posts);
        Author author3 = new Author("author3",author3Posts);

        Post a1p1 = new Post("post1author1","post1 by author1", author1);
        Post a1p2 = new Post("post2author1","post2 by author1", author1);
        Post a1p3 = new Post("post3author1","post3 by author1", author1);

        Post a2p1 = new Post("post1author2","post1 by author2", author2);
        Post a2p2 = new Post("post2author2","post2 by author2", author2);


        this.authorService.addAuthor(author1);
        this.authorService.addAuthor(author2);
        this.authorService.addAuthor(author3);

        this.postService.addPost(a1p1);
        this.postService.addPost(a1p2);
        this.postService.addPost(a1p3);
        this.postService.addPost(a2p1);
        this.postService.addPost(a2p2);

        LOG.info("Sample data seeded");
    }
}