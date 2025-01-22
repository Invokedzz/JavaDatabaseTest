package src.validation;

import src.exceptions.ProductException;

import src.model.entities.Product;

import src.util.ProductPredicate;

public class CheckProducts {

    private Product product;

    public CheckProducts () {}

    public CheckProducts (Product product) {

        this.product = product;

    }

    public Product getProduct () {

        return product;

    }

    public Product validateProduct (Product product, ProductPredicate predicate) {

        if (predicate.test(getProduct())) return product;

        throw new ProductException("Enter a valid product!");

    }

}
