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

        TypeProduct type = assignValidType(cat);

        Integer id = assignValidId(cat);

        Integer tier = assignValidTier(cat);

        return (cat.getType().equals(type) &&
                ((tier.equals(1) && id.equals(1)) ||
                        (tier.equals(2) && id.equals(2)) ||
                        (tier.equals(3) && id.equals(3))));

    }

    private TypeProduct assignValidType (Category cat) {

        if (cat.getId() == 1 && cat.getTier() == 1) {

            cat.setType(TypeProduct.ELECTRONICS);

            return cat.getType();

        }

        else if (cat.getId() == 2 && cat.getTier() == 2) {

            cat.setType(TypeProduct.TOOLS);

            return cat.getType();

        }

        cat.setType(TypeProduct.CLOTHES);

        return cat.getType();

    }

    private Integer assignValidId (Category cat) {

        if (cat.getTier() == 1 && cat.getType().equals(TypeProduct.ELECTRONICS)) {

            cat.setId(1);

            return cat.getId();

        }

        else if (cat.getTier() == 2 && cat.getType().equals(TypeProduct.TOOLS)) {

            cat.setId(2);

            return cat.getId();

        }

        cat.setId(3);

        return cat.getId();

    }

    private Integer assignValidTier (Category cat) {

        if (cat.getId() == 1 && cat.getType().equals(TypeProduct.ELECTRONICS)) {

            cat.setTier(1);

            return cat.getTier();

        }

        else if (cat.getId() == 2 && cat.getType().equals(TypeProduct.TOOLS)) {

            cat.setTier(2);

            return cat.getTier();

        }

        cat.setTier(3);

        return cat.getTier();

    }

}
