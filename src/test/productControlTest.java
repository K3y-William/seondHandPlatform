package test;

import dao.impl.productControlImp;
import entity.Product;
import util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class productControlTest {

    private static productControlImp productControlImp = new productControlImp();

    public static void main(String[] args) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        List<String> path_list = new ArrayList<>();
        path_list.add("this is a path");
        Product product = new Product("owner","kj","Cover: this is a TV", "description: this is a TV", 500.9,time,path_list,5);
        productControlImp.addItem(product);

        String sql = "select product_id from products where title = 'kj' ";
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
                System.out.println(produnct_id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeAll(pstm,conn,rs);
        }
    }
}
