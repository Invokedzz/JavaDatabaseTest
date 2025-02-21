package src.validation.user.forms;

import src.security.MailServ;

import javax.swing.*;
import java.awt.*;

public class CheckIfEmailsAreTheSame {

    public static boolean areTheyTheSame (Component parent, String oldEmail, String newEmail) {

        if (verifyEmailAuth(oldEmail, newEmail)) {

            if (oldEmail.equals(newEmail)) {

                JOptionPane.showMessageDialog(parent, "Enter a different email!");

                return false;

            }

            return true;

        }

        JOptionPane.showMessageDialog(parent, "Enter valid emails!");

        return false;

    }

    private static boolean verifyEmailAuth (String oldEmail, String newEmail) {

        return MailServ.checkMail(oldEmail) && MailServ.checkMail(newEmail);

    }

}
