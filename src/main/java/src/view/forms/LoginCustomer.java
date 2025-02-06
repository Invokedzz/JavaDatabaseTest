package src.view.forms;

import src.db.DB;
import src.model.services.UserServices.CustomerTable;
import src.security.UserSession;
import src.view.page.UserPage;
import src.view.validations.user.login.LoginMessageValidation;
import src.view.validations.user.login.LoginSearchAccountValidation;

import javax.swing.*;

import java.awt.*;
import java.sql.Connection;

public class LoginCustomer extends JFrame {

    private final JTextField emailField;

    private final JPasswordField passwordField;

    private final JButton loginButton, exitButton;

    private CustomerTable customerTable;

    public LoginCustomer() {

        setTitle("Login");
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(300, 220);
        setLocationRelativeTo(null);

        emailField = returnJTextFieldColumns();

        passwordField = returnJPasswordFieldColumns();

        loginButton = new JButton("Login");

        createLoginBtnAction();

        exitButton = new JButton("Cancel");

        createExitButtonAction();

        addComponents();

        setVisible(true);

    }

    private void addComponents () {

        add(new JLabel("Email:"));

        add(emailField);

        add(new JLabel("Password:"));

        add(passwordField);

        add(loginButton);

        add(exitButton);

    }

    private JTextField returnJTextFieldColumns () {

        return new JTextField(15);

    }

    private JPasswordField returnJPasswordFieldColumns () {

        return new JPasswordField(15);

    }

    private void createLoginBtnAction () {

        loginButton.addActionListener(e -> {

            Connection connection;

            connection = DB.getConnection();

            String email = emailField.getText();

            Integer userId = getUserIdByEmail(connection, email);

            String password = new String(passwordField.getPassword());

            if (!LoginSearchAccountValidation.messageIfAccountWasNotFound(this, userId)) return;

            String storedPass = getStoredPasswordByEmail(connection, email);

            UserSession.userId = userId;

            if (!LoginMessageValidation.invalidLoginMessage(this, email, password, storedPass)) return;

            JOptionPane.showMessageDialog(this, "Login successful!");

            new UserPage(connection, userId);

            dispose();

        });

    }

    private void createExitButtonAction () {

        exitButton.addActionListener(e -> dispose());

    }

    private Integer getUserIdByEmail (Connection connection, String email) {

        customerTable = new CustomerTable();

        return customerTable.obtainUserId(connection, email);

    }

    private String getStoredPasswordByEmail (Connection connection, String email) {

        customerTable = new CustomerTable();

        return customerTable.getStoredPassword(connection, email);

    }

}
