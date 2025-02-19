package src.view.forms;

import net.miginfocom.swing.MigLayout;
import src.api.usages.MercadoPagoComponents;
import src.api.usages.PaymentSession;
import src.model.entities.ProdEntities.Product;
import src.model.entities.ProdEntities.Purchases;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.OrderStatus;
import src.model.services.PaymentServices.PaymentTable;
import src.model.services.ProdServices.ProductTable;
import src.model.services.UserServices.CustomerTable;
import src.view.page.DisplayABitMoreOfThePurchaseInfo;
import src.view.page.EditOrderStatus;
import src.view.util.GenerateQrCode;
import src.view.util.SendEmailAfterSuccessfulPurchase;
import src.view.validations.payment.AnalyzeQuantitySelectedByUser;
import src.view.validations.payment.PricePerQuantityExpression;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmProductsPayment extends JFrame {

    private JPanel scrollPanel;

    private JTextField quantityField;

    private JButton confirmButton;

    public ConfirmProductsPayment (Connection connection, List <Product> productsInsideTheCart, Integer userId) {

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
        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setPreferredSize(new Dimension(400, 450));
        add(scrollPane, BorderLayout.CENTER);

        displaySelectedProducts(connection, productsInsideTheCart, userId);

        setVisible(true);

    }

    private void displaySelectedProducts (Connection connection, List <Product> productsInsideTheCart, Integer userId) {

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        confirmButton = new JButton("Confirm Purchase");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(50, 205, 50));
        confirmButton.setForeground(Color.BLACK);
        confirmButton.setPreferredSize(new Dimension(160, 60));

        add(bottomPanel, BorderLayout.SOUTH);

        Map<Product, JTextField> productQuantityFields = new HashMap<>();

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

            pricePanel.add(priceLabel);

            JTextField quantityField = new JTextField(product.getQuantity(), 10);

            productQuantityFields.put(product, quantityField);

            pricePanel.add(new JLabel("Quantity:"));

            pricePanel.add(quantityField);

            productPanel.add(pricePanel, BorderLayout.CENTER);

            scrollPanel.add(productPanel);

        }

        CustomerTable customerTable = new CustomerTable();

        Customer customer = customerTable.obtainUserProperties(connection, userId);

        ProductTable productTable = new ProductTable();

        Address address = customerTable.obtainAddressProperties(connection, userId);

        confirmButton.addActionListener(e -> {

            Double total = 0.0;

            Map<Product, Integer> updatedQuantities = new HashMap<>();

            for (Product product : productsInsideTheCart) {

                JTextField quantityField = productQuantityFields.get(product);

                // Obtemos a quantidade que o usuário selecionou
                Integer selectedQty = Integer.parseInt(quantityField.getText());

                // Calculamos a quantidade restante (estoque atualizado)
                Integer totalQty = Integer.parseInt(product.getQuantity()) - selectedQty;

                // Armazenamos a quantidade atualizada no mapa
                updatedQuantities.put(product, totalQty);

                System.out.println("Quantidade selecionada: " + selectedQty);
                System.out.println("Quantidade restante: " + totalQty);

                // Calcula o total do preço para o produto
                total += selectedQty * Double.parseDouble(product.getPrice());

                System.out.println("Total: " + total);

                if (!AnalyzeQuantitySelectedByUser.isThisQuantityCorrect(this, Integer.parseInt(product.getQuantity()), Integer.parseInt(quantityField.getText()))) return;

            }

            Double finalTotal = total;

            System.out.println(finalTotal);

            JFrame paymentInfoScreen = new JFrame("Payment");
            paymentInfoScreen.setLayout(new MigLayout("center center, wrap 1, gapy 30"));
            paymentInfoScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            paymentInfoScreen.getContentPane().setBackground(new Color(245, 245, 245));
            paymentInfoScreen.setLocationRelativeTo(null);
            paymentInfoScreen.setResizable(false);
            paymentInfoScreen.setSize(500,550);

            MercadoPagoComponents.getPaymentParams(customer.getEmail(), String.valueOf(finalTotal));

            ImageIcon qrCodeImage = GenerateQrCode.create(PaymentSession.qrCode);

            JLabel setUpQrCode = new JLabel(qrCodeImage);

            JTextField productLink = new JTextField(PaymentSession.paymentLink,25);

            JButton confirmPayment = new JButton("Confirm Payment");

            confirmPayment.addActionListener(proceedWithPayment -> {

                JOptionPane.showMessageDialog(this, "Payment was done successfully!");

                List <String> productNames = new ArrayList<>();

                for (Product products : productsInsideTheCart) {

                    productNames.add(products.getName());

                }

                Purchases purchases = new Purchases(PaymentSession.paymentId, productNames.toString(),
                        finalTotal, OrderStatus.PROCESSING, LocalDate.now(), userId, customer, address);

                PaymentTable paymentTable = new PaymentTable(purchases);

                paymentTable.insert();

                SendEmailAfterSuccessfulPurchase.purchaseEmail(customer.getEmail(), purchases.getTransactionId(),
                        purchases.getDate().toString(), String.valueOf(purchases.getTransactionPrice()));

                for (Product product : productsInsideTheCart) {

                    Integer totalQty = updatedQuantities.get(product);

                    Integer productId = productTable.obtainProductId(connection, product.getName());

                    productTable.updateProductQuantity(connection, totalQty, productId);

                }

                dispose();

            });

            paymentInfoScreen.add(new JLabel("Copy the QR Code or the URL, and pay for the product!"));

            paymentInfoScreen.add(setUpQrCode);

            paymentInfoScreen.add(productLink);

            paymentInfoScreen.add(confirmPayment);

            paymentInfoScreen.setVisible(true);


        });


        bottomPanel.add(confirmButton);

    }

    private double calculateTotal(List<Product> productsInsideTheCart, Integer qtySelectedByUser) {

        double total = 0.0;

        for (Product product : productsInsideTheCart) {

            double price = Double.parseDouble(product.getPrice());

            int quantity = qtySelectedByUser;

            total += PricePerQuantityExpression.calculusResult(price, quantity);

        }

        return total;

    }

}
