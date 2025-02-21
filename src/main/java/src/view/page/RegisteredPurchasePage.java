package src.view.page;

import src.model.entities.ProdEntities.Purchases;
import src.model.repositories.purchases.PaymentTable;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class RegisteredPurchasePage extends JFrame {

    private final JPanel scrollPanel;

    public RegisteredPurchasePage (Connection connection) {

        setTitle("Stored Purchase Page");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800, 600);

        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setBackground(new Color(245, 245, 245));

        PaymentTable paymentTable = new PaymentTable();
        List<Purchases> purchasesList = paymentTable.obtainPurchases(connection);
        System.out.println(purchasesList);

        displayUpdatedPurchases(connection, purchasesList);

        setVisible(true);

    }

    private void displayUpdatedPurchases (Connection connection, List <Purchases> purchasesList) {

        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        for (Purchases purchase : purchasesList) {
            JPanel purchasePanel = new JPanel();
            purchasePanel.setLayout(new GridBagLayout());
            purchasePanel.setBackground(Color.WHITE);
            purchasePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 10, 5, 10);

            gbc.gridx = 0;
            gbc.gridy = 0;
            purchasePanel.add(new JLabel("Transaction ID:"), gbc);

            gbc.gridx = 1;
            purchasePanel.add(new JLabel(purchase.getTransactionId()), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            purchasePanel.add(new JLabel("Product Bought:"), gbc);

            gbc.gridx = 1;
            purchasePanel.add(new JLabel(purchase.getProductBought()), gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            purchasePanel.add(new JLabel("Transaction Price:"), gbc);

            gbc.gridx = 1;
            purchasePanel.add(new JLabel(purchase.getTransactionPrice().toString()), gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            purchasePanel.add(new JLabel("Status:"), gbc);

            gbc.gridx = 1;

            JTextField statusField = new JTextField(purchase.getStatus().name(),10);

            purchasePanel.add(statusField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            purchasePanel.add(new JLabel("Email:"), gbc);

            gbc.gridx = 1;
            purchasePanel.add(new JLabel(purchase.getCustomer().getEmail()), gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            purchasePanel.add(new JLabel("CEP:"), gbc);

            gbc.gridx = 1;
            purchasePanel.add(new JLabel(purchase.getAddress().getCEP()), gbc);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

            JButton editStatusBtn = new JButton("Edit status");

            editStatusBtn.addActionListener(e -> new EditOrderStatus(connection, purchase.getTransactionId(), statusField.getText()));

            JButton viewMoreInfoBtn = new JButton("More info");

            viewMoreInfoBtn.addActionListener(event -> new DisplayABitMoreOfThePurchaseInfo(purchase.getAddress().getLabel(), purchase.getAddress().getNumber(),
                    purchase.getAddress().getComplement(), purchase.getDate().toString()));

            buttonPanel.add(editStatusBtn);
            buttonPanel.add(viewMoreInfoBtn);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            purchasePanel.add(buttonPanel, gbc);

            scrollPanel.add(purchasePanel);
            scrollPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        }

    }

}
