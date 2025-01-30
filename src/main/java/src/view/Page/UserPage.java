package src.view.Page;

import src.db.DB;
import src.db.DbException;
import src.model.entities.UserEntities.Customer;
import src.model.services.UserServices.CustomerTable;

import javax.swing.*;
import java.awt.*;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserPage extends JFrame {

    private JTextField usernameField;

    private JTextField actualPasswordField;

    private JButton editBtn;

    private JButton deleteBtn;

    public UserPage(Connection connection, Integer userId) {

        setTitle("User");
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800,600);

        editBtn = new JButton("Edit Info");

        deleteBtn = new JButton("Delete Account");

        CustomerTable customerTable = new CustomerTable();

            Customer customer = customerTable.obtainUserProperties(connection, userId);

            if (customer != null) {

                usernameField = new JTextField(customer.getName(), 15);

                actualPasswordField = new JPasswordField(customer.getPassword(), 15);

            }

            deleteBtn.addActionListener(e -> {

                int response = JOptionPane.showConfirmDialog(null, "Do you really want to proceed?",
                        "Confirm"
                        , JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {

                    new DeleteUserAccount(connection, userId);

                    dispose();

                }

                else dispose();

            });


        add(new JLabel("Username:"));

        add(usernameField);

        add(new JLabel("Password"));

        add(actualPasswordField);

        add(editBtn);

        add(deleteBtn);

        setVisible(true);

    }

}
