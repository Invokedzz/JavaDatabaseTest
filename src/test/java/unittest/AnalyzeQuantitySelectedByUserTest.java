package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.view.validations.payment.AnalyzeQuantitySelectedByUser;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeQuantitySelectedByUserTest {

    private static Integer quantity;

    private static Integer selectedQuantity;

    private Component parent;

    @Test
    @DisplayName("Testing a valid case")
    void validQuantitySelected () {

        quantity = 8;

        selectedQuantity = 6;

        assertTrue(AnalyzeQuantitySelectedByUser.isThisQuantityCorrect(parent, quantity, selectedQuantity));

    }

    @Test
    @DisplayName("Testing an invalid case")
    void invalidQuantitySelected () {

        quantity = 8;

        selectedQuantity = 10;

        assertFalse(AnalyzeQuantitySelectedByUser.isThisQuantityCorrect(parent, quantity, selectedQuantity));

    }

    @Test
    @DisplayName("Let's suppose the quantity and the selected quantity are the same")
    void sameQuantities () {

        quantity = 10;

        selectedQuantity = 10;

        assertTrue(AnalyzeQuantitySelectedByUser.isThisQuantityCorrect(parent, quantity, selectedQuantity));

    }

}