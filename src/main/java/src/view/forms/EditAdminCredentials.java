package src.view.forms;

import com.stripe.model.tax.Registration;
import net.miginfocom.swing.MigLayout;
import src.db.DB;
import src.model.entities.UserEntities.Admin;
import src.model.services.UserServices.AdminTable;
import src.view.util.TradeTicketForTheSupposedAdmin;
import src.view.validations.user.page.CheckIfEmailsAreTheSame;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class EditAdminCredentials extends JFrame {

    private JTextField emailField, newEmailField;

    private JButton updateProfileBtn, updateTicketBtn, cancelBtn;

    public EditAdminCredentials (Connection connection, Integer userId) {

        setTitle("Edit Profile");
        setLayout(new MigLayout("center center, wrap 1, gapy 30"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400,500);

        AdminTable adminTable = new AdminTable();

        Admin admin = adminTable.obtainUserProperties(connection, userId);

        emailField = setJTextFieldWithAdminInfo(admin);

        newEmailField = setJTextField();

        updateProfileBtn = new JButton("Update Email");

        updateTicketBtn = new JButton("Ask for new ticket");

        cancelBtn = new JButton("Cancel");

        createUpdateProfileBtnAction(connection, adminTable, userId);

        createUpdateTicketBtnAction(connection, emailField, userId);

        createCancelBtnAction();

        addComponents(admin);

        setVisible(true);

    }

    private void createUpdateProfileBtnAction (Connection connection, AdminTable table, Integer userId) {

        // CheckIfEmailsAreTheSame, RegisterEmailValidation

        updateProfileBtn.addActionListener(e -> {

            if (!CheckIfEmailsAreTheSame.areTheyTheSame(this, emailField.getText(), newEmailField.getText())) return;

            JOptionPane.showMessageDialog(this, "Email updated successfully!");

            table.updateEmail(connection, newEmailField.getText(), userId);

        });

    }

    private void createUpdateTicketBtnAction (Connection connection, JTextField emailField, Integer userId) {

        updateTicketBtn.addActionListener(e -> {

            TradeTicketForTheSupposedAdmin newTicket = new TradeTicketForTheSupposedAdmin();

            newTicket.sendNewTicketToAdmin(connection, emailField.getText(), userId);

            String msg = String.format("Email was sent to: %s", emailField.getText());

            JOptionPane.showMessageDialog(this, msg);

        });

    }

    private void createCancelBtnAction () {

        cancelBtn.addActionListener(e -> dispose());

    }

    private JTextField setJTextFieldWithAdminInfo (Admin admin) {

        return new JTextField(admin.getEmail(), 15);

    }

    private JTextField setJTextField () {

        return new JTextField(15);

    }

    private void addComponents (Admin admin) {

        add(new JLabel("Edit your email or obtain a new ticket"));

        add(new JLabel("Email:"));

        add(emailField);

        add(new JLabel("New Email:"));

        add(newEmailField);

        add(updateProfileBtn);

        add(updateTicketBtn);

        add(cancelBtn);

    }

}
