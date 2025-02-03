package src.view.page;

import net.miginfocom.swing.MigLayout;
import src.model.entities.UserEntities.Customer;
import src.model.services.UserServices.CustomerTable;
import src.security.PassHash;
import src.security.PassInput;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.Objects;

public class UserPage extends JFrame {

    private final JTextField usernameField, oldPasswordField, newPasswordField;

    private JButton editBtn;

    private JButton deleteBtn;

    public UserPage(Connection connection, Integer userId) {

        setTitle("Profile");
        setLayout(new MigLayout("center center, wrap, gapy 30"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800,600);

        editBtn = new JButton("Update Profile");

        deleteBtn = new JButton("Delete Account");

        CustomerTable customerTable = new CustomerTable();

        Customer customer = customerTable.obtainUserProperties(connection, userId);

            if (customer == null) {

                JOptionPane.showMessageDialog(this, "Something went wrong!");

                System.exit(0);

            }

        usernameField = setCustomerNameTextField(customer);

        oldPasswordField = setJPasswordField();

        newPasswordField = setJPasswordField();

        editBtn.addActionListener(e -> {

            // PassHash, Database, CheckCustomer

            if (!PassHash.checkChosenHash(oldPasswordField.getText(), customer.getPassword())) {

                JOptionPane.showMessageDialog(this, "Wrong password! Try again!");

                return;

            } else JOptionPane.showMessageDialog(this, "Success!");

        });

        deleteBtnAction(connection, userId);

        addComponents();

        setVisible(true);

    }

    private JTextField setCustomerNameTextField (Customer customer) {

        return new JTextField(customer.getName(), 15);

    }

    private JPasswordField setJPasswordField () {

        return new JPasswordField(15);

    }

    private void deleteBtnAction (Connection connection, Integer userId) {

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

    }

    private void addComponents () {

        add(new JLabel("Username:"));

        add(usernameField);

        add(new JLabel("Actual Password:"));

        add(oldPasswordField);

        add(new JLabel("New Password:"));

        add(newPasswordField);

        add(editBtn);

        add(deleteBtn);

    }

}
