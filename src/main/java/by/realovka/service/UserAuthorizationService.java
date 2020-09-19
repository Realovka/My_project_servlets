package by.realovka.service;

import by.realovka.dao.UserDaoImpl;
import by.realovka.entity.User;
import java.sql.Connection;
import java.util.Optional;


public class UserAuthorizationService {
    public static boolean authorizationUser(User user, Connection connection){
        Optional<User> userIsOrNo = UserDaoImpl.getInstance(connection).getByLoginAndPassword(user);
        if (userIsOrNo.isPresent()){
            return true;
        }
        return false;
    }
}
