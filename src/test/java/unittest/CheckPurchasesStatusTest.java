package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Purchases;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.OrderStatus;
import src.validation.CheckPurchasesStatus;

import static org.junit.jupiter.api.Assertions.*;

class CheckPurchasesStatusTest {

    private CheckPurchasesStatus purchasesStatus;

    @BeforeEach
    void setUp () {

        purchasesStatus = new CheckPurchasesStatus();

    }

    @Test
    @DisplayName("Testing if the payment is processing")
    void processingPayment () {

        OrderStatus status = OrderStatus.valueOf("PROCESSING");

        Purchases purchases = new Purchases("", "", 100.0,
                status, new Customer(), new Address());

        assertTrue(purchasesStatus.test(purchases));

    }

    @Test
    @DisplayName("Testing if the product was shipped")
    void checkIfProductWasShipped () {

        OrderStatus status = OrderStatus.valueOf("SHIPPED");

        Purchases purchases = new Purchases("", "", 100.0,
                status, new Customer(), new Address());

        assertTrue(purchasesStatus.test(purchases));

    }


    @Test
    @DisplayName("Testing if the product was delivered")
    void checkIfTheProductWasDeliveredProperly () {

        OrderStatus status = OrderStatus.valueOf("DELIVERED");

        Purchases purchases = new Purchases("", "", 100.0,
                status, new Customer(), new Address());

        assertTrue(purchasesStatus.test(purchases));

    }


    @Test
    @DisplayName("Testing if the payment is canceled")
    void checkIfThePaymentWasCanceled () {

        OrderStatus status = OrderStatus.valueOf("CANCELED");

        Purchases purchases = new Purchases("", "", 100.0,
                status, new Customer(), new Address());

        assertTrue(purchasesStatus.test(purchases));

    }


}