package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Purchases;
import src.validation.components.CheckPurchases;

import static org.junit.jupiter.api.Assertions.*;

class CheckPurchasesTest {

    // String transactionId, String productBought, Integer customerId

    private CheckPurchases checkPurchases;

    @BeforeEach
    void setUp () {

        checkPurchases = new CheckPurchases();

    }

    @Test
    @DisplayName("Testing a valid case")
    void validPurchaseCase () {

        Purchases purchases = new Purchases("16253647", "Basketball", 30);

        assertTrue(checkPurchases.test(purchases));

    }

    @Test
    @DisplayName("Testing an invalid case -> transaction id containing letters")
    void transactionIdContainingLetters () {

        Purchases purchases = new Purchases("1a6b253647", "Basketball", 30);

        assertFalse(checkPurchases.test(purchases));

    }

    @Test
    @DisplayName("Testing an invalid case #2 -> product length is invalid")
    void productBoughtNotHavingTheProperLength () {

        Purchases purchases = new Purchases("16253647", "Bas", 30);

        assertFalse(checkPurchases.test(purchases));

    }

    @Test
    @DisplayName("Testing an invalid case #3 -> customer is is lower than zero")
    void customerIdIsLowerThanZero () {

        Purchases purchases = new Purchases("16253647", "Basketball", -1);

        assertFalse(checkPurchases.test(purchases));

    }


}