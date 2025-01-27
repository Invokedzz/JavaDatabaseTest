package src;

import src.model.entities.UserEntities.Admin;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.UserServices.AdminTable;
import src.model.services.UserServices.CustomerTable;
import src.validation.CheckCustomers;

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

        Customer customer = new Customer(1, "Paul", "Walker", "iwjdwds", TypeUser.CUSTOMER);

        CheckCustomers checkCustomers = new CheckCustomers();

        CustomerTable customerTable = new CustomerTable(customer);

        if (checkCustomers.test(customer)) customerTable.insert();
        else System.out.println("Faz o L!");

    }

}
