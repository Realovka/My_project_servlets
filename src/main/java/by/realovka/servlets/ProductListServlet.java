package by.realovka.servlets;

import by.realovka.dao.ProductDao;
import by.realovka.dao.ProductDaoImpl;
import by.realovka.entity.Product;
import by.realovka.service.ProductAddAndDeleteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;


@WebServlet(urlPatterns = "/productList")

public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addNameProduct = req.getParameter("addNameProduct");
        String addPriceProduct = req.getParameter("addPriceProduct");
        String deleteProduct = req.getParameter("numberDeleteProduct");
        req.getSession().removeAttribute("errorAddProduct");
        req.getSession().removeAttribute("errorDeleteProduct");
        Connection connection = (Connection) req.getSession().getAttribute("connection");
        ProductDao productDao = ProductDaoImpl.getInstance(connection);
        if (addNameProduct != null && addPriceProduct != null && !addNameProduct.equals("") && !addPriceProduct.equals("")) {
            if(ProductAddAndDeleteService.addProduct(addNameProduct, addPriceProduct, connection)){
                List<Product> productsAfterAdd = productDao.getProductList();
                req.getSession().setAttribute("productsList", productsAfterAdd);
            } else {
                String errorAddProduct = "Such product already is";
                req.getSession().setAttribute("errorAddProduct", errorAddProduct);
            }
        }
        if (deleteProduct != null && !deleteProduct.equals("")) {
            if(ProductAddAndDeleteService.deleteProduct(deleteProduct, connection)){
                List<Product> productsAfterDelete = productDao.getProductList();
                req.getSession().setAttribute("productsList", productsAfterDelete);
            } else {
                String errorDeleteProduct = "Such product isn't in DB";
                req.getSession().setAttribute("errorDeleteProduct", errorDeleteProduct);
            }
        }
        req.getRequestDispatcher("/productsListForAdmin.jsp").forward(req, resp);
    }
}
