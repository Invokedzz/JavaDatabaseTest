package src.view.page;

import net.miginfocom.swing.MigLayout;
import src.model.entities.UserEntities.Address;
import src.model.services.UserServices.CustomerTable;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class ShowMoreOfUsersInfo extends JFrame {

    private final JTextField cepField, neighbourhoodField, houseNumberField, cityField, complementField;

    private final JButton viewPurchasesBtn, cancelBtn;

    public ShowMoreOfUsersInfo(Connection connection, Integer userId) {

        setTitle("More info");
        setLayout(new MigLayout("center center, wrap, gapy 30"));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(300, 620);
        setLocationRelativeTo(null);

        viewPurchasesBtn = new JButton("Purchases");

        cancelBtn = new JButton("Cancel");

        CustomerTable customerTable = new CustomerTable();

        Address userAddress = customerTable.obtainAddressProperties(connection, userId);

        cepField = setCepField(userAddress);

        neighbourhoodField = setNeighbourhoodField(userAddress);

        houseNumberField = setHouseNumberField(userAddress);

        complementField = setComplementField(userAddress);

        cityField = setCityField(userAddress);

        createViewPurchasesBtnAction(connection);

        cancelBtnAction();

        addComponents();

        setVisible(true);

    }

    private void createViewPurchasesBtnAction (Connection connection) {

        viewPurchasesBtn.addActionListener(e -> new UserPurchases(connection));

    }

    private void cancelBtnAction () {

        cancelBtn.addActionListener(e -> dispose());

    }

    private JTextField setCepField(Address address) {

        return new JTextField(address.getCEP(), 15);

    }

    private JTextField setNeighbourhoodField(Address address) {

        return new JTextField(address.getLabel(), 15);

    }

    private JTextField setCityField(Address address) {

        return new JTextField(address.getCity(), 15);

    }

    private JTextField setHouseNumberField(Address address) {

        return new JTextField(address.getNumber(), 15);

    }

    private JTextField setComplementField(Address address) {

        return new JTextField(address.getComplement(), 15);

    }

    private void addComponents() {

        add(new JLabel("CEP:"));

        cepField.setEditable(false);

        add(cepField);

        add(new JLabel("Neighbourhood:"));

        neighbourhoodField.setEditable(false);

        add(neighbourhoodField);

        add(new JLabel("House Number:"));

        houseNumberField.setEditable(false);

        add(houseNumberField);

        add(new JLabel("Complement:"));

        complementField.setEditable(false);

        add(complementField);

        add(new JLabel("City:"));

        cityField.setEditable(false);

        add(cityField);

        add(viewPurchasesBtn);

        add(cancelBtn);

    }

}