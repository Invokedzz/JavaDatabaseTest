package src.view.Forms;

import javax.swing.*;

import java.awt.*;

public class CreateUser extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton createButton;

    public CreateUser () {

            setTitle("Create Account");
            setLayout(new FlowLayout());
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            usernameField = new JTextField(15);
            passwordField = new JPasswordField(15);

            createButton = new JButton("Create");
            createButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                JOptionPane.showMessageDialog(this, "Account created for: " + username);
                dispose();
            });

            add(new JLabel("Username:"));
            add(usernameField);
            add(new JLabel("Password:"));
            add(passwordField);
            add(createButton);

            setVisible(true);

    }

}
