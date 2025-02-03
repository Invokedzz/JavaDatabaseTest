package src.view.validations.user.register;

import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.validation.CheckAddress;
import src.validation.CheckCustomers;

import javax.swing.*;
import java.awt.*;

public class RegisterGeneralInfoValidation {

    public static boolean invalidCustomerMessage (Component parent, CheckCustomers checkCustomers, Customer customer) {

        if (!checkCustomers.test(customer)) {

            JOptionPane.showMessageDialog(parent,
                    "Something went wrong. Check your inputs!");

            return false;

        }

        return true;

    }

    public static boolean invalidAddressMessage (Component parent, CheckAddress checkAddress, Address address) {

        if (!checkAddress.test(address)) {

            JOptionPane.showMessageDialog(parent, "Enter a valid address!");

            return false;

        }

        return true;

    }

}
