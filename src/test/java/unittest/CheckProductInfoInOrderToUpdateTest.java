package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.validation.CheckProducts;
import src.view.validations.product.page.CheckProductInfoInOrderToUpdate;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckProductInfoInOrderToUpdateTest {

    private Component parent;

    @Test
    @DisplayName("Testing an invalid product -> the message must be displayed on the screen")
    void invalidProduct () {

        Product invalidProduct = new Product("Product", "100", "0", ProductAvailability.IN_STOCK);

        assertFalse(CheckProductInfoInOrderToUpdate.validateProduct(parent, invalidProduct));

    }

    @Test
    @DisplayName("Testing a valid product")
    void validProduct () {

        Product validProduct = new Product("Product", "100", "1", ProductAvailability.IN_STOCK);

        assertTrue(CheckProductInfoInOrderToUpdate.validateProduct(parent, validProduct));

    }

}