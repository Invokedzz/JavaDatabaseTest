package src.view.page;

import net.miginfocom.swing.MigLayout;
import src.api.usages.MercadoPagoComponents;
import src.api.usages.PaymentSession;
import src.model.entities.ProdEntities.Category;
import src.model.entities.ProdEntities.Product;
import src.model.entities.ProdEntities.Purchases;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.OrderStatus;
import src.model.enums.ProductAvailability;
import src.model.services.PaymentServices.PaymentTable;
import src.model.services.ProdServices.ProductTable;
import src.model.services.UserServices.CustomerTable;
import src.view.util.GenerateQrCode;
import src.view.validations.payment.ProductQtyOutOfBounds;
import src.view.validations.product.page.AreYouSureThisProductExists;
import java.util.List;
import src.view.validations.product.page.CheckProductInfoInOrderToUpdate;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class ProductPage extends JFrame {

    private JTextField searchField;
    private JPanel productPanel;

    public ProductPage (Connection connection, Integer userId) {

            setTitle("Product Page");
            setLayout(new BorderLayout(10, 10));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setBackground(new Color(245, 245, 245));
            setLocationRelativeTo(null);
            setResizable(false);
            setSize(800, 600);


            JPanel searchPanel = new JPanel(new BorderLayout());
            searchField = new JTextField();
            searchField.setToolTipText("Search for products");
            JButton searchButton = new JButton("Search");

            JButton cartBtn = new JButton();

            ImageIcon cartIcon = new ImageIcon("/Users/samunoinv/IdeaProjects/JavaDatabaseTest/src/main/java/src/view/img/cart - PorkyStore.png");


            cartBtn.setIcon(new ImageIcon(cartIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

            searchPanel.add(cartBtn, BorderLayout.WEST);

            searchPanel.add(searchField, BorderLayout.CENTER);
            searchPanel.add(searchButton, BorderLayout.EAST);

            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.add(searchPanel, BorderLayout.CENTER);

            add(topPanel, BorderLayout.NORTH);

            productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane(productPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);

            ProductTable productTable = new ProductTable();
            List <Product> productList = productTable.displayProducts(connection);

            displayProducts(productList, connection, userId);

            searchButton.addActionListener(e -> {

                java.util.List<Product> productsFound = productTable.searchForProducts(connection, searchField.getText());

                if (!AreYouSureThisProductExists.searchForProductInAList(this, productsFound)) return;

                displayProducts(productsFound, connection, userId);

            });

            setVisible(true);

        }

        private void displayProducts(List<Product> productList, Connection connection, Integer userId) {
            productPanel.removeAll();

            CustomerTable customerTable = new CustomerTable();

            ProductTable productTable = new ProductTable();

            for (Product product : productList) {

                Integer productId = productTable.obtainProductId(connection, product.getName());

                JPanel productItemPanel = new JPanel();
                productItemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                productItemPanel.setBackground(new Color(255, 255, 255));
                productItemPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
                productItemPanel.setPreferredSize(new Dimension(750, 80));
                productItemPanel.setMaximumSize(new Dimension(750, 80));
                productItemPanel.setMinimumSize(new Dimension(750, 80));

                JLabel productLabel = new JLabel("<html><strong style='color:black;'>" + product.getName() + "</strong><br>" +
                        "<span style='color:black;'>Price: " + product.getPrice() + "</span><br>" + "<span style='color:black;'>Quantity: " + product.getQuantity() + "</span></html>");
                productLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                productLabel.setForeground(Color.BLACK);

                productItemPanel.add(productLabel);

                JButton buyProductBtn = new JButton("Buy Product");
                buyProductBtn.setBackground(new Color(0, 150, 136));
                buyProductBtn.setForeground(Color.BLACK);
                buyProductBtn.setFocusPainted(false);
                buyProductBtn.setFont(new Font("Arial", Font.BOLD, 12));

                buyProductBtn.addActionListener(e -> {

                    JFrame buyDirectlyPage = new JFrame("Buy Product");

                    buyDirectlyPage.setLayout(new MigLayout("center center, wrap 1, gapy 30"));
                    buyDirectlyPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    buyDirectlyPage.getContentPane().setBackground(new Color(245, 245, 245));
                    buyDirectlyPage.setLocationRelativeTo(null);
                    buyDirectlyPage.setResizable(false);
                    buyDirectlyPage.setSize(400,400);

                    Product selectedProduct = productTable.obtainProductProperties(connection, productId);

                    Customer customer = customerTable.obtainUserProperties(connection, userId);

                    Address address = customerTable.obtainAddressProperties(connection, userId);

                    JTextField productName = new JTextField(selectedProduct.getName(), 15);

                    JTextField productPrice = new JTextField(selectedProduct.getPrice(), 15);

                    int totalQtyStored = Integer.parseInt(selectedProduct.getQuantity());

                    if (!ProductQtyOutOfBounds.checkProductQty(this, totalQtyStored)) return;

                    int nowTakeOffThisAmount = totalQtyStored - 1;

                    Integer total = totalQtyStored - nowTakeOffThisAmount;

                    JTextField productQuantity = new JTextField(String.valueOf(total), 15);

                    JButton confirmBuy = new JButton("Confirm buy");

                    confirmBuy.addActionListener(confirmEvent -> {

                        JFrame paymentInfoScreen = new JFrame("Payment");
                        paymentInfoScreen.setLayout(new MigLayout("center center, wrap 1, gapy 30"));
                        paymentInfoScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        paymentInfoScreen.getContentPane().setBackground(new Color(245, 245, 245));
                        paymentInfoScreen.setLocationRelativeTo(null);
                        paymentInfoScreen.setResizable(false);
                        paymentInfoScreen.setSize(500,550);

                        MercadoPagoComponents.getPaymentParams(customer.getEmail(), selectedProduct.getPrice());

                        ImageIcon qrCodeImage = GenerateQrCode.create(PaymentSession.qrCode);

                        JLabel setUpQrCode = new JLabel(qrCodeImage);

                        JTextField productLink = new JTextField(PaymentSession.paymentLink,25);

                        JButton confirmPayment = new JButton("Confirm Payment");

                        confirmPayment.addActionListener(proceedWithPayment -> {

                            JOptionPane.showMessageDialog(this, "Payment was done successfully!");

                            Integer updatedQuantity = Integer.parseInt(product.getQuantity()) - total;

                            Double totalPrice = Double.parseDouble(product.getPrice());

                            Purchases purchases = new Purchases(PaymentSession.paymentId, product.getName(),
                                    totalPrice, OrderStatus.PROCESSING, customer, address);

                            PaymentTable paymentTable = new PaymentTable(purchases);

                            productTable.updateProductQuantity(connection, updatedQuantity, productId);

                            paymentTable.insert();

                            dispose();

                        });

                        paymentInfoScreen.add(new JLabel("Copy the QR Code or the URL, and pay for the product!"));

                        paymentInfoScreen.add(setUpQrCode);

                        paymentInfoScreen.add(productLink);

                        paymentInfoScreen.add(confirmPayment);

                        paymentInfoScreen.setVisible(true);

                    });

                    buyDirectlyPage.add(new JLabel("Name:"));

                    buyDirectlyPage.add(productName);

                    buyDirectlyPage.add(new JLabel("Price:"));

                    buyDirectlyPage.add(productPrice);

                    buyDirectlyPage.add(new JLabel("Quantity:"));

                    buyDirectlyPage.add(productQuantity);

                    buyDirectlyPage.add(confirmBuy);

                    buyDirectlyPage.setVisible(true);

                });

                productItemPanel.add(buyProductBtn);

                JButton addToCartBtn = new JButton("Add to Cart");
                addToCartBtn.setBackground(new Color(255, 69, 58));
                addToCartBtn.setForeground(Color.BLACK);
                addToCartBtn.setFocusPainted(false);
                addToCartBtn.setFont(new Font("Arial", Font.BOLD, 12));

                addToCartBtn.addActionListener(e -> {

                });

                productItemPanel.add(addToCartBtn);

                productPanel.add(productItemPanel);
            }


            productPanel.revalidate();
            productPanel.repaint();

    }

}
