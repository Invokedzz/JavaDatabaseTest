package src.security;

public class VerifyNumericalInputs {

    public static boolean numericalInput (String input) {

        return input.matches("^\\d*\\.?\\d+$");

    }

}
