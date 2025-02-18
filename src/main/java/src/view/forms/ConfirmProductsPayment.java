package src.view.forms;

import src.model.entities.ProdEntities.Product;
import src.model.entities.ProdEntities.Purchases;
import src.model.services.PaymentServices.PaymentTable;
import src.view.page.DisplayABitMoreOfThePurchaseInfo;
import src.view.page.EditOrderStatus;
import src.view.validations.payment.PricePerQuantityExpression;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class ConfirmProductsPayment extends JFrame {

    private JPanel scrollPanel;

    public ConfirmProductsPayment (Connection connection, List <Product> productsInsideTheCart) {

        setTitle("Stored Purchase Page");
        setLayout(new BorderLayout(10, 5));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800, 600);

        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setBackground(new Color(245, 245, 245));

        displaySelectedProducts(connection, productsInsideTheCart);

        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setPreferredSize(new Dimension(400, 450));
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

    }

    private void displaySelectedProducts (Connection connection, List <Product> productsInsideTheCart) {

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        double total = calculateTotal(productsInsideTheCart);

        JButton confirmButton = new JButton("Confirm Purchase");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(50, 205, 50));
        confirmButton.setForeground(Color.BLACK);
        confirmButton.setPreferredSize(new Dimension(160, 60));
        bottomPanel.add(confirmButton);

        add(bottomPanel, BorderLayout.SOUTH);

        for (Product product : productsInsideTheCart) {

            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BorderLayout(10, 10));
            productPanel.setBackground(Color.WHITE);
            productPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

            JLabel productNameLabel = new JLabel(String.format("Product Name: %s", product.getName()));
            productNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            productNameLabel.setPreferredSize(new Dimension(100, 10));
            productPanel.add(productNameLabel, BorderLayout.NORTH);

            JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pricePanel.setBackground(Color.WHITE);

            Double price = Double.parseDouble(product.getPrice());

            JLabel priceLabel = new JLabel("Price: $" + String.format("%.2f", price));

            JTextField quantityField = new JTextField(product.getQuantity(), 10);

            pricePanel.add(priceLabel);

            pricePanel.add(new JLabel("Quantity:"));

            pricePanel.add(quantityField);

            productPanel.add(pricePanel, BorderLayout.CENTER);

            scrollPanel.add(productPanel);

        }

    }

    private double calculateTotal(List<Product> productsInsideTheCart) {

        double total = 0.0;

        for (Product product : productsInsideTheCart) {

            double price = Double.parseDouble(product.getPrice());

            int quantity = Integer.parseInt(product.getQuantity());

            total += price * quantity;

        }
        return total;

    }

}
