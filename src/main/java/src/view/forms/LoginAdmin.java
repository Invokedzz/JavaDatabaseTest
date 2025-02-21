package src.view.forms;

import net.miginfocom.swing.MigLayout;
import src.db.DB;
import src.model.repositories.userinfo.AdminTable;
import src.security.UserSession;
import src.view.page.AdminPage;
import src.validation.user.login.LoginMessageValidation;
import src.validation.user.login.LoginTicketValidation;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class LoginAdmin extends JFrame {

    private final JTextField emailField, ticketField;

    private final JPasswordField passwordField;

    private AdminTable adminTable;

    private final JButton loginBtn, cancelBtn;

    public LoginAdmin () {

        setTitle("Login as admin");
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

        createLoginBtnAction();

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

        // getUserIdByEmail, getStoredPasswordByEmail

        loginBtn.addActionListener(e -> {

            Connection connection;

            connection = DB.getConnection();

            String email = emailField.getText();

            String password = new String(passwordField.getPassword());

            String ticket = ticketField.getText();

            Integer userId = getUserIdByEmail(connection, email);

            UserSession.userId = userId;

            String hashedPassword = getStoredPasswordByEmail(connection, email);

            String storedTicket = obtainTicketFromDB(connection, userId);

            if (!LoginMessageValidation.invalidLoginMessage(this, email, password, hashedPassword)) return;

            if (!LoginTicketValidation.checkSentTicket(this, ticket, storedTicket)) return;

            JOptionPane.showMessageDialog(this, "Success! Logging you into the system.");

            new AdminPage(connection, userId);

            dispose();

        });

    }

    private Integer getUserIdByEmail (Connection connection, String email) {

        adminTable = new AdminTable();

        return adminTable.obtainUserId(connection, email);

    }

    private String obtainTicketFromDB (Connection connection, Integer userId) {

        adminTable = new AdminTable();

        return adminTable.getStoredTicket(connection, userId);

    }

    private String getStoredPasswordByEmail (Connection connection, String email) {

        adminTable = new AdminTable();

        return adminTable.getStoredPassword(connection, email);

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
