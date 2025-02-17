package src.model.entities.ProdEntities;

import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.OrderStatus;

import java.time.LocalDate;

public class Purchases {

    private String transactionId;

    private String productBought;

    private Double transactionPrice;

    private OrderStatus status;

    private LocalDate date;

    private Customer customer;

    private Address address;

    public Purchases () {}

    public Purchases (String transactionId, String productBought, Double transactionPrice, OrderStatus status, LocalDate date, Customer customer, Address address) {

        this.transactionId = transactionId;

        this.productBought = productBought;

        this.transactionPrice = transactionPrice;

        this.status = status;

        this.date = date;

        this.customer = customer;

        this.address = address;

    }

    public String getTransactionId () {

        return transactionId;

    }

    public String getProductBought () {

        return productBought;

    }

    public Double getTransactionPrice () {

        return transactionPrice;

    }

    public OrderStatus getStatus () {

        return status;

    }

    public LocalDate getDate () {

        return date;

    }

    public Customer getCustomer () {

        return customer;

    }

    public Address getAddress () {

        return address;

    }

    @Override
    public String toString() {
        return "Purchases{" +
                "transactionId='" + transactionId + '\'' +
                ", productBought='" + productBought + '\'' +
                ", transactionPrice=" + transactionPrice +
                ", status=" + status +
                ", date=" + date +
                ", customer=" + customer +
                ", address=" + address +
                '}';
    }

}
