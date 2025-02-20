package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.view.util.ConvertCentsToReal;
import org.hamcrest.Matchers;
import src.validation.CheckProducts;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

class ConvertCentsToRealTest {

    @Test
    @DisplayName("Testing a valid matching")
    void validMatch () {

        Product product = new Product("random", "100.0", "1", ProductAvailability.IN_STOCK);

        Double productPrice = Double.parseDouble(product.getPrice());

        BigDecimal valueA = new BigDecimal("10000");

        assertThat(valueA, Matchers.comparesEqualTo(ConvertCentsToReal.moneyConverter(productPrice)));

    }

    @Test
    @DisplayName("Testing a valid matching + checkProducts validation")
    void matchingPlusValidation () {

        Product product = new Product("Basketball", "200.0", "10", ProductAvailability.IN_STOCK);

        CheckProducts checkProducts = new CheckProducts();

        Double productPrice = Double.parseDouble(product.getPrice());

        BigDecimal comparableValue = new BigDecimal("20000");

        if (checkProducts.test(product)) assertThat(comparableValue, Matchers.comparesEqualTo(ConvertCentsToReal.moneyConverter(productPrice)));

    }

}