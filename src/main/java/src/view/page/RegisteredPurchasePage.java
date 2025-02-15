package src.view.page;

import net.miginfocom.swing.MigLayout;
import src.model.entities.ProdEntities.Purchases;
import src.model.services.PaymentServices.PaymentTable;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class RegisteredPurchasePage extends JFrame {

    public RegisteredPurchasePage (Connection connection) {

        setTitle("Stored Purchase Page");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800, 600);

        PaymentTable paymentTable = new PaymentTable();
        List<Purchases> purchasesList = paymentTable.obtainPurchases(connection);

        displayUpdatedPurchases(connection, paymentTable, purchasesList);

        setVisible(true);

    }

    private void displayUpdatedPurchases (Connection connection, PaymentTable paymentTable, List <Purchases> purchasesList) {

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        scrollPanel.removeAll();

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
            purchasePanel.add(new JLabel(String.valueOf(purchase.getStatus())), gbc);

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
            JButton viewMoreInfoBtn = new JButton("More info");

            editStatusBtn.addActionListener(e -> {

                JFrame editStatusSection = new JFrame();

                editStatusSection.setTitle("Change Order Status");
                editStatusSection.setLayout(new MigLayout("center center, wrap, gapy 20"));
                editStatusSection.setSize(300, 300);
                editStatusSection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editStatusSection.setResizable(false);
                editStatusSection.getContentPane().setBackground(new Color(245, 245, 245));
                editStatusSection.setLocationRelativeTo(null);

                JTextField statusField = new JTextField(purchase.getStatus().name(), 15);

                JButton confirmCurrentStatusBtn = new JButton("Confirm");

                confirmCurrentStatusBtn.addActionListener(confirmUpdate -> {

                    Integer purchaseId = paymentTable.getPurchaseId(connection, purchase.getTransactionId());

                    paymentTable.updatePurchaseStatus(connection, statusField.getText(), purchaseId);

                    JOptionPane.showMessageDialog(editStatusSection, "Order was updated successfully!");

                    editStatusSection.dispose();

                    List <Purchases> updatedList = paymentTable.obtainPurchases(connection);

                    System.out.println(updatedList);

                    displayUpdatedPurchases(connection, paymentTable, updatedList);

                });

                JButton cancelBtn = new JButton("Cancel");

                cancelBtn.addActionListener(cancelPanel -> editStatusSection.dispose());

                editStatusSection.add(statusField);

                editStatusSection.add(confirmCurrentStatusBtn);

                editStatusSection.add(cancelBtn);

                editStatusSection.setVisible(true);

            });

            buttonPanel.add(editStatusBtn);
            buttonPanel.add(viewMoreInfoBtn);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            purchasePanel.add(buttonPanel, gbc);

            scrollPanel.add(purchasePanel);
            scrollPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        }


        scrollPanel.revalidate();

        scrollPanel.repaint();

    }

}
