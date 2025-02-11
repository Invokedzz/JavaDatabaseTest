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

    public void setType (TypeProduct type) {

        this.type = type;

    }

    public Integer getTier() {

        return tier;

    }

    public void setTier (Integer tier) {

        this.tier = tier;

    }

    @Override
    public String toString () {

        StringBuilder sb;

        sb = new StringBuilder();

        sb.append("TYPE: ").append(getType())
                .append(" TIER: ").append(getTier())
                .append(" ID: ").append(getId());

        return sb.toString();

    }

}
