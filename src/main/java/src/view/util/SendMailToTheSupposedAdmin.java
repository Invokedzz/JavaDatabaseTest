package src.view.util;

import org.apache.commons.mail.*;
import src.exceptions.MailException;
import src.view.util.credentials.EmailData;

public class SendMailToTheSupposedAdmin {

    public void sendSubjectAndMessage (String email) {

        sendEmailToUser(email);

    }

    private void sendEmailToUser (String email) {

        try {

            Email mail = new SimpleEmail();

            mail.setHostName("smtp.gmail.com");

            mail.setSmtpPort(587);
            mail.setSSLOnConnect(false);
            mail.setStartTLSEnabled(true);

            mail.setAuthentication(EmailData.PORKY_EMAIL, EmailData.PORKY_PASSWORD);

            System.out.println(EmailData.PORKY_EMAIL);

            mail.setFrom(EmailData.PORKY_EMAIL);

            mail.addTo(email);

            mail.setSubject("Account Verification");

            mail.setMsg("Hello!");

            mail.send();


        } catch (EmailException exception) {

            throw new MailException(exception.getMessage());

        }

    }

}
