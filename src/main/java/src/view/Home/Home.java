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
        setSize(800,600);
        setLayout(null);

        JLabel labelWelcome = new JLabel("Welcome to the Porky Store!", JLabel.CENTER);

        JLabel explanationLabel = new JLabel("Create an account, and buy our products!", JLabel.CENTER);

        JLabel otherLabelForExplanation = new JLabel("Or, If you have an account, log in!", JLabel.CENTER);

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

}
