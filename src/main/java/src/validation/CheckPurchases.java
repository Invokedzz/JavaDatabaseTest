package src.validation;

import src.model.entities.ProdEntities.Purchases;
import src.security.VerifyNumericalInputs;
import src.util.PurchasesPredicate;

public class CheckPurchases implements PurchasesPredicate {

    @Override
    public boolean test(Purchases purchases) {

        return VerifyNumericalInputs.numericalInput(purchases.getTransactionId()) &&
                purchases.getProductBought().length() > 3 &&
                purchases.getProductBought().length() <= 20 &&
                purchases.getCustomerId() > 0;

    }

}
