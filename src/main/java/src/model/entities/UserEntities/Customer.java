package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public class Customer extends User {

    public Customer () {

        super();

    }

    public Customer (Integer id, String name, String lastName, String password, TypeUser typeUser) {

        super(id, name, lastName, password, typeUser);

    }

}
