package src.model.services.PaymentServices;

import src.db.DB;
import src.db.DbException;
import src.model.entities.ProdEntities.Purchases;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.OrderStatus;
import src.model.services.DatabaseGeneralContract;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentTable implements DatabaseGeneralContract, PaymentContract {

    private Purchases purchases;

    private PreparedStatement statement;

    private ResultSet set;

    public PaymentTable () {}

    public PaymentTable (Purchases purchases) {

        this.purchases = purchases;

    }

    @Override
    public void insert() {

        try {

            Connection connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO \"Purchases\".\"PurchasesRegister\" " +
                            "(transaction_id, product_bought, transaction_price, order_status, payer_cep, payer_address, payer_email, " +
                            "payer_housenumber, payer_housecomplement, purchase_date, id_customer) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, purchases.getTransactionId());

            statement.setString(2, purchases.getProductBought());

            statement.setDouble(3, purchases.getTransactionPrice());

            statement.setString(4, purchases.getStatus().name());

            statement.setString(5, purchases.getAddress().getCEP());

            statement.setString(6, purchases.getAddress().getLabel());

            statement.setString(7, purchases.getCustomer().getEmail());

            statement.setString(8, purchases.getAddress().getNumber());

            statement.setString(9, purchases.getAddress().getComplement());

            statement.setDate(10, Date.valueOf(purchases.getDate()));

            statement.setInt(11, purchases.getCustomerId());

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void deleteComponent(Connection connection, Integer id) {

        try {

            statement = connection.prepareStatement(
                    "DELETE FROM \"Purchases\".\"PurchasesRegister\" WHERE id = ?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public List <Purchases> obtainPurchases(Connection connection) {

        List <Purchases> purchasesList = new ArrayList<>();

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM \"Purchases\".\"PurchasesRegister\""
            );

            set = statement.executeQuery();

            while (set.next()) {

                String transactionId = set.getString("transaction_id");

                String productBought = set.getString("product_bought");

                Double transactionPrice = set.getDouble("transaction_price");

                OrderStatus status = OrderStatus.valueOf(set.getString("order_status"));

                LocalDate date = set.getDate("purchase_date").toLocalDate();

                String userCep = set.getString("payer_cep");

                String userAddress = set.getString("payer_address");

                String email = set.getString("payer_email");

                String houseNumber = set.getString("payer_housenumber");

                String complement = set.getString("payer_housecomplement");

                purchasesList.add(new Purchases(transactionId, productBought, transactionPrice, status,
                        date, new Customer(email), new Address(userCep, userAddress, houseNumber, complement)));

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return purchasesList;

    }

    @Override
    public Integer getPurchaseId(Connection connection, String transactionId) {

        try {

            statement = connection.prepareStatement(
                    "SELECT id FROM \"Purchases\".\"PurchasesRegister\" WHERE transaction_id = ?"
            );

            statement.setString(1, transactionId);

            set = statement.executeQuery();

            if (set.next()) return set.getInt("id");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return null;

    }

    @Override
    public void updatePurchaseStatus(Connection connection, String status, Integer purchaseId) {

        try {

            statement = connection.prepareStatement(
                    "UPDATE \"Purchases\".\"PurchasesRegister\" SET order_status = ? WHERE id = ?"
            );

            statement.setString(1, status);

            statement.setInt(2, purchaseId);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public List<Purchases> obtainUserPurchases(Connection connection) {

        List <Purchases> productsBoughtByUser = new ArrayList<>();

        try {

            statement = connection.prepareStatement(
                    """
                            SELECT
                                product_bought,
                                transaction_price,
                                order_status,
                                purchase_date,
                                transaction_id
                            FROM
                                "Purchases"."PurchasesRegister"
                            JOIN
                                "User"."Customer" c
                            ON
                                id_customer = c.id;"""
            );

            set = statement.executeQuery();

            while (set.next()) {

                String productBought = set.getString("product_bought");

                Double transactionPrice = set.getDouble("transaction_price");

                OrderStatus status = OrderStatus.valueOf(set.getString("order_status"));

                LocalDate date = set.getDate("purchase_date").toLocalDate();

                String transactionId = set.getString("transaction_id");

                productsBoughtByUser.add(new Purchases(productBought, transactionPrice, status, date, transactionId));

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return productsBoughtByUser;

    }

}
