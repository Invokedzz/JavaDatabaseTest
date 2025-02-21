package src.validation.user.login;

import javax.swing.*;
import java.awt.*;

public class LoginTicketValidation {

    public static boolean checkSentTicket (Component parent, String sentTicket, String actualTicket) {

        if (!sentTicket.equals(actualTicket)) {

            JOptionPane.showMessageDialog(parent, "Tickets are not equal!");

            return false;

        }

        return true;

    }

}
