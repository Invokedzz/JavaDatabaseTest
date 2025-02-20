package src.model.entities.ProdEntities;

import src.model.enums.ProductAvailability;

public class Product {

    private String name;

    private String price;

    private ProductAvailability availability;

    private String quantity;

    private String description;

    private String img;

    private String productCode;

    private Category category;

    public Product () {}

    public Product (String name, String price, String quantity, ProductAvailability availability, Category category,
                    String productCode, String img) {

        this.name = name;

        this.price = price;

        this.quantity = quantity;

        this.availability = availability;

        this.category = category;

        this.productCode = productCode;

        this.img = img;

    }

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

    public Product (String name, String price, String quantity, ProductAvailability availability, String img) {

        this.name = name;

        this.price = price;

        this.quantity = quantity;

        this.availability = availability;

        this.img = img;

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

    public void setQuantity (String quantity) {

        this.quantity = quantity;

    }

    public ProductAvailability getAvailability () {

        return availability;

    }

    public void setAvailability (ProductAvailability availability) {

        this.availability = availability;

    }

    public String getImg () {

        return img;

    }

    public String getProductCode () {

        return productCode;

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
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", productCode='" + productCode + '\'' +
                ", category=" + category +
                '}';
    }

}
