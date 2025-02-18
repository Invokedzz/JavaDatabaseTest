package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.view.validations.product.page.CheckIfProductIsInsideTheCart;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfProductIsInsideTheCartTest {

    private Component parent;

    @Test
    @DisplayName("Testing a case where all the products are different from each other")
    void validCase () {

        List <Product> cart = new ArrayList<>();

        cart.add(new Product("Ball", "1000.0", "1", ProductAvailability.OUT_OF_STOCK));

        Product product = new Product("All", "21.0", "2", ProductAvailability.IN_STOCK);

        assertTrue(CheckIfProductIsInsideTheCart.lookInsideTheCart(parent, cart, product));

    }

    @Test
    @DisplayName("Testing a case where the same product tries to enter twice")
    void invalidCase () {

        List <Product> cart = new ArrayList<>();

        cart.add(new Product("Ball", "1000.0", "1", ProductAvailability.OUT_OF_STOCK));

        Product product = new Product("Ball", "21.0", "2", ProductAvailability.IN_STOCK);

        assertFalse(CheckIfProductIsInsideTheCart.lookInsideTheCart(parent, cart, product));

    }

}