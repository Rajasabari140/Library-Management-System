package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kce.book.bean.BookBean;
import com.kce.book.bean.AuthorBean;
import com.kce.book.util.DBUtil;

public class BookDAO {

    public int createBook(BookBean bookBean)
    {
        int rows=0;
        Connection connection=DBUtil.getDBConnection();

        String query =
        "INSERT INTO Book_tbl (ISBN, BOOK_TITLE, BOOK_TYPE, AUTHOR_CODE, BOOK_COST) VALUES (?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement ps=connection.prepareStatement(query);

            ps.setString(1, bookBean.getIsbn());
            ps.setString(2, bookBean.getBookName());
            ps.setString(3, String.valueOf(bookBean.getBookType())); 
            ps.setInt(4, bookBean.getAuthor().getAuthorCode());
            ps.setFloat(5, bookBean.getCost());

            rows = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }

    public BookBean fetchBook(String isbn) {
        BookBean book = null;
        Connection connection = DBUtil.getDBConnection();

        String query = "SELECT b.ISBN, b.BOOK_TITLE, b.BOOK_TYPE, b.BOOK_COST, " +
                       "a.AUTHOR_CODE, a.AUTHOR_NAME, a.CONTACT_NO " +
                       "FROM Book_tbl b JOIN Author_tbl a " +
                       "ON b.AUTHOR_CODE = a.AUTHOR_CODE " +
                       "WHERE b.ISBN = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AuthorBean author = new AuthorBean();
                author.setAuthorCode(rs.getInt("AUTHOR_CODE"));
                author.setAuthorName(rs.getString("AUTHOR_NAME"));
                author.setContactNo(rs.getLong("CONTACT_NO")); 

                book = new BookBean();
                book.setIsbn(rs.getString("ISBN"));
                book.setBookName(rs.getString("BOOK_TITLE"));
                book.setBookType(rs.getString("BOOK_TYPE").charAt(0));
                book.setCost(rs.getFloat("BOOK_COST"));
                book.setAuthor(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }
}