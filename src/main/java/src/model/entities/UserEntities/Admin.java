package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public class Admin extends User {

    // The Admin (frontman) must have a full database access
    // They can create new Products, Categories, and stuff like that

    private String ticket;

    public Admin () {

        super ();

    }

    public Admin (String email, String password, TypeUser typeUser, String ticket) {

        super(email, password, typeUser);

        this.ticket = ticket;

    }

    public String getTicket () {

        return ticket;

    }

}
