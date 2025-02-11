package src.validation;

import src.model.entities.ProdEntities.Category;

import src.model.enums.TypeProduct;

import src.util.CategoryPredicate;

public class CheckCategories implements CategoryPredicate {

    // REMEMBER:
    // Name: Electronics, tier: 1
    //  Name: Tools, tier: 2
    // Name: Clothes, tier: 3

    @Override
    public boolean test (Category cat) {

        return false;

    }

}
