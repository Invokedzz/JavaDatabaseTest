package src.model.entities.ProdEntities;

public class Product {

    private String name;

    private Double price;

    private Category category;

    public Product () {}

    public Product ( String name ) {

        this.name = name;

    }

    public Product (String name, Double price, Category category) {

        this.name = name;

        this.price = price;

        this.category = category;

    }

    public String getName () {

        return name;

    }

    public Double getPrice () {

        return price;

    }

    public Category getCategory () {

        return category;

    }

    @Override
    public String toString () {

        return getName() + " " + getPrice();

    }

}
