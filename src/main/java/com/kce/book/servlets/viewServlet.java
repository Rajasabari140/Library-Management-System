package com.kce.book.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import com.kce.book.bean.BookBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewServlet")
public class viewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false); 
        BookBean bookBean = (BookBean) (session != null ? session.getAttribute("book") : null);

        out.print("<html><head><title>Book Details</title></head><body>");
        if (bookBean != null) {
            out.print("<h2>Book Details Found:</h2>");
            out.print("<p><strong>Book Title:</strong> " + bookBean.getBookName() + "</p>");
            out.print("<p><strong>Author Name:</strong> " + bookBean.getAuthor().getAuthorName() + "</p>");
            out.print("<p><strong>Author Contact:</strong> " + bookBean.getAuthor().getContactNo() + "</p>");
            out.print("<p><strong>Book Price:</strong> " + bookBean.getCost() + "</p>");
            out.print("<p><strong>Book ISBN:</strong> " + bookBean.getIsbn() + "</p>");
            out.print("<br><a href='Viewbook.html'>Search Again</a>");
        } else {
            out.print("<h2>Error: No book details found in session.</h2>");
            out.print("<p>Please ensure you search for a book through the proper form.</p>");
            out.print("<a href='Viewbook.html'>Back to Search</a>");
        }
        out.print("</body></html>");
    }
}