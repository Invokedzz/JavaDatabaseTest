package src.model.services.UserServices;

import src.model.entities.UserEntities.Address;

import java.sql.Connection;

public interface CustomerContract {

    Address obtainAddressProperties (Connection connection, Integer userId);

}
