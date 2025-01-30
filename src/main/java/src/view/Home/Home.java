package src.view.Home;

import src.view.Forms.CreateUser;
import src.view.Forms.Login;

import javax.swing.*;

import java.awt.*;


public class Home extends JFrame {

    public Home () {

        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(800,600);
        setLayout(null);

        JLabel labelWelcome = createCenteredLabel("Welcome to the Porky Store!", 50, 50, new Font("Arial", Font.BOLD, 30), Color.BLACK);

        JLabel explanationLabel = createCenteredLabel("Create an account, and buy our products!", 70, 100, new Font("Arial", Font.PLAIN, 16), Color.DARK_GRAY);

        JLabel otherLabelForExplanation = createCenteredLabel("Or, if you have an account, log in!", 110, 100, new Font("Arial", Font.PLAIN, 16), Color.DARK_GRAY);

        labelWelcome.setBounds(0, 50, getWidth(), 50);

        explanationLabel.setBounds(0, 70, getWidth(), 150);

        otherLabelForExplanation.setBounds(0, 100, getWidth(), 200);

        add(labelWelcome);

        add(explanationLabel);

        add(otherLabelForExplanation);

        int x = (getWidth() - 250) / 2;

        int y = (getHeight() - 70) / 2;

        JButton btnCreateAccount = new JButton("Create Account");

        btnCreateAccount.setBounds(x, y, 250, 70);

        btnCreateAccount.setFont(new Font("Helvetica", Font.PLAIN, 15));

        btnCreateAccount.addActionListener(e -> new CreateUser());

        add(btnCreateAccount);

        JButton btnLogin = new JButton("Login");

        btnLogin.setBounds(x, y + 100, 250, 70);

        btnLogin.setFont(new Font("Helvetica", Font.PLAIN, 15));

        btnLogin.addActionListener(e -> new Login());

        add(btnLogin);

        JButton btnQuitApp = new JButton("Quit");

        btnQuitApp.setBounds(x, y + 200, 250, 70);

        btnQuitApp.setFont(new Font("Helvetica", Font.PLAIN, 15));

        btnQuitApp.addActionListener(e -> System.exit(0));

        add(btnQuitApp);

        setVisible(true);

    }

    private JLabel createCenteredLabel(String text, int top, int height, Font font, Color color) {

        JLabel label = new JLabel(text, JLabel.CENTER);

        label.setBounds(0, top, getWidth(), height);

        label.setFont(font);

        label.setForeground(color);

        return label;

    }

}
