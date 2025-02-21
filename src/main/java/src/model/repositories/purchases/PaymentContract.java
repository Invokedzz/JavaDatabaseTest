package src.model.repositories.purchases;

import src.model.entities.ProdEntities.Purchases;

import java.sql.Connection;
import java.util.List;

public interface PaymentContract {

    List <Purchases> obtainPurchases (Connection connection);

    Integer getPurchaseId (Connection connection, String transactionId);

    void updatePurchaseStatus (Connection connection, String status, Integer purchaseId);

    List <Purchases> obtainUserPurchases (Connection connection);

}
