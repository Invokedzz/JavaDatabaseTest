package src.validation;

import src.exceptions.CategoryException;

import src.model.entities.Category;

import src.util.CategoryPredicate;

public class CheckCategories {

    private Category category;

    public CheckCategories () {}

    public CheckCategories (Category category) {

        this.category = category;

    }

    public Category getCategory () {

        return category;

    }

    public Category validateCategory (Category category, CategoryPredicate predicate) {

        if (predicate.test(category)) return category;

        throw new CategoryException("Enter a valid category for your product!");

    }

}
