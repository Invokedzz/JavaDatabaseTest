package src.model.entities.ProdEntities;

import src.model.enums.ProductAvailability;

public class Product {

    private String name;

    private Double price;

    private ProductAvailability availability;

    private Category category;

    public Product () {}

    public Product (String name) {

        this.name = name;

    }

    public Product (String name, Double price, ProductAvailability availability, Category category) {

        this.name = name;

        this.price = price;

        this.availability = availability;

        this.category = category;

    }

    public String getName () {

        return name;

    }

    public Double getPrice () {

        return price;

    }

    public ProductAvailability getAvailability () {

        return availability;

    }

    public Category getCategory () {

        return category;

    }

    @Override
    public String toString () {

        StringBuilder sb;

        sb = new StringBuilder();

        return sb.toString();

    }

}
