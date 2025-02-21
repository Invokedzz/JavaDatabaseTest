package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.validation.product.page.CheckNumericalInput;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckNumericalInputTest {

    private Component parent;

    @Test
    @DisplayName("Testing a case where the input is totally composed by numerical values")
    void numericalInput () {

        String value = "123456789";

        assertTrue(CheckNumericalInput.isThisValueNumerical(parent, value));

    }

    @Test
    @DisplayName("Testing a case where the input is composed by numerical and alphabetical values")
    void numbersAndLettersMakingPartOfTheInputText () {

        String value = "1a2b3c4d5e6f7g8h9i";

        assertFalse(CheckNumericalInput.isThisValueNumerical(parent, value));

    }

    @Test
    @DisplayName("Testing a case where the input is composed by alphabetical values")
    void alphabeticalInput () {

        String value = "abcdefghi";

        assertFalse(CheckNumericalInput.isThisValueNumerical(parent, value));

    }

    @Test
    @DisplayName("Testing a case where the input is composed by numbers and special characters")
    void numbersAndSpecialCharacters () {

        String value = "1@#345%987";

        assertFalse(CheckNumericalInput.isThisValueNumerical(parent, value));

    }

    @Test
    @DisplayName("Testing a case where the input is empty")
    void emptyInput () {

        String value = "";

        assertFalse(CheckNumericalInput.isThisValueNumerical(parent, value));

    }

}