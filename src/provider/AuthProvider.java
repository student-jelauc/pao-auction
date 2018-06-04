package provider;

import entity.User;
import repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthProvider {

    protected static UserRepository userRepository = new UserRepository();

    public static void attachAuthenticatedUser(HttpServletRequest request) {
        User user = getAuthenticatedUser(request);
        request.setAttribute("logged", user);
    }

    public static User getAuthenticatedUser(HttpServletRequest request) {
        Cookie cookies[] = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                try {
                    return userRepository.getByUsername(cookie.getValue());
                } catch (Exception e) { }

                break;
            }
        }

        return null;
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookies[] = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
    }
}
