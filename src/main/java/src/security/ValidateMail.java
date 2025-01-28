package src.security;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidateMail {

    public static boolean checkMail (String mail) {

        return EmailValidator.getInstance().isValid(mail);

    }

}
