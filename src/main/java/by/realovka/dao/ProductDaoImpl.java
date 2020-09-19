package by.realovka.dao;

import by.realovka.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static String CREATE_PRODUCT = "INSERT INTO products VALUES (default, ?, ?)";
    private static String GET_ALL_PRODUCTS = "SELECT * FROM products";
    private static String REMOVE_BY_ID = "DELETE FROM products WHERE id=?";

    private Connection connection;

    private static ProductDao instance;

    private ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public static ProductDao getInstance(Connection connection) {
        if (instance == null) {
            return new ProductDaoImpl(connection);
        } else {
            return instance;
        }
    }

    @Override
    public void createProduct(Product product) {
        try (
                PreparedStatement ps = connection.prepareStatement(CREATE_PRODUCT)) {
            ps.setString(1, product.getProduct());
            ps.setDouble(2, product.getPrice());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        try (
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProduct(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    @Override
    public void removeById(int idProductDelete) {
        try (
                PreparedStatement ps = connection.prepareStatement(REMOVE_BY_ID)) {
            ps.setInt(1, idProductDelete);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
