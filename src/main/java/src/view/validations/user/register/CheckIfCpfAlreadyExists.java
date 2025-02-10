package src.view.validations.user.register;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CheckIfCpfAlreadyExists {

    public static boolean verifyCpfExistence (Component parent, String cpfSentByUser, List<String> storedCpf) {

        for (String storedEmail : storedCpf) {

            if (storedEmail.equals(cpfSentByUser)) {

                JOptionPane.showMessageDialog(parent, "This cpf is already registered!");

                return false;

            }

        }

        return true;

    }

}
