package src.view.page;

import src.model.entities.ProdEntities.Purchases;
import src.model.repositories.purchases.PaymentTable;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class UserPurchases extends JFrame {

    private JPanel productPanel;

    public UserPurchases (Connection connection) {

        setTitle("Products Bought");
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

        PaymentTable paymentTable = new PaymentTable();
        List <Purchases> productsBoughtByUser = paymentTable.obtainUserPurchases(connection);

        displayProducts(productsBoughtByUser);

        setVisible(true);

    }

    private void displayProducts(List<Purchases> productsBoughtByUser) {
        productPanel.removeAll();

        for (Purchases purchases : productsBoughtByUser) {

            JPanel productItemPanel = new JPanel();
            productItemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            productItemPanel.setBackground(new Color(255, 255, 255));
            productItemPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            productItemPanel.setPreferredSize(new Dimension(750, 110));
            productItemPanel.setMaximumSize(new Dimension(750, 110));
            productItemPanel.setMinimumSize(new Dimension(750, 110));

            JLabel productLabel = new JLabel("<html><strong style='color:black;'>" + purchases.getProductBought() + "</strong><br>" +
                    "<span style='color:black;'>Paid: $" + purchases.getTransactionPrice() + "</span><br>" + "<span style='color:black;'>Status: " + purchases.getStatus().name() + "</span><br>" +
                    "<span style='color:black;'>Date: " + purchases.getDate().toString()  + "</span><br>" + "<span style='color:black;'>Transaction Id: " + purchases.getTransactionId() + "</span></html>");
            productLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            productLabel.setForeground(Color.BLACK);

            productItemPanel.add(productLabel);

            productPanel.add(productItemPanel);
        }

        productPanel.revalidate();
        productPanel.repaint();

    }

}


