package by.realovka.dao;

import by.realovka.entity.User;

import java.util.Optional;

public interface UserDao {
    void createUser(User user);

    Optional<User> getByLogin(String login);

    Optional<User> getByLoginAndPassword(User user);

}
