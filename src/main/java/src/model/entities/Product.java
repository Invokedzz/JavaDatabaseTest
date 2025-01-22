package src.model.entities;

public class Product {

    private Integer id;

    private String name;

    private Double price;

    private Category category;

    public Product () {}

    public Product ( Integer id, String name, Double price ) {

        this.id = id;

        this.name = name;

        this.price = price;

    }

    public Integer getId () {

        return id;

    }

    public Double getPrice () {

        return price;

    }

    public String getName () {

        return name;

    }

    public Category getCategory () {

        return category;

    }

    @Override
    public String toString () {

        return getId() + " " + getName() + " "
                + getPrice() + " " + getCategory();

    }

}
