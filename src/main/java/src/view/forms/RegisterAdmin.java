package src.view.forms;

import net.miginfocom.swing.MigLayout;
import src.security.MailServ;
import src.view.util.SendMailToTheSupposedAdmin;
import src.view.validations.user.register.RegisterEmailValidation;

import javax.swing.*;
import java.awt.*;

public class RegisterAdmin extends JFrame {

    private JTextField emailField;

    private JButton askForAccountBtn, proceedBtn;

    public RegisterAdmin () {

        setTitle("Login");
        setLayout(new MigLayout("center center, wrap, gapy 30"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(500, 300);
        setLocationRelativeTo(null);

        emailField = returnJTextFieldColumns();

        proceedBtn = new JButton("Proceed");

        askForAccountBtn = new JButton("Ask for administrator account");

        createProceedBtnAction();

        createAskForAccountBtnAction();

        addComponents();

        setVisible(true);

    }

    private void createProceedBtnAction () {

        proceedBtn.addActionListener(e -> {

            new LoginAdmin();

            dispose();

        });

    }

    private void createAskForAccountBtnAction () {

        SendMailToTheSupposedAdmin mail = new SendMailToTheSupposedAdmin();

        askForAccountBtn.addActionListener(e -> {

            String email = emailField.getText();

            if (!RegisterEmailValidation.invalidEmailMessage(this, emailField)) return;

            mail.sendSubjectAndMessage(email);

        });

    }

    private JTextField returnJTextFieldColumns () {

        return new JTextField(15);

    }

    private void addComponents () {

        add(new JLabel("If you receive the email containing the account information, proceed!"));

        add(new JLabel("Enter your email:"));

        add(emailField);

        add(proceedBtn);

        add(askForAccountBtn);

    }

}
