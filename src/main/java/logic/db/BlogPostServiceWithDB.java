package logic.db;

import dao.BlogDao;
import entity.BlogPost;
import logic.EnhancedBlogPostService;
import logic.utils.FilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;

@Service
public class BlogPostServiceWithDB implements EnhancedBlogPostService {
    private final BlogDao blogDao;

    @Autowired
    public BlogPostServiceWithDB(BlogDao blogDao) {
        super();
        this.blogDao = blogDao;
    }

    @Override
    public Mono<BlogPost> createPost(BlogPost post) {
        return this.blogDao.save(post);
    }

    @Override
    public Flux<BlogPost> getAll(String filterBy, String filterValue, String sortBy, String sortOrder) {
        if(filterBy!=null &&filterValue!=null)
        {
            /*
            // byCreation
            if(filterBy.equals(FilterType.BY_CREATION.toString())){
                return this.blogDao.findAllByPostingTimeStamp(Sort.by(sortOrder, sortBy), Date.valueOf(filterValue));
            }
            // byCount
            else if(filterBy.equals(FilterType.BY_COUNT.toString())){
                return this.blogDao.findAll(PageRequest.of(page, filterValue, Sort.Direction.valueOf(sortOrder), sortBy));
            }*/
        }
        return this.blogDao.findAll(Sort.by(sortOrder, sortBy));
    }

    @Override
    public Flux<BlogPost> getAllByUser(String email, String filterBy, String filterValue, String sortBy, String sortOrder) {
        if (filterBy != null && filterValue != null) {
            // byLanguage
            if (filterBy.equals(FilterType.BY_LANGUAGE.toString())) {
                return this.blogDao.findAllByUser_Email_AndLanguage(Sort.by(sortOrder, sortBy), email, filterValue);
            }
            // byCreation
            if (filterBy.equals(FilterType.BY_CREATION.toString())) {
                return this.blogDao.findAllByUser_Email_AndAndPostingTimeStamp(Sort.by(sortOrder, sortBy), email, Date.valueOf(filterValue));
            }
            // byProduct
            if (filterBy.equals(FilterType.BY_PRODUCT.toString())) {
                return this.blogDao.findAllByUser_Email_AndProduct_Id(Sort.by(sortOrder, sortBy), email, filterValue);
            }
        }
        return this.blogDao.findAllByUser_Email(Sort.by(sortOrder, sortBy), email);
    }

    @Override
    public Flux<BlogPost> getAllByProduct(String productId, String filterBy, String filterValue, String sortBy, String sortOrder) {
        if(filterBy!=null&&filterValue!=null){
            // byLanguage
            if(filterBy.equals(FilterType.BY_LANGUAGE.toString())){
                return this.blogDao.findAllByProduct_Id_AndLanguage(Sort.by(sortOrder, sortBy), productId, filterValue);
            }
            // byCreation
            if(filterBy.equals((FilterType.BY_CREATION).toString())){
                return this.blogDao.findAllByProduct_Id_AndAndPostingTimeStamp(Sort.by(sortOrder, sortBy), productId, Date.valueOf(filterValue));
            }
        }
        return this.blogDao.findAllByProduct_Id(Sort.by(sortOrder, sortBy), productId);
    }

    @Override
    public void deleteAll() {
        blogDao.deleteAll();
    }
}
