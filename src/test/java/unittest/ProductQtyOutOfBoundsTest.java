package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.validation.payment.ProductQtyOutOfBounds;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductQtyOutOfBoundsTest {

    private Component parent;

    @Test
    @DisplayName("Testing a case where quantity is equal to zero")
    void equalToZero () {

        Product product = new Product("Product", "100.0", "0", ProductAvailability.IN_STOCK);

        Integer quantity = Integer.parseInt(product.getQuantity());

        assertFalse(ProductQtyOutOfBounds.checkProductQty(parent, quantity));

    }

    @Test
    @DisplayName("Testing a case where quantity is different than zero")
    void differentThanZero () {

        Product product = new Product("Product", "100.0", "10", ProductAvailability.IN_STOCK);

        Integer quantity = Integer.parseInt(product.getQuantity());

        assertTrue(ProductQtyOutOfBounds.checkProductQty(parent, quantity));


    }

}