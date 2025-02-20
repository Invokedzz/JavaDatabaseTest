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

    private Integer customerId;

    private Customer customer;

    private Address address;

    public Purchases () {}

    public Purchases (String transactionId, String productBought, Double transactionPrice, OrderStatus status,
                      LocalDate date, Integer customerId, Customer customer, Address address) {

        this.transactionId = transactionId;

        this.productBought = productBought;

        this.transactionPrice = transactionPrice;

        this.status = status;

        this.date = date;

        this.customerId = customerId;

        this.customer = customer;

        this.address = address;

    }

    public Purchases (String transactionId, String productBought, Double transactionPrice, OrderStatus status,
                      LocalDate date, Customer customer, Address address) {

        this.transactionId = transactionId;

        this.productBought = productBought;

        this.transactionPrice = transactionPrice;

        this.status = status;

        this.date = date;

        this.customer = customer;

        this.address = address;

    }


    public Purchases (String productBought, Double transactionPrice, OrderStatus status, LocalDate date, String transactionId) {

        this.productBought = productBought;

        this.transactionPrice = transactionPrice;

        this.status = status;

        this.date = date;

        this.transactionId = transactionId;

    }

    // I'm only creating this constructor in order to validate specific methods
    public Purchases (String transactionId, String productBought, Integer customerId) {

        this.transactionId = transactionId;

        this.productBought = productBought;

        this.customerId = customerId;

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

    public Integer getCustomerId () {

        return customerId;

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
