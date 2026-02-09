package com.kce.book.service;

import com.kce.book.bean.BookBean;
import com.kce.book.dao.BookDAO;

public class Administrator {

    public String addBook(BookBean bookBean)
    {
        if(bookBean==null ||
           bookBean.getBookName().isEmpty() ||
           bookBean.getIsbn().isEmpty() ||
           bookBean.getBookType()==' ' ||        
           bookBean.getCost()==0 ||
           bookBean.getAuthor()==null ||        
           bookBean.getAuthor().getAuthorName().isEmpty())
        {
            return "INVALID";
        }

        BookDAO dao = new BookDAO();
        int result = dao.createBook(bookBean);

        if (result > 0) {
            return "SUCCESS";
        }

        return "FAILURE";
    }

    public BookBean viewBook(String isbn)
    {
        return new BookDAO().fetchBook(isbn);
    }
}
