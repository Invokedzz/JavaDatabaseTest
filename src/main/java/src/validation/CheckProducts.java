package src.validation;

import src.exceptions.ProductException;

import src.model.entities.ProdEntities.Product;

import src.util.ProductPredicate;

public class CheckProducts implements ProductPredicate {

    @Override
    public boolean test (Product p) {

        return false;

    }

}
