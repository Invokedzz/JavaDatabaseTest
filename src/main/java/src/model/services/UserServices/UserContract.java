package src.model.services.UserServices;

import java.sql.Connection;
import src.model.entities.UserEntities.User;

public interface UserContract {

    void updateName (String name, Integer id);

    User obtainUserProperties (Connection connection, Integer id);

    Integer obtainUserId (Connection connect, String email);

    String getStoredPassword (Connection connection, String email);

    boolean checkUserById (Integer id);

}
