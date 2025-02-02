package src.view.forms;

import src.db.DB;
import src.model.services.UserServices.CustomerTable;
import src.security.LoginServ;
import src.security.UserSession;
import src.view.page.UserPage;

import javax.swing.*;

import java.awt.*;
import java.sql.Connection;

public class Login extends JFrame {

    private final JTextField emailField;

    private final JPasswordField passwordField;

    private final JButton loginBtn;

    private final JButton exitBtn;

    private CustomerTable customerTable;

    public Login () {

        setTitle("Login");
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(300, 220);
        setLocationRelativeTo(null);

        emailField = new JTextField(15);

        passwordField = new JPasswordField(15);

        loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {

            Connection connection;

            connection = DB.getConnection();

            String email = emailField.getText();

            Integer userId = getUserIdByEmail(connection, email);

            String password = new String(passwordField.getPassword());

            if (!messageIfAccountWasNotFound(userId)) return;

            String storedPass = getStoredPasswordByEmail(connection, email);

            UserSession.userId = userId;

            if (!invalidLoginMessage(email, password, storedPass)) return;

            JOptionPane.showMessageDialog(this, "Login successful!");

            new UserPage(connection, userId);

            dispose();

        });

        exitBtn = new JButton("Cancel");

        exitBtn.addActionListener(e -> dispose());

        add(new JLabel("Email:"));

        add(emailField);

        add(new JLabel("Password:"));

        add(passwordField);

        add(loginBtn);

        add(exitBtn);

        setVisible(true);

    }

    private boolean messageIfAccountWasNotFound (Integer userId) {

        if (userId == null) {

            JOptionPane.showMessageDialog(this, "Account not found!");

            return false;

        }

        return true;

    }

    private Integer getUserIdByEmail (Connection connection, String email) {

        customerTable = new CustomerTable();

        return customerTable.obtainUserId(connection, email);

    }

    private String getStoredPasswordByEmail (Connection connection, String email) {

        customerTable = new CustomerTable();

        return customerTable.getStoredPassword(connection, email);

    }

    private boolean isLoginValid(String email, String password, String storedPassword) {

        return LoginServ.isLoginValid(email, password, storedPassword);

    }

    private boolean invalidLoginMessage (String email, String password, String storedPass) {

        if (!isLoginValid(email, password, storedPass)) {

            JOptionPane.showMessageDialog(this, "Something went wrong. Please, try again!");

            return false;

        }

        return true;

    }

}
