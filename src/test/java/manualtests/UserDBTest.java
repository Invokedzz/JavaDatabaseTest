package manualtests;

import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Admin;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.UserServices.AdminTable;
import src.model.services.UserServices.CustomerTable;
import src.validation.CheckAdmin;
import src.validation.CheckCustomers;

public class UserDBTest {

    public static void main (String[] args) {

        testUserAdmin();

    }

    private static void testUserAdmin () {

        Admin admin = new Admin("Mr.Duck", "mrduck@gmail.com",
                "1qaz2345", TypeUser.ADMIN);

        CheckAdmin checkAdmin = new CheckAdmin();

        AdminTable adminTable = new AdminTable(admin);

        adminTable.insert();

    }

    private static void testUserCustomer () {

        Customer customer = new Customer("Paul", "paulwalker@hotmail.com",
                "iwjdwds", TypeUser.CUSTOMER);

        CheckCustomers checkCustomers = new CheckCustomers();

        CustomerTable customerTable = new CustomerTable(customer, new Address());

        if (checkCustomers.test(customer)) customerTable.display();

    }

}
