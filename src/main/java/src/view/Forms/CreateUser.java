package src.view.Forms;

import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.UserServices.CustomerTable;
import src.validation.CheckCustomers;

import javax.swing.*;

import java.awt.*;

public class CreateUser extends JFrame {

    private JTextField usernameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton createButton;
    private JButton exitPageButton;

    public CreateUser () {

            setTitle("Create Account");
            setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
            setSize(300, 320);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            usernameField = new JTextField(15);

            passwordField = new JPasswordField(15);

            lastNameField = new JTextField(15);

            emailField = new JTextField(15);

            createButton = new JButton("Create");

            exitPageButton = new JButton("Exit");

            createButton.addActionListener(e -> {

                String username = usernameField.getText();

                String lastName = lastNameField.getText();

                String email = emailField.getText();

                String password = new String(passwordField.getPassword());

                Customer customer = new Customer(username, lastName, email, password, TypeUser.CUSTOMER);

                CustomerTable customerTable = new CustomerTable(customer);

                CheckCustomers checkCustomers = new CheckCustomers();

                if (checkCustomers.test(customer)) {

                    customerTable.insert();

                    JOptionPane.showMessageDialog(this, "Account created successfully!");

                    dispose();

                }

                else JOptionPane.showMessageDialog(this, "Something went wrong! Check your inputs!");

            });

            exitPageButton.addActionListener(e -> dispose());

            add(new JLabel("Name:"));

            add(usernameField);

            add(new JLabel("Last Name:"));

            add(lastNameField);

            add(new JLabel("Email:"));

            add(emailField);

            add(new JLabel("Password:"));

            add(passwordField);

            add(createButton);

            add(exitPageButton);

            setVisible(true);

    }

}
