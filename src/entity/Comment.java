package entity;

import java.sql.Timestamp;

public class Comment {

    Integer product_id;
    String username;
    String content;
    Integer likes;
    Timestamp createdTime;

    public Comment(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public Comment(String username, String content, Integer likes, Timestamp createdTime) {
        this.username = username;
        this.content = content;
        this.likes = likes;
        this.createdTime = createdTime;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Integer getLikes() {
        return likes;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public Integer getProduct_id() {
        return product_id;
    }
}
