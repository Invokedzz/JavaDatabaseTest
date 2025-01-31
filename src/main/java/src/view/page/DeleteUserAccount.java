package src.view.page;

import src.model.services.UserServices.CustomerTable;

import javax.swing.*;
import java.sql.Connection;

public class DeleteUserAccount extends JFrame {

    public DeleteUserAccount (Connection connection, Integer userId) {

        CustomerTable customerTable = new CustomerTable();

        customerTable.deleteComponent(connection, userId);


    }

}
