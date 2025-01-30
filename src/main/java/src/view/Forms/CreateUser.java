package src.view.Forms;

import net.miginfocom.swing.MigLayout;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.UserServices.CustomerTable;
import src.security.MailServ;
import src.security.PassInput;
import src.validation.CheckCustomers;

import javax.swing.*;

import java.awt.*;

public class CreateUser extends JFrame {

    private final JTextField usernameField, emailField;
    private final JPasswordField passwordField, repeatPasswordField;

    public CreateUser () {

            setTitle("Create Account");
            setLayout(new MigLayout("center center, wrap, gapy 20"));
            setSize(320, 430);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setBackground(new Color(245, 245, 245));
            setLocationRelativeTo(null);

            usernameField = new JTextField(15);

            passwordField = new JPasswordField(15);

            repeatPasswordField = new JPasswordField(15);

            emailField = new JTextField(15);

            JButton createButton = new JButton("Create");

            JButton exitPageButton = new JButton("Exit");

            createButton.addActionListener(e -> {

                String username = usernameField.getText();

                String email = emailField.getText();

                String password = new String(passwordField.getPassword());

                String rePassword = new String(repeatPasswordField.getPassword());

                Customer customer = new Customer(username, email, password, TypeUser.CUSTOMER);

                CustomerTable customerTable = new CustomerTable(customer);

                CheckCustomers checkCustomers = new CheckCustomers();

                if (!checkCustomers.test(customer)) {

                   JOptionPane.showMessageDialog(this, "Something went wrong. Check your inputs!");

                   return;

                }

                if (!PassInput.verifyPasswordText(password)) {

                    JOptionPane.showMessageDialog(this, "Your password must contain letters and numbers!");

                    return;

                }

                if (!password.equals(rePassword)) {

                    JOptionPane.showMessageDialog(this, "Passwords do NOT match!");

                    return;

                }

                if (!MailServ.checkMail(email)) {

                    JOptionPane.showMessageDialog(this, "Enter a valid email!");

                    return;

                }

                customerTable.insert();

                JOptionPane.showMessageDialog(this, "Account created successfully!");

                dispose();

            });

            exitPageButton.addActionListener(e -> dispose());

            add(new JLabel("Name:"));

            add(usernameField);

            add(new JLabel("Email:"));

            add(emailField);

            add(new JLabel("Password:"));

            add(passwordField);

            add(new JLabel("Repeat Password:"));

            add(repeatPasswordField);

            add(createButton, "split 2, sizegroup buttons");

            add(exitPageButton, "sizegroup buttons");

            setVisible(true);

    }

}
