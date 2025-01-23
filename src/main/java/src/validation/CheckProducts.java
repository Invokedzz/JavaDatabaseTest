package src.validation;

import src.exceptions.ProductException;

import src.model.entities.Product;

import src.util.ProductPredicate;

public class CheckProducts {

    public Product validateProduct (Product prod, ProductPredicate predicate) {

        if (predicate.test(prod)) return prod;

        throw new ProductException("Enter a valid product!");

    }

}
