package src.security;


public class LoginServ {

    public static boolean isLoginValid (String email, String password, String storedPassword) {

        return MailServ.checkMail(email) && PassHash.checkChosenHash(password, storedPassword);

    }

}
