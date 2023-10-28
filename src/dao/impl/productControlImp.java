package dao.impl;

import dao.productControl;
import entity.Comment;
import entity.Product;
import util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DButil.exeUpdate;

public class productControlImp implements productControl {
    @Override
    public List<Product> getItem(String name, int sortedByLike) {
        String sql;
        //sorted by time from new to old
        if (sortedByLike == 0){
            sql = "select owner_username, title, cover, description, price, addingTime" +
                    "from products" +
                    "where title like %" + name + "%" +
                    "order by addingTime DESC";
        }

        //sorted by liked, from high to low
        else{
            sql = "select owner_username, title, cover, description, price, addingTime" +
                    "from products" +
                    "where title like %" + name + "%" +
                    "order by likes DESC";
        }

        Connection conn = DButil.getConnection();
        List<Product> product_list = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                //extract data from rs
                String owner_username = rs.getString(1);
                String title = rs.getString(2);
                String cover = rs.getString(3);
                String description = rs.getString(4);
                float price = rs.getFloat(5);
                Timestamp timestamp = rs.getTimestamp(6);

                //put the data into stock object
                Product product = new Product(owner_username,title,cover,description,price,timestamp);

                //add all the stock into stock_list
                product_list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeAll(pstm,conn,rs);
        }

        return product_list;
    }

    @Override
    public Product getDetail(Product product) {
        Connection conn = DButil.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Product product_detail = new Product();
        List<Comment> comment_list = new ArrayList<>();
        List<String> pics_list = new ArrayList<>();
        String sql = "select owner_username, title, description, price, stock, likedBy, addingTime" +
                "from products" +
                "where product_id = " + product.getProduct_id();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                //extract data from rs
                String owner_username = rs.getString(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                float price = rs.getFloat(4);
                Integer stock = rs.getInt(5);
                Integer likedBy = rs.getInt(6);
                Timestamp timestamp = rs.getTimestamp(7);

                //put the data into product object
                product_detail.setOwner_username(owner_username);
                product_detail.setTitle(title);
                product_detail.setDescription(description);
                product_detail.setPrice(price);
                product_detail.setStock(stock);
                product_detail.setLikedBy(likedBy);
                product_detail.setTimestamp(timestamp);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeAll(pstm,conn,rs);
        }

        //get Comment_list
        sql = "select username,comment,likes,createdTime" +
                "from comments" +
                "where product_id = " + product.getProduct_id();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                //extract data from rs
                String username = rs.getString(1);
                String content = rs.getString(2);
                Integer likes = rs.getInt(3);
                Timestamp createdTime = rs.getTimestamp(4);

                //put the data into product object
                Comment comment = new Comment(username,content,likes,createdTime);
                comment_list.add(comment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeAll(pstm,conn,rs);
        }


        //select the product's picture_path
        sql = "select path" +
                "from picspath" +
                "where product_id = " + product.getProduct_id();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                //extract data from rs
                String path = rs.getString(1);

                pics_list.add(path);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeAll(pstm,conn,rs);
        }
        product_detail.setComments(comment_list);
        product_detail.setPics_path(pics_list);

        return product_detail;
    }


    @Override
    public Product addItem(Product product) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Object obj[] = {product.getTitle(),product.getDescription(),product.getPrice(),product.getCover(),product.getOwner_username(),product.getStock(),time};
        //insert product detail
        String sql = "insert into products" +
                "(title,description,price,cover,owner_username,stock,addingTime)" +
                "values (?,?,?,?,?,?,?)";
        exeUpdate(sql,obj);



        //get inserted item's product_id
        //估计是char 和 string类型的原因
        sql = "select product_id from products where title = " + product.getTitle();
        //sql = "select product_id from products where title = 'kj' ";
        Connection conn = DButil.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Integer produnct_id = null;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                //extract data from rs
                produnct_id = rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeAll(pstm,conn,rs);
        }

        //insert picsPath
        List<String> path = product.getPics_path();
        sql = "insert into picspath values (?,?)";
        for (int i = 0; i < path.size(); i++) {
            Object temp[] = {produnct_id, path.get(i)};
            exeUpdate(sql,temp);
        }

        Product result = new Product();
        result.setProduct_id(produnct_id);
        return result;


    }

    @Override
    public Product addLike(Product product) {
        Object obj[] = {product.getProduct_id()};
        //update likes
        String sql = "update products set likes = likes + 1 where product_id = ?";
        exeUpdate(sql,obj);

        //get current likes
        sql = "select likes from products where product_id = " + product.getProduct_id();
        Connection conn = DButil.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Integer likes = null;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                //extract data from rs
                likes = rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeAll(pstm,conn,rs);
        }

        Product result = new Product();
        result.setLikedBy(likes);
        return result;
    }


    //return the number of rows that has been affected
    @Override
    public Integer addComment(Comment comment) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Object obj[] = {comment.getProduct_id(),comment.getUsername(),comment.getContent(),0,time};
        String sql = "insert into comments values (?,?,?,?,?)";
        return exeUpdate(sql,obj);
    }
}
