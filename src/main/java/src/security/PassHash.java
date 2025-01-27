package src.security;

import org.mindrot.jbcrypt.BCrypt;

public class PassHash {

    public static String generateHash (String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }

    public static boolean checkChosenHash (String password, String storedHash) {

        return BCrypt.checkpw(password, storedHash);

    }

}
