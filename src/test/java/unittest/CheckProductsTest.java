package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.validation.components.CheckProducts;

import static org.junit.jupiter.api.Assertions.*;

class CheckProductsTest {

    // String name, String price, String quantity, ProductAvailability availability, Category category

    private CheckProducts checkProducts;

    @BeforeEach
    void setUp() {

        checkProducts = new CheckProducts();

    }

    @Test
    @DisplayName("Valid product")
    void testingAValidProduct () {

        Product product = new Product("Product", "200", "10", ProductAvailability.IN_STOCK);

        assertTrue(checkProducts.test(product));

    }

    @Test
    @DisplayName("Testing a product that is out of stock")
    void outOfStockProduct () {

        Product product = new Product("Product", "200", "0", ProductAvailability.OUT_OF_STOCK);

        assertTrue(checkProducts.test(product));

    }

    @Test
    @DisplayName("Testing an invalid product that is supposed to be out of stock")
    void invalidOutOfStockProduct () {

        Product product = new Product("Product", "300", "0", ProductAvailability.IN_STOCK);

        assertFalse(checkProducts.test(product));

    }

    @Test
    @DisplayName("Testing an invalid product that is supposed to be out of stock pt.2")
    void otherInvalidOutOfStockProduct () {

        Product product = new Product("Product", "300", "1", ProductAvailability.OUT_OF_STOCK);

        assertFalse(checkProducts.test(product));

    }

    @Test
    @DisplayName("Trying to insert a letter inside the quantity field")
    void whyIsThisThingHere () {

        Product product = new Product("Product", "300", "as", ProductAvailability.OUT_OF_STOCK);

        assertFalse(checkProducts.test(product));

    }

    @Test
    @DisplayName("Trying to insert a letter inside the price field")
    void whyIsThisThingHerePtTwo () {

        Product product = new Product("Product", "ball", "0", ProductAvailability.OUT_OF_STOCK);

        assertFalse(checkProducts.test(product));

    }

}