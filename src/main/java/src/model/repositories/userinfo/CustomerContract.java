package src.model.repositories.userinfo;

import src.model.entities.UserEntities.Address;

import java.sql.Connection;
import java.util.List;

public interface CustomerContract {

    Address obtainAddressProperties (Connection connection, Integer userId);

    List <String> obtainStoredCpf (Connection connection);

}
