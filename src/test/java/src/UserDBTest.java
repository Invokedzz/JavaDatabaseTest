package src;

import src.model.entities.UserEntities.Admin;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.UserServices.AdminTable;
import src.model.services.UserServices.CustomerTable;
import src.security.PassHash;
import src.validation.CheckAdmin;
import src.validation.CheckCustomers;

public class UserDBTest {

    public static void main (String[] args) {

        testUserAdmin();

    }

    private static void testUserAdmin () {

        Admin admin = new Admin("Mr.Duck", "Quak", "1qaz2345", TypeUser.ADMIN);

        CheckAdmin checkAdmin = new CheckAdmin();

        AdminTable adminTable = new AdminTable(admin);

        if (checkAdmin.test(admin)) System.out.println(adminTable.checkUserById(2));

    }

    private static void testUserCustomer () {

        Customer customer = new Customer("Paul", "Walker", "iwjdwds", TypeUser.CUSTOMER);

        CheckCustomers checkCustomers = new CheckCustomers();

        CustomerTable customerTable = new CustomerTable(customer);

        if (checkCustomers.test(customer)) customerTable.display();

    }

}
