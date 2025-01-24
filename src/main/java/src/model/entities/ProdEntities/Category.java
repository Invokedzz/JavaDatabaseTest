package src.model.entities.ProdEntities;

import src.model.enums.TypeProduct;

public class Category extends Product {

    private Integer id;

    private TypeProduct type;

    private Integer tier;

    public Category () {}

    public Category (Integer id, TypeProduct type, Integer tier ) {

        this.id = id;

        this.type = type;

        this.tier = tier;

    }

    public Integer getId () {

        return id;

    }

    public TypeProduct getType () {

        return type;

    }

    public Integer getTier() {

        return tier;

    }

    @Override
    public String toString () {

        StringBuilder sb;

        sb = new StringBuilder();

        return sb.toString();

    }

}
