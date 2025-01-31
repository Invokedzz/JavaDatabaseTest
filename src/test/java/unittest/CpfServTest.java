package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.security.CpfServ;

import static org.junit.jupiter.api.Assertions.*;

class CpfServTest {

    private static String randomCpf;

    @Test
    @DisplayName("Testing a valid CPF")
    void testingAValidCpf () {

        randomCpf = "02760448436";

        assertTrue(CpfServ.validateUserCpf(randomCpf));

    }

    @Test
    @DisplayName("Testing an invalid CPF")
    void testingAnInvalidCpf () {

        randomCpf = "330112445";

        assertFalse(CpfServ.validateUserCpf(randomCpf));

    }

    @Test
    @DisplayName("Testing a String that contains letters and numbers")
    void testingAStringThatContainsLettersAndNumbers () {

        randomCpf = "3A0B1C4D5";

        assertFalse(CpfServ.validateUserCpf(randomCpf));

    }

    @Test
    @DisplayName("Testing a String that contains only letters")
    void testingAStringThatContainsOnlyLetters () {

        randomCpf = "ABCDEFGHI";

        assertFalse(CpfServ.validateUserCpf(randomCpf));

    }

    @Test
    @DisplayName("Testing an empty CPF")
    void testingAnEmptyCPF () {

        randomCpf = "";

        assertFalse(CpfServ.validateUserCpf(randomCpf));

    }

}