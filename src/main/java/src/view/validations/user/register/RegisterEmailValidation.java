package src.view.validations.user.register;

import src.security.MailServ;

import javax.swing.*;
import java.awt.*;

public class RegisterEmailValidation extends Component {

    public static boolean invalidEmailMessage (Component parent, JTextField emailField) {

        if (!MailServ.checkMail(emailField.getText())) {

            JOptionPane.showMessageDialog(parent, "Enter a valid email!");

            return false;

        }

        return true;

    }

}
