package src.view.Page;

import src.db.DB;
import src.db.DbException;
import src.model.services.UserServices.CustomerTable;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteUserAccount extends JFrame {

    private JButton deleteBtn;

    private JButton cancelBtn;

    public DeleteUserAccount (Connection connection, Integer userId) {

        setTitle("Create Account");
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        setSize(300, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        CustomerTable customerTable = new CustomerTable();

        deleteBtn = new JButton("Delete");

        cancelBtn = new JButton("Cancel");

        deleteBtn.addActionListener(e -> {

            customerTable.deleteComponent(connection, userId);

            System.exit(0);

        });

        cancelBtn.addActionListener(e -> dispose());

        add(deleteBtn);

        add(cancelBtn);

        setVisible(true);

    }

}
