package by.realovka.servlets;

import by.realovka.dao.ProductDaoImpl;
import by.realovka.dao.UserDaoImpl;
import by.realovka.entity.Product;
import by.realovka.entity.User;
import by.realovka.service.UserAuthorizationService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static by.realovka.entity.UserRole.ADMIN;
import static by.realovka.entity.UserRole.USER;


@WebServlet(urlPatterns = "/authorization")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        String loginAuthorization = req.getParameter("loginAuthorization");
        String passwordAuthorization = req.getParameter("passwordAuthorization");
        Connection connection = (Connection) req.getSession().getAttribute("connection");
        User userAuthorization = new User(loginAuthorization,passwordAuthorization);
        if(UserAuthorizationService.authorizationUser(userAuthorization, connection)){
            Optional<User> userIsOrNo = UserDaoImpl.getInstance(connection).getByLoginAndPassword(userAuthorization);
            List<Product> products = ProductDaoImpl.getInstance(connection).getProductList();
            req.getSession().setAttribute("productsList",products);
            redirectByUserRole(userIsOrNo.get(),req, resp);
        } else {
            String errorAuthorization = "Login or password is invalid";
            req.getSession().setAttribute("errorAuthorization", errorAuthorization);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }

    private void redirectByUserRole(User userAuthorization, HttpServletRequest req, HttpServletResponse resp){
        if(userAuthorization.getUserRole().equals(ADMIN)){
            try {
                resp.sendRedirect("/productsListForAdmin.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userAuthorization.getUserRole().equals(USER)){
            try {
                resp.sendRedirect("/productsListForUser.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
