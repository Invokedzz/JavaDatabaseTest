package src.util;

import src.model.entities.Category;

import java.util.function.Predicate;

public interface CategoryPredicate extends Predicate <Category> {

    @Override
    boolean test (Category category);

}
