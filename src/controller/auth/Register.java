package controller.auth;

import entity.User;
import repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {
    protected UserRepository userRepository = new UserRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                request.getParameter("username"),
                request.getParameter("password")
        );

        if (user.getUsername().length() < 2 || user.getPassword().length() < 6) {
            response.sendRedirect("/register");
            return;
        }

        try {
            this.userRepository.store(user);
        } catch (Exception e) {
            response.sendRedirect("/register");
            return;
        }

        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/resource/views/auth/register.jsp");

        dispatcher.forward(request, response);
    }
}
