package acs.rest;

import acs.entity.BlogPost;
import acs.logic.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BlogController {
    private BlogPostService blogService;

    @Autowired
    public void setMessageService(BlogPostService blogService) {
        this.blogService=blogService;
    }

    // POST /blog get boundary retruns Mono
    @RequestMapping(path = "/blog",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BlogPost> createMessage(@RequestBody BlogPost blogPost) {
        return this.blogService.createPost(blogPost);
    }

    // GET /blog/byUser/{email}?sortBy={sortArrt}&sortOrder={order}
    // GET /blog/byUser/{email}?filterType=byLanguage&filterValue={language}&sortBy={sortArrt}&sortOrder={order}
    // GET /blog/byUser/{email}?filterType=byCreation&filterValue={timeEnum}&sortBy={sortArrt}&sortOrder={order}
    // GET /blog/byUser/{email}?filterType=byProduct&filterValue={productId}&sortBy={sortArrt}&sortOrder={order}
    @RequestMapping(path="/blog/byUser/{email}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BlogPost> getAllByUser (
            @PathVariable("email") String email,
            @RequestParam(name = "filterBy", required = false) String filterBy,
            @RequestParam(name = "filterValue", required = false) String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {

        return this.blogService.getAllByUser(email, filterBy, filterValue, sortBy, sortOrder);
    }


    // GET /blog/byProduct/{productId}?sortBy={sortArrt}&sortOrder={order}
    // GET /blog/byProduct/{productId}?filterType=byLanguage&filterValue={language}&sortBy={sortArrt}&sortOrder={order}
    // GET /blog/byProduct/{productId}?filterType=byCreation&filterValue={timeEnum}&sortBy={sortArrt}&sortOrder={order}
    @RequestMapping(path="/blog/byProduct/{productId}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BlogPost> getAllByProduct (
            @PathVariable("productId") String productId,
            @RequestParam(name = "filterBy", required = false) String filterBy,
            @RequestParam(name = "filterValue", required = false) String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {

        return this.blogService.getAllByProduct(productId, filterBy, filterValue, sortBy, sortOrder);
    }

    // GET /blog?filterType=byCreation&filterValue={timeEnum}&sortBy={sortArrt}&sortOrder={order}
    // GET /blog?filterType=byCount&filterValue={postsCount}
    @RequestMapping(path="/blog",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BlogPost> getAllByProduct (
            @RequestParam(name = "filterBy", required = false) String filterBy,
            @RequestParam(name = "filterValue", required = false) String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {

        return this.blogService.getAll(filterBy, filterValue, sortBy, sortOrder);
    }
}

