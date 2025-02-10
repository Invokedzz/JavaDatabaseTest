package src.model.services.UserServices;

import java.sql.Connection;
import java.util.List;

import src.model.entities.UserEntities.User;

public interface UserContract {

    User obtainUserProperties (Connection connection, Integer id);

    Integer obtainUserId (Connection connect, String email);

    String getStoredPassword (Connection connection, String email);

    void updateUser (Connection connection, String name, String email, String password, Integer id);

    List <String> obtainStoredEmails (Connection connection);

}
