package src.validation;

import src.exceptions.ProductException;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.util.ProductPredicate;

public class CheckProducts implements ProductPredicate {

    // String name, Double price, ProductAvailability availability, Category cat

    // don't forget to change availability according to quantity
    // if quantity > 0, then availability == IN_STOCK
    // if quantity == 0, then availability == OUT_OF_STOCK

    @Override
    public boolean test (Product product) {

        if (product.getAvailability().equals(ProductAvailability.OUT_OF_STOCK)) {

            return product.getName().length() > 3 &&
                    product.getName().length() <= 20;

        }

        return product.getName().length() > 3 &&
                product.getName().length() <= 20;

    }

}
