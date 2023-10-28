package entity;

import java.sql.Timestamp;
import java.util.List;


public class Product {

    Integer product_id;
    String owner_username;
    String title;
    String cover;
    String description;
    double price;
    Integer stock;
    Integer likedBy;
    List<String> pics_path;
    Timestamp timestamp;
    List<Comment>  comments;

    public Product(){

    }

    //when getItem, use this constructor
    public Product(String owner_username, String title, String cover, String description, double price, Timestamp timestamp) {
        this.owner_username = owner_username;
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.price = price;
        this.timestamp = timestamp;
    }



    public Product(String owner_username, String title, String cover, String description, double price, Timestamp timestamp, List<String> pics_path, Integer stock) {
        this.owner_username = owner_username;
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.price = price;
        this.timestamp = timestamp;
        this.pics_path = pics_path;
        this.stock = stock;
    }

    //when getDetail, use this constructor
    public Product(String owner_username, String title, String description, double price, Integer stock, Integer likedBy, List<String> pics_path, Timestamp timestamp, List<Comment> comments) {
        this.owner_username = owner_username;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.likedBy = likedBy;
        this.pics_path = pics_path;
        this.timestamp = timestamp;
        this.comments = comments;
    }

    //when adding a product, use the following constructor
    public Product(String owner_username, String title, String cover, String description, double price, Integer stock, List<String> pics_path) {
        this.owner_username = owner_username;
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.pics_path = pics_path;
        timestamp = new Timestamp(System.currentTimeMillis());
    }


    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public void setOwner_username(String owner_username) {
        this.owner_username = owner_username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPics_path(List<String> pics_path) {
        this.pics_path = pics_path;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLikedBy(Integer likedBy) {
        this.likedBy = likedBy;
    }

    public List<String> getPics_path() {
        return pics_path;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public String getOwner_username() {
        return owner_username;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getLikedBy() {
        return likedBy;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


}
