package src.view.forms;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LoginAdmin extends JFrame {

    private JTextField emailField, ticketField;

    private JPasswordField passwordField;

    private JButton loginBtn, cancelBtn;

    public LoginAdmin () {

        setTitle("Login as Admin");
        setLayout(new MigLayout("center center, wrap, gapy 20"));
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);

        emailField = returnJTextFieldColumns();

        ticketField = returnJTextFieldColumns();

        passwordField = returnJPasswordFieldColumns();

        loginBtn = new JButton("Login");

        cancelBtn = new JButton("Cancel");

        createCancelBtnAction();

        addComponents();

        setVisible(true);

    }

    private void addComponents () {

        add(new JLabel("Email:"));

        add(emailField);

        add(new JLabel("Ticket:"));

        add(ticketField);

        add(new JLabel("Password:"));

        add(passwordField);

        add(loginBtn);

        add(cancelBtn);

    }

    private void createLoginBtnAction () {

        loginBtn.addActionListener(e -> {



        });

    }

    private void createCancelBtnAction () {

        cancelBtn.addActionListener(e -> dispose());

    }

    private JTextField returnJTextFieldColumns () {

        return new JTextField(15);

    }

    private JPasswordField returnJPasswordFieldColumns () {

        return new JPasswordField(15);

    }

}
