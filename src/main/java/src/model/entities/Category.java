package src.model.entities;

public class Category {

    private Integer id;

    private String name;

    private Integer tier;

    public Category () {}

    public Category ( Integer id, String name, Integer tier ) {

        this.id = id;

        this.name = name;

        this.tier = tier;

    }

    public Integer getId () {

        return id;

    }

    public String getName () {

        return name;

    }

    public Integer getTier() {

        return tier;

    }

    @Override
    public String toString () {

        return getId() + " " + getName() + " " + getTier();

    }

}
