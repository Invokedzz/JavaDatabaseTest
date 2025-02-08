package src.model.services.UserServices;

import java.sql.Connection;

public interface AdminContract {

    String getStoredTicket (Connection connection, Integer userId);

    void updateEmail (Connection connection, String email, Integer userId);

    void updateTicket (Connection connection, String ticket, Integer userId);

}
