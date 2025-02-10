package src.view.page;

import net.miginfocom.swing.MigLayout;
import src.model.entities.UserEntities.Customer;
import src.model.services.UserServices.CustomerTable;
import src.security.PassHash;
import src.validation.CheckElementsInOrderToUpdate;
import src.view.validations.user.page.CheckElementsSentByUser;
import src.view.validations.user.page.CheckIfCustomerIsNull;
import src.view.validations.user.page.ComparePasswordsInOrderToUpdate;
import src.view.validations.user.register.CheckIfEmailAlreadyExists;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class UserPage extends JFrame {

    private final JTextField usernameField, emailField, oldPasswordField, newPasswordField;

    private final JButton editBtn, showMoreStuffBtn, deleteBtn;

    public UserPage(Connection connection, Integer userId) {

        setTitle("Profile");
        setLayout(new MigLayout("center center, wrap, gapy 30"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800,600);

        editBtn = new JButton("Update Profile");

        showMoreStuffBtn = new JButton("Show more info");

        deleteBtn = new JButton("Delete Account");

        CustomerTable customerTable = new CustomerTable();

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        Customer customer = customerTable.obtainUserProperties(connection, userId);

        CheckIfCustomerIsNull.isThisCustomerInsideTheSystem(this, customer);

        usernameField = setCustomerNameTextField(customer);

        oldPasswordField = setJPasswordField();

        emailField = setCustomerEmailTextField(customer);

        emailField.setEditable(false);

        newPasswordField = setJPasswordField();

        editBtn.addActionListener(e -> {

            // PassHash, Database, CheckCustomer

        if (!ComparePasswordsInOrderToUpdate.comparePasswords(this, oldPasswordField.getText(), customer)) return;

        // need to make some changes in order to let user change his password

        Customer ogCustomer = new Customer(usernameField.getText(), emailField.getText(), oldPasswordField.getText());

        if (!CheckElementsSentByUser.verifyElements(this, elements, ogCustomer)) return;

        String hashBackOgPassword = PassHash.generateHash(oldPasswordField.getText());

        if (!CheckElementsSentByUser.verifyElements(this, elements, ogCustomer)) return;

        customerTable.updateUser(connection, usernameField.getText(), emailField.getText(), hashBackOgPassword, userId);

        JOptionPane.showMessageDialog(this, "Profile updated!");

        });

        showMoreStuffBtnAction(connection, userId);

        deleteBtnAction(connection, userId);

        addComponents();

        setVisible(true);

    }

    private JTextField setCustomerNameTextField (Customer customer) {

        return new JTextField(customer.getName(), 15);

    }

    private JTextField setCustomerEmailTextField (Customer customer) {

        return new JTextField(customer.getEmail(), 15);

    }

    private JPasswordField setJPasswordField () {

        return new JPasswordField(15);

    }

    private void showMoreStuffBtnAction (Connection connection, Integer userId) {

        showMoreStuffBtn.addActionListener(e -> new ShowMoreOfUsersInfo(connection, userId));

    }

    private void deleteBtnAction (Connection connection, Integer userId) {

        deleteBtn.addActionListener(e -> {

            int response = JOptionPane.showConfirmDialog(null, "Do you really want to proceed?",
                    "Confirm"
                    , JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

                new DeleteUserAccount(connection, userId);

                dispose();

            }

            else dispose();

        });

    }

    private void addComponents () {

        add(new JLabel("Username:"));

        add(usernameField, "span, growx");

        add(new JLabel("Email:"));

        add(emailField, "span, growx");

        add(new JLabel("Change Password:"));

        add(newPasswordField, "span, growx");

        add(new JLabel("Actual Password:"));

        add(oldPasswordField, "span, growx");

        add(editBtn, "split 2, growx");

        add(showMoreStuffBtn, "growx");

        add(deleteBtn, "span, growx, wrap");

    }

}
