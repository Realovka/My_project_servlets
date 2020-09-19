package by.realovka.service;

import by.realovka.dao.UserDao;
import by.realovka.dao.UserDaoImpl;
import by.realovka.entity.User;

import java.sql.Connection;
import java.util.Optional;

public class UserRegistrationService {
    public static boolean userRegistration(User user, Connection connection) {
        UserDao userDao = UserDaoImpl.getInstance(connection);
        Optional<User> searchUserByLogin = userDao.getByLogin(user.getLogin());
        if(searchUserByLogin.isPresent()){
            return true;
        }
        userDao.createUser(user);
        return false;
    }
}
