package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Purchases;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.OrderStatus;
import src.view.validations.payment.CheckOrderStatusUpdate;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckOrderStatusUpdateTest {

    // String transactionId, String productBought, Double transactionPrice, OrderStatus status, Customer customer, Address address

    private Component parent;

    @Test
    @DisplayName("Testing a valid case")
    void validOrderStatusCase () {

        Purchases purchases = new Purchases("10283", "Product",
                100.0, OrderStatus.valueOf("PROCESSING"), new Customer(), new Address());

        assertTrue(CheckOrderStatusUpdate.orderUpdate(parent, purchases.getStatus().name()));

    }

    @Test
    @DisplayName("Testing an invalid case")
    void invalidOrderStatusCase () {

        // IllegalArgumentException -> enum

        try {

            Purchases purchases = new Purchases("10283", "Product",
                    100.0, OrderStatus.valueOf(""), new Customer(), new Address());

            assertFalse(CheckOrderStatusUpdate.orderUpdate(parent, purchases.getStatus().name()));

        } catch (IllegalArgumentException exception) {

            throw new IllegalArgumentException(exception.getMessage());

        }

    }

}