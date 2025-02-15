package src.validation;

import src.model.entities.ProdEntities.Purchases;
import src.model.enums.OrderStatus;
import src.util.PurchasesPredicate;

public class CheckPurchasesStatus implements PurchasesPredicate {

    @Override
    public boolean test (Purchases purchases) {

        return purchases.getStatus().equals(OrderStatus.valueOf("PROCESSING")) ||
                purchases.getStatus().equals(OrderStatus.valueOf("SHIPPED")) ||
                purchases.getStatus().equals(OrderStatus.valueOf("DELIVERED")) ||
                purchases.getStatus().equals(OrderStatus.valueOf("CANCELED"));

    }

}
