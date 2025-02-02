package src.view.forms;

import net.miginfocom.swing.MigLayout;
import src.api.here.HereComponents;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.UserServices.CustomerTable;
import src.security.MailServ;
import src.security.PassInput;
import src.validation.CheckAddress;
import src.validation.CheckCustomers;

import javax.swing.*;

import java.awt.*;

public class CreateUser extends JFrame {

    private final JTextField usernameField, emailField, cepField, neighbourhoodField, complementField, houseNumberField, cityField;
    private final JPasswordField passwordField, repeatPasswordField;

    public CreateUser () {

        setTitle("Create Account");
            setLayout(new MigLayout("center center, wrap, gapy 20"));
            setSize(320, 600);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setBackground(new Color(245, 245, 245));
            setLocationRelativeTo(null);

            Panel panel = new Panel();

            JScrollPane scrollPane = new JScrollPane(panel);

            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            scrollPane.setBounds(50, 30, 300, 50);

            usernameField = new JTextField(15);

            passwordField = new JPasswordField(15);

            cepField = new JTextField(15);

            neighbourhoodField = new JTextField(15);

            complementField = new JTextField(15);

            houseNumberField = new JTextField(15);

            cityField = new JTextField(15);

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

                String cep = cepField.getText();

                String number = houseNumberField.getText();

                String complement = complementField.getText();

                String label = neighbourhoodField.getText();

                String city = cityField.getText();

                Address address = new Address(cep, number, complement, label, city);

                Address validAddress = HereComponents.obtainAddressThroughApi(address);

                CheckAddress checkAddress = new CheckAddress();

                CustomerTable customerTable = new CustomerTable(customer, validAddress);

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

                if (!checkAddress.test(validAddress)) {

                    JOptionPane.showMessageDialog(this, "Enter a valid address!");

                    return;

                }

                customerTable.insert();

                JOptionPane.showMessageDialog(this, "Account created successfully!");

                dispose();

            });

            exitPageButton.addActionListener(e -> dispose());

            JPanel jPanel = new JPanel(new MigLayout("center center, wrap, gapy 20"));

            JScrollBar bar = new JScrollBar(Adjustable.VERTICAL);

            jPanel.add(new JLabel("Name:"));

            jPanel.add(usernameField);

            jPanel.add(new JLabel("Email:"));

            jPanel.add(emailField);

            jPanel.add(new JLabel("CEP:"));

            jPanel.add(cepField);

            jPanel.add(new JLabel("Neighbourhood:"));

            jPanel.add(neighbourhoodField);

            jPanel.add(new JLabel("House Number:"));

            jPanel.add(houseNumberField);

            jPanel.add(new JLabel("Complement:"));

            jPanel.add(complementField);

            jPanel.add(new JLabel("City:"));

            jPanel.add(cityField);

            jPanel.add(new JLabel("Password:"));

            jPanel.add(passwordField);

            jPanel.add(new JLabel("Repeat Password:"));

            jPanel.add(repeatPasswordField);

            jPanel.add(createButton, "split 2, sizegroup buttons");

            jPanel.add(exitPageButton, "sizegroup buttons");

            JScrollPane jScrollPane = new JScrollPane(jPanel);

            jScrollPane.setVerticalScrollBar(bar);

            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            add(jScrollPane);

            setVisible(true);

    }

}
