package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.validation.product.page.CheckIfProductsHaveTheSameName;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfProductsHaveTheSameNameTest {

    private static String firstName, secondName;

    private Component parent;

    @Test
    @DisplayName("Let's suppose they have the same name")
    void productsWithTheSameName () {

        firstName = "Controller";

        secondName = "Controller";

        assertFalse(CheckIfProductsHaveTheSameName.verifyProductsName(parent, firstName, secondName));

    }

    @Test
    @DisplayName("Let's suppose they don't have the same name")
    void productsWithADifferentName () {

        firstName = "Controller123";

        secondName = "Controller";

        assertTrue(CheckIfProductsHaveTheSameName.verifyProductsName(parent, firstName, secondName));

    }

}