package src;

import src.model.entities.UserEntities.*;

import src.model.enums.TypeUser;
import src.validation.*;

public class Test {

    // ok don't mind me, but I'm going to test A LOT of stuff here
    // yeah, the class "Test" was created to actually test stuff
    // very original of my part

    // Integer id, String name, String lastName, String password, TypeUser typeUser

    public static void main (String[] args) {

        validateComponents();

    }

    private static void validateComponents () {

        Admin admin = new Admin(1, "Mr.Duck", "Qua", "8392", TypeUser.ADMIN);

        Customer customer = new Customer(2, "Mr.Goose", "Occk", "982398", TypeUser.CUSTOMER);

        CheckAdmin checkAdmin = new CheckAdmin();

        CheckCustomers checkCustomer = new CheckCustomers();

        System.out.println(checkAdmin.test(admin)); // it's working

        System.out.println(checkCustomer.test(customer)); // also working

    }

    private static void runDBStuff () {



    }

}
