package src.view.forms;

import net.miginfocom.swing.MigLayout;
import src.api.here.HereComponents;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.UserServices.CustomerTable;
import src.validation.CheckAddress;
import src.validation.CheckCustomers;
import src.view.validations.user.register.RegisterEmailValidation;
import src.view.validations.user.register.RegisterGeneralInfoValidation;
import src.view.validations.user.register.RegisterPasswordValidation;

import javax.swing.*;

import java.awt.*;

public class RegisterUser extends JFrame {

    private JTextField usernameField, emailField, cepField, neighbourhoodField, complementField, houseNumberField, cityField;

    private  JPasswordField passwordField, repeatPasswordField;

    private void addJPanelAndInputs (JButton createButton, JButton exitPageButton) {

        JPanel jPanel = new JPanel(new MigLayout("center center, wrap, gapy 20"));

        jPanel.setPreferredSize(new Dimension(350, 800));

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

    }

    private void setupPanelAndScroll () {

        Panel panel = new Panel();

        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBounds(50, 30, 500, 50);

    }

    private void setInputs () {

        usernameField = setJTextField();

        emailField = setJTextField();

        cepField = setJTextField();

        neighbourhoodField = setJTextField();

        houseNumberField = setJTextField();

        complementField = setJTextField();

        cityField = setJTextField();

        passwordField = setJPasswordField();

        repeatPasswordField = setJPasswordField();

    }

    private JTextField setJTextField () {

        return new JTextField(15);

    }

    private JPasswordField setJPasswordField () {

        return new JPasswordField(15);

    }

    public RegisterUser() {

        setTitle("Create Account");
            setLayout(new MigLayout("center center, wrap, gapy 20"));
            setSize(400, 600);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setBackground(new Color(245, 245, 245));
            setLocationRelativeTo(null);

            setInputs();

            setupPanelAndScroll();

            JButton createButton = new JButton("Create");

            JButton exitPageButton = new JButton("Exit");

            createBtnAction(createButton);

            createExitBtn(exitPageButton);

            addJPanelAndInputs(createButton, exitPageButton);

            setVisible(true);

    }

    private void createBtnAction (JButton createButton) {

        createButton.addActionListener(e -> {

            Customer customer = new Customer(usernameField.getText(), emailField.getText(),
                    new String(passwordField.getPassword()), TypeUser.CUSTOMER);

            Address address = new Address(cepField.getText(), neighbourhoodField.getText(),
                    complementField.getText(), houseNumberField.getText(), cityField.getText());

            System.out.println(address);

            CheckAddress checkAddress = new CheckAddress();

            CheckCustomers checkCustomers = new CheckCustomers();

            if (!RegisterGeneralInfoValidation.invalidCustomerMessage(this, checkCustomers, customer)) return;

            if (!RegisterPasswordValidation.invalidPasswordMessage(this, passwordField)) return;

            if (!RegisterPasswordValidation.invalidPasswordMatchMessage(this, passwordField, repeatPasswordField)) return;

            if (!RegisterEmailValidation.invalidEmailMessage(this, emailField)) return;

            if (!RegisterGeneralInfoValidation.invalidAddressMessage(this, checkAddress, address)) return;

            Address validatedAddress = HereComponents.obtainAddressThroughApi(address);

            CustomerTable customerTable = new CustomerTable(customer, validatedAddress);

            customerTable.insert();

            JOptionPane.showMessageDialog(this, "Account created successfully!");

            dispose();

        });

    }

    private void createExitBtn (JButton exitPageButton) {

        exitPageButton.addActionListener(e -> dispose());

    }


}
