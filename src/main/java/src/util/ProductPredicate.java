package src.util;

import src.model.entities.Product;

import java.util.function.Predicate;

public interface ProductPredicate extends Predicate <Product> {

    @Override
    boolean test (Product p);

}
