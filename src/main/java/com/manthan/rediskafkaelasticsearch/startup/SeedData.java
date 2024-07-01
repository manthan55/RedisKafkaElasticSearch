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

        Author author1 = new Author("ByteByteGo",author1Posts);
        Author author2 = new Author("High Scalability",author2Posts);
        Author author3 = new Author("Jordan has no life",author3Posts);

        Post a1p1 = new Post("A Crash Course in Database Sharding","As an application grows in popularity, it attracts more active users and incorporates additional features. This growth leads to a daily increase in data generation, which is a positive indicator from a business perspective. ", author1);
        Post a1p2 = new Post("How Netflix Manages 238 Million Memberships?","As a subscription-based streaming service, Netflix's primary revenue source is its membership business. With a staggering 238 million members worldwide, managing memberships efficiently is crucial for the company's success and continued growth.", author1);
        Post a1p3 = new Post("A Crash Course on Microservice Communication Patterns","Microservices architecture promotes the development of independent services. However, these services still need to communicate with each other to function as a cohesive system. ", author1);

        Post a2p1 = new Post("Brief History of Scaling Uber","On a cold evening in Paris in 2008, Travis Kalanick and Garrett Camp couldn't get a cab. That's when the idea for Uber was born. How great would it be if you could \"push a button and get a ride?\"", author2);
        Post a2p2 = new Post("Behind AWS S3’s Massive Scale","It’s the service that popularized the notion of cold-storage to the world of cloud. In essence - a scalable multi-tenant storage service which provides interfaces to store and retrieve objects with extremely high availability and durability at a relatively low cost. Customers share all of the underlying hardware.", author2);


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