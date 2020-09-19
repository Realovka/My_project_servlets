package by.realovka.dao;

import by.realovka.entity.Product;

import java.util.List;


public interface ProductDao {
    void createProduct(Product product);

    List<Product> getProductList();

    void removeById(int idProductDelete);
}
