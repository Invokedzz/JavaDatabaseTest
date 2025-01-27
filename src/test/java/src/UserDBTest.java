package src;

import src.model.entities.UserEntities.Admin;
import src.model.entities.UserEntities.Customer;
import src.model.services.UserServices.AdminTable;
import src.model.services.UserServices.CustomerTable;

public class UserDBTest {

    public static void main (String[] args) {

       // testUserAdmin();

        testUserCustomer();

    }

    private static void testUserAdmin () {

        Admin admin = new Admin();

        AdminTable adTable = new AdminTable(admin);

        adTable.deleteAll();

    }

    private static void testUserCustomer () {

        Customer customer = new Customer();

        CustomerTable customerTable = new CustomerTable(customer);

        customerTable.deleteAll();

    }

}
