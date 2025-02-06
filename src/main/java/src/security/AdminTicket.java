package src.security;

import java.security.*;

public class AdminTicket {

    public static String generateTicket () {

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        SecureRandom secureRandom = new SecureRandom();

        StringBuilder ticket = new StringBuilder();

        for (int index = 0; index < 8; index++) {

            int randomIndex = secureRandom.nextInt(chars.length());

            ticket.append(chars.charAt(randomIndex));

        }

        return ticket.toString();

    }

}
