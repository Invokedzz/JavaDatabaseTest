package src.util;

import src.model.entities.Category;

import java.util.function.Predicate;

@FunctionalInterface
public interface CategoryPredicate extends Predicate <Category> {

    @Override
    boolean test (Category category);

}
