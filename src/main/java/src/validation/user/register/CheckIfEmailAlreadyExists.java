package src.validation.user.register;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CheckIfEmailAlreadyExists {

    public static boolean verifyEmailExistence (Component parent, String emailSentByUser, List <String> storedEmails) {

        for (String storedEmail : storedEmails) {

            if (storedEmail.equals(emailSentByUser)) {

                JOptionPane.showMessageDialog(parent, "This email is already registered!");

                return false;

            }

        }

        return true;

    }

}
