package by.realovka.servlets;

import by.realovka.entity.User;
import by.realovka.entity.UserRole;
import by.realovka.service.UserRegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        String nameRegistration = req.getParameter("nameRegistration");
        String loginRegistration = req.getParameter("loginRegistration");
        String passwordRegistration = req.getParameter("passwordRegistration");
        UserRole userRole = UserRole.valueOf(req.getParameter("role"));
        Connection connection = (Connection) req.getSession().getAttribute("connection");
        User userRegistration = new User(nameRegistration, loginRegistration, passwordRegistration, userRole);
        if (UserRegistrationService.userRegistration (userRegistration, connection)) {
            String errorRegistration = "Such login is used";
            req.getSession().setAttribute("errorRegistration", errorRegistration);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/index.jsp");
        }
    }
}

