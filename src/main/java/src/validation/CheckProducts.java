package src.validation;

import src.exceptions.ProductException;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.security.VerifyNumericalInputs;
import src.util.ProductPredicate;

public class CheckProducts implements ProductPredicate {

    // String name, Double price, ProductAvailability availability, Category cat

    // don't forget to change availability according to quantity
    // if quantity > 0, then availability == IN_STOCK
    // if quantity == 0, then availability == OUT_OF_STOCK

    @Override
    public boolean test (Product product) {

        if (product.getAvailability().equals(ProductAvailability.valueOf("OUT_OF_STOCK"))) {

            return product.getName().length() > 3 &&
                    product.getName().length() <= 20 &&
                    VerifyNumericalInputs.numericalInput(product.getQuantity()) &&
                    VerifyNumericalInputs.numericalInput(product.getPrice()) &&
                    product.getQuantity().equals("0") &&
                    !product.getPrice().isEmpty();

        }

        return product.getName().length() > 3 &&
                product.getName().length() <= 20 &&
                VerifyNumericalInputs.numericalInput(product.getPrice()) &&
                product.getAvailability().equals(ProductAvailability.valueOf("IN_STOCK")) &&
                VerifyNumericalInputs.numericalInput(product.getQuantity()) &&
                !product.getQuantity().equals("0") &&
                !product.getQuantity().isEmpty() &&
                !product.getPrice().isEmpty();

    }

}
