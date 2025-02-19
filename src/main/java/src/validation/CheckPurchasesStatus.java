package src.validation;

import src.util.PurchasesPredicate;

public class CheckPurchasesStatus implements PurchasesPredicate {

    @Override
    public boolean test(String status) {

        return status.equals("PENDING_PAYMENT") ||
                status.equals("PROCESSING") ||
                status.equals("SHIPPED") ||
                status.equals("DELIVERED") ||
                status.equals("CANCELED");

    }

}
