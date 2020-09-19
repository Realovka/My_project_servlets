package by.realovka.dao;

import by.realovka.entity.User;
import by.realovka.entity.UserRole;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static String CREATE_USER = "INSERT INTO users_two  VALUES (default,?,?,?,?::users_role)";
    private static String GET_BY_LOGIN = "SELECT * FROM users_two WHERE login=?";
    private static String GET_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users_two WHERE login=? AND password=?";

    private Connection connection;

    private static UserDao instance = null;

    private UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public static UserDao getInstance(Connection connection) {
        if (instance == null) {
            return new UserDaoImpl(connection);
        } else {
            return instance;
        }
    }


    @Override
    public void createUser(User user) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserRole().name());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getByLogin(String login) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(rs.getString("login")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public Optional<User> getByLoginAndPassword(User user) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(rs.getString("login"), rs.getString("password"), UserRole.valueOf(rs.getString("role"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}