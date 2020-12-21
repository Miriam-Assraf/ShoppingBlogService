package dao;

import entity.BlogPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface BlogDao extends ReactiveSortingRepository<BlogPost, String> {
    // find all
    Flux<BlogPost> findAll(final Pageable page);
    Flux<BlogPost> findAllByPostingTimeStamp(Sort by, @Param("enumTime") Date enumTime);

    // find all by user
    Flux<BlogPost> findAllByUser_Email(Sort by, @Param("email") String email);
    Flux<BlogPost> findAllByUser_Email_AndAndPostingTimeStamp(Sort by, @Param("email") String email, @Param("date") Date date);
    Flux<BlogPost> findAllByUser_Email_AndLanguage(Sort by, @Param("email") String email, @Param("language") String language);
    Flux<BlogPost> findAllByUser_Email_AndProduct_Id(Sort by, @Param("email") String email, @Param("productId") String productId);

    // find all by product
    Flux<BlogPost> findAllByProduct_Id(Sort by, @Param("productId") String productId);
    Flux<BlogPost> findAllByProduct_Id_AndAndPostingTimeStamp(Sort by, @Param("productId") String productId, @Param("date") Date date);
    Flux<BlogPost> findAllByProduct_Id_AndLanguage(Sort by, @Param("productId") String productId, @Param("language") String language);

}
