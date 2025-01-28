package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public abstract class User {

    private String name;

    private String lastName;

    private String email;

    private String password;

    private TypeUser typeUser;

    public User () {}

    public User (String name, String lastName, String email, String password, TypeUser typeUser) {

        this.name = name;

        this.lastName = lastName;

        this.email = email;

        this.password = password;

        this.typeUser = typeUser;

    }

    public String getName () {

        return name;

    }

    public String getLastName () {

        return lastName;

    }

    public String getEmail () {

        return email;

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
                .append(", LAST NAME: ").append(getLastName())
                .append(", TYPE: ").append(getTypeUser());

        return sb.toString();

    }

}
