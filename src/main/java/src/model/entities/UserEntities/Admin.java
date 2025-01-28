package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public class Admin extends User {

    // The Admin (frontman) must have a full database access
    // They can create new Products, Categories, and stuff like that

    private TypeUser typeUser;

    public Admin () {

        super ();

    }

    public Admin (String name, String lastName, String password, TypeUser typeUser) {

        super(name, lastName, password, typeUser);

    }

}
