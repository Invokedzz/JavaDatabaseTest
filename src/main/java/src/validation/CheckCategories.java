package src.validation;

import src.exceptions.CategoryException;

import src.model.entities.Category;

import src.util.CategoryPredicate;

public class CheckCategories {

    public Category validateCategory (Category cat, CategoryPredicate predicate) {

        if (predicate.test(cat)) return cat;

        throw new CategoryException("Enter a valid category for your product!");

    }

}
