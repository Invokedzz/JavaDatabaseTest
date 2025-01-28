package src.view.Home;

import src.view.Forms.CreateUser;

import javax.swing.*;

import java.awt.*;


public class Home extends JFrame {

    public Home () {

        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800,400);
        setLayout(null);

        JLabel labelWelcome = new JLabel("Welcome to the Phantom Store!", JLabel.CENTER);

        JLabel explanationLabel = new JLabel("Create an account, and buy our products!", JLabel.CENTER);

        labelWelcome.setBounds(0, 50, getWidth(), 50);

        explanationLabel.setBounds(0, 70, getWidth(), 50);

        add(labelWelcome);

        add(explanationLabel);

        int x = (getWidth() - 250) / 2;

        int y = (getHeight() - 70) / 2;

        JButton btnCreateAccount = new JButton("Create Account");

        btnCreateAccount.setBounds(x, y, 250, 70);

        btnCreateAccount.setFont(new Font("Helvetica", Font.PLAIN, 20));

        btnCreateAccount.addActionListener(e -> new CreateUser());

        add(btnCreateAccount);

        JButton btnQuitApp = new JButton("Quit");

        btnQuitApp.setBounds(x, y + 100, 250, 70);

        btnQuitApp.setFont(new Font("Helvetica", Font.PLAIN, 15));

        btnQuitApp.addActionListener(e -> System.exit(0));

        add(btnQuitApp);

        setVisible(true);

    }

}
