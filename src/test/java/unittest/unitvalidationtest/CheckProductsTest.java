package unittest.unitvalidationtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.exceptions.ProductException;
import src.model.entities.ProdEntities.Category;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.validation.CheckProducts;

import static org.junit.jupiter.api.Assertions.*;

class CheckProductsTest {

    private static Product product;

    private static Product outOfStockProduct;

    private static Product productException;

    private static CheckProducts checkProducts;

    @BeforeEach
    void setUp() {

        checkProducts = new CheckProducts();

        product = new Product("Ok Computer", 400.0, 10, ProductAvailability.IN_STOCK, new Category());

        outOfStockProduct = new Product("OutOfStock", 400.0, 0, ProductAvailability.IN_STOCK, new Category());

        productException = new Product("", 400.0, 10, ProductAvailability.IN_STOCK, new Category());

    }

    @Test
    @DisplayName("Confirm to me if that product is true")
    void isProductTrue () {

        assertTrue(checkProducts.test(product));

    }

    @Test
    @DisplayName("Confirm to me if that product is false")
    void isProductFalse () {

        assertFalse(checkProducts.test(outOfStockProduct));

    }

    @Test
    @DisplayName("Confirm to me if this product throws an exception")
    void thisProductMustThrowAnException () {

        assertThrows(ProductException.class, () -> checkProducts.test(productException));

    }

}