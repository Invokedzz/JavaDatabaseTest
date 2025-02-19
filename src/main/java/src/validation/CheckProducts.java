package src.validation;

import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.security.VerifyNumericalInputs;
import src.util.ProductPredicate;

public class CheckProducts implements ProductPredicate {

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
