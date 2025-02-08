package src.view.forms;

import net.miginfocom.swing.MigLayout;
import src.model.entities.UserEntities.Admin;
import src.model.services.UserServices.AdminTable;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class EditAdminCredentials extends JFrame {

    private JTextField emailField;

    private JButton updateProfileBtn, updateTicketBtn, cancelBtn;

    public EditAdminCredentials (Connection connection, Integer userId) {

        setTitle("Edit Profile");
        setLayout(new MigLayout("center center, wrap 1, gapy 30"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400,400);

        AdminTable adminTable = new AdminTable();

        Admin admin = adminTable.obtainUserProperties(connection, userId);

        emailField = setJTextField(admin);

        updateProfileBtn = new JButton("Update Email");

        updateTicketBtn = new JButton("Ask for new ticket");

        cancelBtn = new JButton("Cancel");

        createUpdateProfileBtnAction();

        createUpdateTicketBtnAction();

        createCancelBtnAction();

        addComponents(admin);

        setVisible(true);

    }

    private void createUpdateProfileBtnAction () {

        // CheckIfEmailsAreTheSame, RegisterEmailValidation

        updateProfileBtn.addActionListener(e -> {

        });

    }

    private void createUpdateTicketBtnAction () {

        updateTicketBtn.addActionListener(e -> {

        });

    }

    private void createCancelBtnAction () {

        cancelBtn.addActionListener(e -> dispose());

    }

    private JTextField setJTextField (Admin admin) {

        return new JTextField(admin.getEmail(), 15);

    }

    private void addComponents (Admin admin) {

        add(new JLabel("Edit your email or obtain a new ticket"));

        add(emailField);

        add(updateProfileBtn);

        add(updateTicketBtn);

        add(cancelBtn);

    }

}
