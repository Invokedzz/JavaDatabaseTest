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

        //testUserAdmin();

        testUserCustomer();

    }

    private static void testUserAdmin () {

        Admin admin = new Admin(1, "Mr.Duck", "Quak", "password", TypeUser.ADMIN);

        String hashedPassword = PassHash.generateHash(admin.getPassword());

        System.out.println(hashedPassword);

        boolean isTrue = PassHash.checkChosenHash(admin.getPassword(), hashedPassword);

        System.out.println(isTrue);

        AdminTable adTable = new AdminTable(admin);

        CheckAdmin checkAdmin = new CheckAdmin();

        adTable.updateName();

    }

    private static void testUserCustomer () {

        Customer customer = new Customer(1, "Paul", "Walker", "iwjdwds", TypeUser.CUSTOMER);

        CheckCustomers checkCustomers = new CheckCustomers();

        CustomerTable customerTable = new CustomerTable(customer);

        if (checkCustomers.test(customer)) customerTable.updateName();

    }

}
