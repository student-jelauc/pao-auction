package controller.auth;

import entity.User;
import repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected UserRepository userRepository = new UserRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;

        try {
            user = this.userRepository.getByUsername(request.getParameter("username"));
            if (!user.getPassword().equals(request.getParameter("password"))) {
                throw new Exception("Bad password");
            }

            Cookie authorize = new Cookie("auth", user.getUsername());
            authorize.setMaxAge(-1);
            response.addCookie(authorize);


        } catch (Exception e) {
            response.sendRedirect("/login");
            return;
        }

        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/resource/views/auth/login.jsp");

        dispatcher.forward(request, response);
    }
}
