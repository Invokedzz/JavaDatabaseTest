package src.security;

import org.apache.commons.validator.routines.EmailValidator;

public class MailServ {

    public static boolean checkMail (String mail) {

        return EmailValidator.getInstance().isValid(mail);

    }

}
