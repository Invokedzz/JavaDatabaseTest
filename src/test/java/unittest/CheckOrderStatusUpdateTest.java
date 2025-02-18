package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Purchases;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.OrderStatus;
import src.view.validations.payment.CheckOrderStatusUpdate;

import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CheckOrderStatusUpdateTest {

    // String transactionId, String productBought, Double transactionPrice, OrderStatus status, Customer customer, Address address

    private Component parent;

    @Test
    @DisplayName("Testing a valid case")
    void validOrderStatusCase () {

        Purchases purchases = new Purchases("10283", "Product",
                100.0, OrderStatus.valueOf("PROCESSING"), LocalDate.now(), new Customer(), new Address());

        assertTrue(CheckOrderStatusUpdate.orderUpdate(parent, purchases.getStatus().name()));

    }

}