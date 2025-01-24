package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public abstract class User {

    private Integer id;

    private String name;

    private String lastName;

    private String password;

    private TypeUser typeUser;

    public User () {}

    public User (Integer id, String name, String lastName, String password, TypeUser typeUser) {

        this.id = id;

        this.name = name;

        this.lastName = lastName;

        this.password = password;

        this.typeUser = typeUser;

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

    public TypeUser getTypeUser () {

        return typeUser;

    }

    @Override
    public String toString () {

        StringBuilder sb;

        sb = new StringBuilder();

        sb.append("NAME: ").append(getName())
                .append(" ").append(getLastName())
                .append(" TYPE: ").append(getTypeUser());

        return sb.toString();

    }

}
