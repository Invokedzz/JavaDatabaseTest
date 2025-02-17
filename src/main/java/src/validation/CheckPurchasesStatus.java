package src.validation;

import src.model.entities.ProdEntities.Purchases;
import src.model.enums.OrderStatus;
import src.util.PurchasesPredicate;

public class CheckPurchasesStatus implements PurchasesPredicate {

    /*    PENDING_PAYMENT,

    PROCESSING,

    SHIPPED,

    DELIVERED,

    CANCELED

    */

    @Override
    public boolean test(String status) {

        return status.equals("PENDING_PAYMENT") ||
                status.equals("PROCESSING") ||
                status.equals("SHIPPED") ||
                status.equals("DELIVERED") ||
                status.equals("CANCELED");

    }

}
