package src.view.util;

import org.apache.commons.mail.*;
import src.exceptions.MailException;
import src.model.entities.UserEntities.Admin;
import src.model.enums.TypeUser;
import src.model.services.UserServices.AdminTable;
import src.security.AdminCredentials;
import src.security.PassHash;
import src.view.util.credentials.EmailData;

import javax.swing.*;

public class SendMailToTheSupposedAdmin {

    public void sendSubjectAndMessage (String email) {

        if (askForPermissionInOrderToSend(email)) sendEmailToUser(email);

    }

    private void sendEmailToUser (String email) {

        String password = obtainPassword();

        String ticket = obtainTicket();

        try {

            Email mail = new SimpleEmail();

            mail.setHostName("smtp.gmail.com");

            mail.setSmtpPort(587);
            mail.setSSLOnConnect(false);
            mail.setStartTLSEnabled(true);

            mail.setAuthentication(EmailData.PORKY_EMAIL, EmailData.PORKY_PASSWORD);

            mail.setFrom(EmailData.PORKY_EMAIL);

            mail.addTo(email);

            mail.setSubject("Access Information for Porky Store Admin");

            String message = String.format("""
            Welcome to the team! Below, you will find the necessary information to access the admin dashboard and manage operations for Porky Store:
        
            Admin Password: %s
        
            Ticket Information:
            
            Your ticket reference for future support inquiries is: %s
        
            Please make sure to keep this information secure. If you have any questions or need assistance, feel free to reach out to our support team at porkystore@gmail.com.
        
            We look forward to having you on board and wish you success in your new role!
        
            Best regards,
            Porky Store Team""", password, ticket);

            mail.setMsg(message);

            mail.send();

            String hashPassword = PassHash.generateHash(password);

            createNewAdmin(email, ticket, hashPassword);

        } catch (EmailException exception) {

            throw new MailException(exception.getMessage());

        }

    }

    private void createNewAdmin (String email, String ticket, String password) {

        Admin admin = new Admin(email, ticket, password, TypeUser.ADMIN);

        AdminTable adminTable = new AdminTable(admin);

        adminTable.insert();

    }

    private String obtainTicket () {

        return AdminCredentials.generateTicket();

    }

    private String obtainPassword () {

        return AdminCredentials.generatePassword();

    }

    private boolean askForPermissionInOrderToSend (String email) {

        int response = JOptionPane.showConfirmDialog(

                null,
                "Do you wish to authorize the request from " + email + "?",
                "Confirm the send of the email",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE

        );

        return response == JOptionPane.YES_OPTION;

    }

}
