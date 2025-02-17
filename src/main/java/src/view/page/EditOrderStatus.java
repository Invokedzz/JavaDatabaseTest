package src.view.page;

import src.model.services.PaymentServices.PaymentTable;
import src.view.validations.payment.CheckOrderStatusUpdate;

import javax.swing.*;
import java.sql.Connection;

public class EditOrderStatus extends JFrame {

    public EditOrderStatus (Connection connection, String transactionId, String status) {

        PaymentTable paymentTable = new PaymentTable();

        Integer purchaseId = paymentTable.getPurchaseId(connection, transactionId);

        if (!CheckOrderStatusUpdate.orderUpdate(this, status)) return;

        paymentTable.updatePurchaseStatus(connection, status, purchaseId);

        JOptionPane.showMessageDialog(this, "Status was updated successfully!");

    }

}
