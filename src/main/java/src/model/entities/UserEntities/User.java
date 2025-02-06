package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public abstract class User {

    private String name;

    private String email;

    private String password;

    private TypeUser typeUser;

    public User () {}

    public User (String name, String email, String password, TypeUser typeUser) {

        this.name = name;

        this.email = email;

        this.password = password;

        this.typeUser = typeUser;

    }

    public User (String name, String email, String password) {

        this.name = name;

        this.email = email;

        this.password = password;

    }

    public User (String email, String password, TypeUser typeUser) {

        this.email = email;

        this.password = password;

        this.typeUser = typeUser;

    }

    public User (String email, String password) {

        this.email = email;

        this.password = password;

    }

    public String getName () {

        return name;

    }

    public String getEmail () {

        return email;

    }

    public String getPassword () {

        return password;

    }

    public void setPassword (String password) {

        this.password = password;

    }

    public TypeUser getTypeUser () {

        return typeUser;

    }

    @Override
    public String toString () {

        StringBuilder sb;

        sb = new StringBuilder();

        sb.append("NAME: ").append(getName())
                .append(", TYPE: ").append(getTypeUser());

        return sb.toString();

    }

}
