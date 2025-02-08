package src.view.util;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import src.exceptions.MailException;
import src.model.entities.UserEntities.Admin;
import src.model.enums.TypeUser;
import src.model.services.UserServices.AdminTable;
import src.security.AdminCredentials;
import src.security.PassHash;
import src.view.util.credentials.EmailData;

import java.sql.Connection;

public class TradeTicketForTheSupposedAdmin {

    public void sendNewTicketToAdmin (Connection connection, String email, Integer userId) {

        sendEmailToUser(connection, email, userId);

    }

    private void sendEmailToUser (Connection connection, String email, Integer userId) {

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

            mail.setSubject("New Ticket Assigned - Porky Store");

            String message = String.format("""
                    I hope this message finds you well.
                    
                    I would like to inform you that a new ticket has been created and assigned to you. Please review the details at your earliest convenience and let me know if you need any further assistance.
                    
                    Thank you for your prompt attention to this matter.
                    
                    New Ticket: %s
                    
                    Best regards,
                    Porky Store""", ticket);

            mail.setMsg(message);

            mail.send();

            createNewTicket(connection, ticket, userId);

        } catch (EmailException exception) {

            throw new MailException(exception.getMessage());

        }

    }

    private void createNewTicket (Connection connection, String ticket, Integer userId) {

        AdminTable adminTable = new AdminTable();

        adminTable.updateTicket(connection, ticket, userId);

    }

    private String obtainTicket () {

        return AdminCredentials.generateTicket();

    }

}
