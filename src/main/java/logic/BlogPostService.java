package logic;

import entity.BlogPost;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlogPostService {
    Mono<BlogPost> createPost (BlogPost post);

    Flux<BlogPost> getAll (String filterBy, String filterValue, String sortBy, String sortOrder);

    Flux<BlogPost> getAllByUser(String email, String filterBy, String filterValue, String sortBy, String sortOrder);

    Flux<BlogPost> getAllByProduct(String productId, String filterBy, String filterValue, String sortBy, String sortOrder);
}

