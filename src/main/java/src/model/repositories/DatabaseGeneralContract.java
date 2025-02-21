package src.model.repositories;

import java.sql.Connection;

public interface DatabaseGeneralContract {

    void insert ();

    void deleteComponent (Connection connection, Integer id);

}
