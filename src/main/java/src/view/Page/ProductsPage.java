package src.view.Page;

import javax.swing.*;

public class ProductsPage extends JFrame {

    public ProductsPage (Integer userId) {

        setTitle("Products");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800,600);
        setLayout(null);

        add(new JLabel("Email:"));

        JLabel greetingsLabel = new JLabel("Feel free to buy our products, " + userId, JLabel.CENTER);

        greetingsLabel.setBounds(0, 50, getWidth(), 50);

        add(greetingsLabel);

        setVisible(true);

    }

}
