package src.view.Forms;

import src.model.entities.UserEntities.Customer;
import src.model.services.UserServices.CustomerTable;
import src.security.LoginServ;

import javax.swing.*;

import java.awt.*;

public class Login extends JFrame {

    private JTextField emailField;

    private JPasswordField passwordField;

    private JButton loginBtn;

    private JButton exitBtn;

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

            String email = emailField.getText();

            String password = new String(passwordField.getPassword());

            CustomerTable customerTable = new CustomerTable();

            String storedPass = customerTable.getStoredPassword(email);

            if (LoginServ.isLoginValid(email, password, storedPass)) {

                JOptionPane.showMessageDialog(this, "Login successful!");

            }

            else JOptionPane.showMessageDialog(this, "Account not found!");

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

}
