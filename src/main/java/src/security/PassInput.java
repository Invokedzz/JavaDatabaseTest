package src.security;

public class PassInput {

    public static boolean verifyPasswordText (String password) {

        return password.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]+$");

    }

}
