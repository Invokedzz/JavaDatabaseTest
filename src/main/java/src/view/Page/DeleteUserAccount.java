package src.view.Page;

import src.model.services.UserServices.CustomerTable;
import src.view.Home.Home;

import javax.swing.*;
import java.sql.Connection;

public class DeleteUserAccount extends JFrame {

    public DeleteUserAccount (Connection connection, Integer userId) {

        CustomerTable customerTable = new CustomerTable();

        customerTable.deleteComponent(connection, userId);


    }

}
