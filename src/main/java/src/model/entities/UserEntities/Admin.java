package src.model.entities.UserEntities;

import src.model.enums.TypeUser;

public class Admin extends User {

    // The Admin (frontman) must have a full database access
    // They can create new Products, Categories, and stuff like that

    private String ticket;

    public Admin () {

        super ();

    }

    public Admin (String email, String ticket, String password, TypeUser typeUser) {

        super(email, password, typeUser);

        this.ticket = ticket;

    }

    public Admin (String email, String ticket, String password) {

        super(email, password);

        this.ticket = ticket;

    }

    public String getTicket () {

        return ticket;

    }

}
