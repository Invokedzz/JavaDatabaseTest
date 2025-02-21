package src.model.services;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import src.exceptions.MailException;
import src.model.services.credentials.EmailData;

public class SendEmailAfterSuccessfulPurchase {

    public static void purchaseEmail (String email, String transactionId, String date, String amount) {

        emailContent(email, transactionId, date, amount);

    }

    private static void emailContent (String email, String transactionId, String date, String amount) {

        try {

            Email mail = new SimpleEmail();

            mail.setHostName("smtp.gmail.com");

            mail.setSmtpPort(587);
            mail.setSSLOnConnect(false);
            mail.setStartTLSEnabled(true);

            mail.setAuthentication(EmailData.PORKY_EMAIL, EmailData.PORKY_PASSWORD);

            mail.setFrom(EmailData.PORKY_EMAIL);

            mail.addTo(email);

            mail.setSubject("Your Purchase is Complete! Thank You for Shopping with Porky Store!");

            String message = String.format("""
                   \s
                    Thank you for your purchase at Porky Store! We’re excited to let you know that your order has been successfully processed.
                   \s
                    Here are the details of your order:
                   \s
                    Order Number: %s
                    Order Date: %s
                    Total Amount: $%s                   \s
                    If you have any questions or need assistance, feel free to contact us.
                   \s
                    We truly appreciate your business and look forward to serving you again soon!
                   \s
                    Best regards,
                    The Porky Store Team
                   \s
                    Porky Store
                    1234 Market Street, Suite 567
                    Springfield, IL 62701""", transactionId, date, amount);

            mail.setMsg(message);

            mail.send();


        } catch (EmailException exception) {

            throw new MailException(exception.getMessage());

        }

    }

}
