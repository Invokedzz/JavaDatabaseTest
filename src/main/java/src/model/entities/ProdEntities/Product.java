package src.model.entities.ProdEntities;

import src.model.enums.ProductAvailability;

public class Product {

    private String name;

    private Double price;

    private ProductAvailability availability;

    private Integer quantity;

    private Category category;

    public Product () {}

    public Product (String name) {

        this.name = name;

    }

    public Product (String name, Double price, Integer quantity, ProductAvailability availability, Category category) {

        this.name = name;

        this.price = price;

        this.quantity = quantity;

        this.availability = availability;

        this.category = category;

    }

    public String getName () {

        return name;

    }

    public Double getPrice () {

        return price;

    }

    public Integer getQuantity () {

        return quantity;

    }

    public ProductAvailability getAvailability () {

        return availability;

    }

    public void setAvailability (ProductAvailability availability) {

        this.availability = availability;

    }

    public Category getCategory () {

        return category;

    }

    @Override
    public String toString () {

        StringBuilder sb;

        sb = new StringBuilder();

        sb.append("NAME: ").append(getName())
                .append(" PRICE: ").append(getPrice())
                .append(" QUANTITY: ").append(getQuantity())
                .append(" AVAILABILITY: ").append(getAvailability());

        return sb.toString();

    }

}
