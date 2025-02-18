package src.model.entities.ProdEntities;

import src.model.enums.ProductAvailability;

public class Product {

    private String name;

    private String price;

    private ProductAvailability availability;

    private String quantity;

    private Category category;

    // productImg, productCode, productDescription -> I'm going to add those variables after some time

    public Product () {}

    public Product (String name, String price, String quantity, ProductAvailability availability, Category category) {

        this.name = name;

        this.price = price;

        this.quantity = quantity;

        this.availability = availability;

        this.category = category;

    }

    public Product (String name, String price, String quantity, ProductAvailability availability) {

        this.name = name;

        this.price = price;

        this.quantity = quantity;

        this.availability = availability;

    }

    public String getName () {

        return name;

    }

    public String getPrice () {

        return price;

    }

    public String getQuantity () {

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
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", availability=" + availability +
                ", quantity='" + quantity + '\'' +
                ", category=" + category +
                '}';
    }

}
