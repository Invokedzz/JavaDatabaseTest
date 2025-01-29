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
    public boolean test (Product p) {

        // I'm lazy, so I'm going to validate ALL this stuff with if/else >:(

        ProductAvailability type;

        if (p.getQuantity() > 0) {

            type = ProductAvailability.IN_STOCK;

            p.setAvailability(type);

        }

        else {

            type = ProductAvailability.OUT_OF_STOCK;

            p.setAvailability(type);

        }

        if (p.getAvailability().equals(type) &&
                p.getName().length() > 1 &&
                p.getName().length() <= 12 &&
                p.getPrice() > 0 &&
                p.getQuantity() > 0) return true;

        else if (p.getAvailability().equals(type) &&
                p.getName().length() > 1
                && p.getName().length() <= 12
                && p.getPrice() > 0 &&
                p.getQuantity() == 0) {

            System.out.println("Product out of stock!");

            return false;

        }

        throw new ProductException("Something went wrong");

    }

}
