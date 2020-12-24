package acs.logic.db;

import acs.dao.BlogDao;
import acs.entity.BlogPost;
import acs.logic.EnhancedBlogPostService;
import acs.logic.utils.FilterType;
import acs.logic.utils.TimeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;

@Service
public class BlogPostServiceWithDB implements EnhancedBlogPostService {
    private BlogDao blogDao;

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
            // byCreation
            if(filterBy.equals(FilterType.BY_CREATION.toString())) {
                LocalDate fromDate = getFromDate(filterValue);
                if (fromDate != null) {
                    return this.blogDao.findAllByPostingTimeStampAfter(Sort.by(sortOrder, sortBy), fromDate);
                }
            }
            // byCount
            /*else if(filterBy.equals(FilterType.BY_COUNT.toString())){
                return this.blogDao.findAll(PageRequest.of(page(?), Integer.parseInt(filterValue), Sort.Direction.valueOf(sortOrder), "postingTimeStamp",sortBy));
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
                LocalDate fromDate = getFromDate(filterValue);
                if(fromDate!=null){
                    return this.blogDao.findAllByUser_Email_AndPostingTimeStampAfter(Sort.by(sortOrder, sortBy), email, fromDate);
                }
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
                LocalDate fromDate = getFromDate(filterValue);
                if(fromDate!=null) {
                    return this.blogDao.findAllByProduct_Id_AndPostingTimeStampAfter(Sort.by(sortOrder, sortBy), productId, fromDate);
                }
            }
        }
        return this.blogDao.findAllByProduct_Id(Sort.by(sortOrder, sortBy), productId);
    }

    @Override
    public void deleteAll() {
        blogDao.deleteAll();
    }

    private LocalDate getFromDate(String timeEnum){
        LocalDate fromDate=null;
        if(timeEnum.equals(TimeEnum.LAST_DAY.toString())){
            fromDate = LocalDate.now().minusDays(1);
        }
        else if(timeEnum.equals(TimeEnum.LAST_WEEK.toString())){
            fromDate = LocalDate.now().minusDays(7);
        }
        else if(timeEnum.equals(TimeEnum.LAST_MONTH.toString())){
            fromDate = LocalDate.now().minusDays(30);
        }
        return fromDate;
    }
}
