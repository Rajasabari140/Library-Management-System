package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kce.book.bean.AuthorBean;
import com.kce.book.util.DBUtil;

public class AuthorDAO {

    public AuthorBean getAuthor(int authorCode) {

        String query = "SELECT * FROM Author_tbl WHERE author_code = ?";

        try {
            Connection connection = DBUtil.getDBConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, authorCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AuthorBean authorBean = new AuthorBean();
                authorBean.setAuthorCode(rs.getInt("author_code"));
                authorBean.setAuthorName(rs.getString("author_name"));
                authorBean.setContactNo(rs.getLong("contact_no"));

                return authorBean;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public AuthorBean getAuthor(String authorName) {

    	 String query = "SELECT * FROM Author_tbl WHERE author_name = ?";

         try {
             Connection connection = DBUtil.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(query);

             ps.setString(1, authorName);

             ResultSet rs = ps.executeQuery();

             if (rs.next()) {
                 AuthorBean authorBean = new AuthorBean();
                 authorBean.setAuthorCode(rs.getInt("author_code"));
                 authorBean.setAuthorName(rs.getString("author_name"));
                 authorBean.setContactNo(rs.getLong("contact_no"));

                 return authorBean;
             }

         } catch (Exception e) {
             e.printStackTrace();
         }

         return null;
     }
 }