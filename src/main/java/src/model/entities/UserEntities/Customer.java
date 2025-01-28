package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public class Customer extends User {

    public Customer () {

        super();

    }

    public Customer (String name, String lastName, String password, TypeUser typeUser) {

        super(name, lastName, password, typeUser);

    }

}
