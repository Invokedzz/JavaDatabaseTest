package src.validation.util;

import src.model.entities.ProdEntities.Category;

import java.util.function.Predicate;

@FunctionalInterface
public interface CategoryPredicate extends Predicate <Category> {

    @Override
    boolean test (Category cat);

}
