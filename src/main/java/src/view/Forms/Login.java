package src.view.Forms;

import src.db.DB;
import src.db.DbException;
import src.model.entities.UserEntities.Customer;
import src.model.services.UserServices.CustomerTable;
import src.security.LoginServ;
import src.security.UserSession;
import src.view.Page.ProductsPage;

import javax.swing.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

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
        setSize(300, 220);
        setLocationRelativeTo(null);

        emailField = new JTextField(15);

        passwordField = new JPasswordField(15);

        loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {

            try (Connection connection = DB.getConnection()) {

                String email = emailField.getText();

                Integer userId = getUserIdByEmail(connection, email);

                String password = new String(passwordField.getPassword());

                if (userId != null) {

                    String storedPass = getStoredPasswordByEmail(email);

                    UserSession.userId = userId;

                    if (isLoginValid(email, password, storedPass)) {

                        JOptionPane.showMessageDialog(this, "Login successful!");

                        new ProductsPage(userId);

                        dispose();

                    }

                }

                else JOptionPane.showMessageDialog(this, "Account not found!");

            } catch (SQLException exception) {

                throw new DbException(exception.getMessage());

            }

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

    private Integer getUserIdByEmail (Connection connection, String email) {
        customerTable = new CustomerTable();
        return customerTable.obtainUserId(connection, email);
    }

    private String getStoredPasswordByEmail (String email) {
        customerTable = new CustomerTable();
        return customerTable.getStoredPassword(email);
    }

    private boolean isLoginValid(String email, String password, String storedPassword) {
        return LoginServ.isLoginValid(email, password, storedPassword);
    }

}
