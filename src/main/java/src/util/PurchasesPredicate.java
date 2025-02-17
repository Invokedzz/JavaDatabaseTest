package src.util;

import src.model.entities.ProdEntities.Purchases;

import java.util.function.Predicate;

public interface PurchasesPredicate extends Predicate <String> {

    @Override
    boolean test(String status);

}
