package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.view.validations.product.page.AnalyzeAvailabilityInput;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeAvailabilityInputTest {

    private Component parent;

    @Test
    @DisplayName("Testing a valid case -> IN_STOCK")
    void validCaseNumberOne () {

        String randomText = "IN_STOCK";

        assertTrue(AnalyzeAvailabilityInput.lookForAvailabilityText(parent, randomText));

    }

    @Test
    @DisplayName("Testing a valid case -> OUT_OF_STOCK")
    void validCaseNumberTwo () {

        String randomText = "OUT_OF_STOCK";

        assertTrue(AnalyzeAvailabilityInput.lookForAvailabilityText(parent, randomText));

    }

    @Test
    @DisplayName("Testing an invalid case #1")
    void invalidCaseWhereMyTextIsEmpty () {

        String randomText = "";

        assertFalse(AnalyzeAvailabilityInput.lookForAvailabilityText(parent, randomText));

    }

    @Test
    @DisplayName("Testing an invalid case #2")
    void invalidCaseWhereMyTextHaveSomeLetters () {

        String randomText = "abc";

        assertFalse(AnalyzeAvailabilityInput.lookForAvailabilityText(parent, randomText));

    }

}