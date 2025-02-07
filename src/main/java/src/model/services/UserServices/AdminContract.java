package src.model.services.UserServices;

import java.sql.Connection;

public interface AdminContract {

    String getStoredTicket (Connection connection, Integer userId);

}
