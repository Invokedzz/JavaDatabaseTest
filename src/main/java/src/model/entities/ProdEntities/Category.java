package src.model.entities.ProdEntities;

import src.model.enums.TypeProduct;

public class Category extends Product {

    private Integer id;

    private TypeProduct type;

    private Integer tier;

    public Category () {}

    public Category (Integer id, TypeProduct type, Integer tier) {

        this.id = id;

        this.type = type;

        this.tier = tier;

    }

    public Category (Integer id) {

        this.id = id;

    }

    public Integer getId () {

        return id;

    }

    public void setId (Integer id) {

        this.id = id;

    }

    public TypeProduct getType () {

        return type;

    }

    public Integer getTier() {

        return tier;

    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type=" + type +
                ", tier=" + tier +
                '}';
    }

}
