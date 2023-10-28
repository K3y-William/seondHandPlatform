package dao;

import entity.Comment;
import entity.Product;

import java.util.List;

public interface productControl {

    //search the product by name, and option by like
    List<Product> getItem(String name, int sortedByLike);

    //return detail of a product
    Product getDetail(Product product);

    //add item, return the item id
    Product addItem(Product product);

    //add like to the item, return the current like
    Product addLike(Product product);

    //add comment, return number of rows that has been affected in database
    Integer addComment(Comment comment);


}