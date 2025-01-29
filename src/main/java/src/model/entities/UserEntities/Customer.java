package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public class Customer extends User {

    public Customer () {

        super();

    }

    public Customer (String email, String password) {

        super(email, password);

    }

    public Customer (String name, String lastName, String email, String password, TypeUser typeUser) {

        super(name, lastName, email, password, typeUser);

    }

}
