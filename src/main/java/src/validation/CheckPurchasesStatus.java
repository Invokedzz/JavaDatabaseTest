package src.validation;

import src.util.OrderStatusPredicate;

public class CheckPurchasesStatus implements OrderStatusPredicate {

    @Override
    public boolean test(String status) {

        return status.equals("PENDING_PAYMENT") ||
                status.equals("PROCESSING") ||
                status.equals("SHIPPED") ||
                status.equals("DELIVERED") ||
                status.equals("CANCELED");

    }

}
