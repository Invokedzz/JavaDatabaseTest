package src.view.forms;

import src.security.MailServ;
import src.view.util.SendMailToTheSupposedAdmin;

import javax.swing.*;
import java.awt.*;

public class RegisterAdmin extends JFrame {

    private JTextField emailField;

    private JButton askForAccountBtn;

    public RegisterAdmin () {

        setTitle("Login");
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(300, 220);
        setLocationRelativeTo(null);

        emailField = returnJTextFieldColumns();

        askForAccountBtn = new JButton("Ask for administrator account");

        createAskForAccountBtnAction();

        addComponents();

        setVisible(true);

    }

    private void createAskForAccountBtnAction () {

        SendMailToTheSupposedAdmin mail = new SendMailToTheSupposedAdmin();

        askForAccountBtn.addActionListener(e -> {


            String email = emailField.getText();

            System.out.println(email);

            if (!MailServ.checkMail(email)) return;

            mail.sendSubjectAndMessage(email);

        });

    }

    private JTextField returnJTextFieldColumns () {

        return new JTextField(15);

    }

    private void addComponents () {

        add(new JLabel("Enter your email:"));

        add(emailField);

        add(askForAccountBtn);

    }

}
