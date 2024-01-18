package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Home", value = "/Home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get data from the database and process it

        // Generate HTML content
        String htmlContent = "<html><body><h1>Hello, ShiMotren!</h1></body></html>";

        // Set content type
        response.setContentType("text/html");

        // Write HTML content to the response
        response.getWriter().println(htmlContent);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}