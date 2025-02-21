package src.view.page;

import src.model.entities.ProdEntities.Product;
import src.view.forms.ConfirmProductsPayment;
import src.validation.payment.CheckIfCartIsEmpty;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class CartPage extends JFrame {

    private final JPanel productPanel;

    public CartPage (Connection connection, List <Product> productsInsideTheCart, Integer userId) {

        setTitle("Cart");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800, 600);

        JPanel searchPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        displayProducts(connection, productsInsideTheCart, userId);

        setVisible(true);

    }

    private void displayProducts (Connection connection, List <Product> productsInsideTheCart, Integer userId) {

        productPanel.removeAll();

        JButton buyProductsBtn = new JButton("Confirm buy");

        buyProductsBtn.addActionListener(e -> {

            if (!CheckIfCartIsEmpty.isCartEmpty(this, productsInsideTheCart)) return;

            new ConfirmProductsPayment(connection, productsInsideTheCart, userId);

        });

        add(buyProductsBtn, BorderLayout.SOUTH);

        if (!productsInsideTheCart.isEmpty()) {

            for (Product product : productsInsideTheCart) {

                JPanel productItemPanel = new JPanel();
                productItemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                productItemPanel.setBackground(new Color(255, 255, 255));
                productItemPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
                productItemPanel.setPreferredSize(new Dimension(750, 110));
                productItemPanel.setMaximumSize(new Dimension(750, 110));
                productItemPanel.setMinimumSize(new Dimension(750, 110));

                JLabel productLabel = new JLabel("<html><strong style='color:black;'>" + product.getName() + "</strong><br>" +
                        "<span style='color:black;'>Price: $" + product.getPrice() + "</span><br>" + "<span style='color:black;'>Quantity:: " + product.getQuantity() + "</span><br>" +
                        "<span style='color:black;'>Availability: " + product.getAvailability()  + "</span><br></html>");
                productLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                productLabel.setForeground(Color.BLACK);

                JButton deleteButton = new JButton("Remove");
                deleteButton.setBackground(new Color(255, 76, 76));
                deleteButton.setForeground(Color.BLACK);

                deleteButton.addActionListener(e -> {

                    productsInsideTheCart.remove(product);

                    displayProducts(connection, productsInsideTheCart, userId);

                });

                productItemPanel.add(deleteButton);

                productItemPanel.add(productLabel);

                productPanel.add(productItemPanel);

            }

        } else {

            JPanel emptyCartPanel = new JPanel();

            emptyCartPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            emptyCartPanel.setBackground(new Color(255, 255, 255));

            JLabel jLabel = new JLabel("Your cart looks empty. Why don't you add some products inside of it?");

            emptyCartPanel.add(jLabel);

            productPanel.add(emptyCartPanel);

            emptyCartPanel.setVisible(true);

        }

        productPanel.revalidate();

        productPanel.repaint();

    }

}
