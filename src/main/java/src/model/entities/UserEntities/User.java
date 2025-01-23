package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public abstract class User {

    private Integer id;

    private String name;

    private String lastName;

    private String password;

    public User () {}

    public User (Integer id, String name, String lastName, String password) {

        this.id = id;

        this.name = name;

        this.lastName = lastName;

        this.password = password;

    }

    public String getName () {

        return name;

    }

    public String getLastName () {

        return lastName;

    }

    public String getPassword () {

        return password;

    }

}
