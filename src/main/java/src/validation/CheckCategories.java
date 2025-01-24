package src.validation;

import src.exceptions.CategoryException;

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

        if (cat.getType() == TypeProduct.ELECTRONICS && cat.getTier() == 1) return true;

        else if (cat.getType() == TypeProduct.TOOLS && cat.getTier() == 2) return true;

        return cat.getType() == TypeProduct.CLOTHES && cat.getTier() == 3;

    }

}
