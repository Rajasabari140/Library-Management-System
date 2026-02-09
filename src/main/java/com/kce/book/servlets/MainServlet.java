package com.kce.book.servlets;

import java.io.IOException;



import com.kce.book.bean.BookBean;
import com.kce.book.dao.AuthorDAO;
import com.kce.book.service.Administrator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("AddBook".equals(operation)) {

            String result = addBook(request);

            if ("SUCCESS".equals(result)) {
                response.sendRedirect("Menu.html");
            } else if ("INVALID".equals(result)) {
                response.sendRedirect("Invalid.html");
            } else {
                response.sendRedirect("Failure.html");
            }

        } else if ("Search".equals(operation)) {

            String isbn = request.getParameter("isbn");
            BookBean bookBean = viewBook(isbn);

            if (bookBean == null) {
                response.sendRedirect("Invalid.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("book", bookBean);
                
                RequestDispatcher rd = request.getRequestDispatcher("viewServlet"); 
                rd.forward(request, response);
            }
        }
        else {
            response.getWriter().println("Operation not matched: " + operation);
        }

    }

    public String addBook(HttpServletRequest request) {

        String isbn = request.getParameter("isbn");
        String bookName = request.getParameter("bookName");
        String bookType = request.getParameter("bookType");
        String authorName = request.getParameter("authorName");
        String cost = request.getParameter("cost");

        BookBean bookBean = new BookBean();
        bookBean.setIsbn(isbn);
        bookBean.setBookName(bookName);
        bookBean.setBookType(bookType.charAt(0));
        bookBean.setCost(Float.parseFloat(cost));
        bookBean.setAuthor(new AuthorDAO().getAuthor(authorName));

        return new Administrator().addBook(bookBean);
    }

    public BookBean viewBook(String isbn) {
        return new Administrator().viewBook(isbn);
    }
}
