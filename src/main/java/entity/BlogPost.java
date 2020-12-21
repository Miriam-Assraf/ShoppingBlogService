package entity;

import logic.utils.Language;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Document
public class BlogPost {
    @Id
    private String id;
    private User user;
    private Product product;
    private Date postingTimeStamp;
    private Language language;
    private Map<String,Object> postContent;

    public BlogPost(){
        this.postContent=new HashMap<>();
    }

    public BlogPost(User user, Product product, Date postingTimeStamp, Language language, Map<String,Object> postContent) {
        this.user = user;
        this.product = product;
        this.postingTimeStamp = postingTimeStamp;
        this.language = language;
        this.postContent = postContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getPostingTimeStamp() {
        return postingTimeStamp;
    }

    public void setPostingTimeStamp(Date postingTimeStamp) {
        this.postingTimeStamp = postingTimeStamp;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Map getPostContent() {
        return postContent;
    }

    public void setPostContent(Map postContent) {
        this.postContent = postContent;
    }
}
