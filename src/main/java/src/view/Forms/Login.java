package src.view.Forms;

import javax.swing.*;

import java.awt.*;

public class Login extends JFrame {

    private JTextField usernameField;

    private JTextField lastNameField;

    private JPasswordField passwordField;

    private JButton confirmBtn;

    private JButton exitBtn;

    public Login () {

        setTitle("Login");
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        usernameField = new JTextField(15);

        lastNameField = new JTextField(15);

        passwordField = new JPasswordField(15);

        setVisible(true);

    }

}
