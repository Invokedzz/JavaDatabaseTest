package src.util;

import src.model.entities.ProdEntities.Product;

import java.util.function.Predicate;

@FunctionalInterface
public interface ProductPredicate extends Predicate <Product> {

    @Override
    boolean test (Product p);

}
