package src.model.entities.ProdEntities;

public class Category extends Product {

    private Integer id;

    private Integer tier;

    public Category () {}

    public Category ( Integer id, String name, Integer tier ) {

        super( name );

        this.id = id;

        this.tier = tier;

    }

    public Integer getId () {

        return id;

    }

    public Integer getTier() {

        return tier;

    }

    @Override
    public String toString () {

        return  getName() + " " + getTier();

    }

}
