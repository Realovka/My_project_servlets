package by.realovka.service;

import by.realovka.dao.ProductDaoImpl;
import by.realovka.dao.ProductDao;
import by.realovka.entity.Product;

import java.sql.Connection;
import java.util.List;

public class ProductAddAndDeleteService {
    public static boolean addProduct(String productName, String productPrice, Connection connection) {
        double productPriceDouble = Double.parseDouble(productPrice);
        Product product = new Product(productName, productPriceDouble);
        ProductDao productDao = ProductDaoImpl.getInstance(connection);
        List<Product> products = productDao.getProductList();
        for (Product item : products) {
            if (item.getProduct().equals(product.getProduct())) {
                return false;
            }
        }
        productDao.createProduct(product);
        products.add(product);
        return true;
    }

    public static boolean deleteProduct(String productDelete, Connection connection) {
        int productDeleteInt = Integer.parseInt(productDelete);
        ProductDao productDao = ProductDaoImpl.getInstance(connection);
        List<Product> products = productDao.getProductList();
        for (Product item : products) {
            if (item.getId() == productDeleteInt) {
                productDao.removeById(productDeleteInt);
                return true;
            }
        }
        return false;
    }
}


