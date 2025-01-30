package manualtests;

import src.model.entities.ProdEntities.*;
import src.model.entities.UserEntities.*;
import src.model.enums.*;
import src.validation.*;
import src.security.MailServ;

public class ValidationsTest {

    // ok don't mind me, but I'm going to test A LOT of stuff here
    // yeah, the class "Test" was created to actually test stuff
    // very original of my part

    // Integer id, String name, String lastName, String password, TypeUser type

    public static void main (String[] args) {

        validateComponents();

        validateCategories();

        validateAdmin();

        validateCustomer();

        validateProducts();

        isEmailValid();

    }

    private static void validateComponents () {

        Admin admin = new Admin("Mr.Duck", "", "8392", TypeUser.ADMIN);

        Customer customer = new Customer("Mr.Goose", "","982398", TypeUser.CUSTOMER);

        CheckAdmin checkAdmin = new CheckAdmin();

        CheckCustomers checkCustomer = new CheckCustomers();

        System.out.println(customer);

        System.out.println(checkAdmin.test(admin)); // it's working

        System.out.println(checkCustomer.test(customer)); // also working

    }

    private static void validateCategories () {

        Category category = new Category(2, TypeProduct.TOOLS, 2);

        CheckCategories checkCategories = new CheckCategories();

        checkCategories.test(category); // validated

        System.out.println("ValidateCategories function: ");

        System.out.println(category);

    }

    private static void validateProducts () {

        // String name, Double price, Integer quantity,  ProductAvailability availability, Category cat

        Product product = new Product("Computer", 100.0, 1,
                ProductAvailability.IN_STOCK, new Category());

        CheckProducts checkProducts = new CheckProducts();

        System.out.println("ValidateProducts function: ");

        System.out.println(checkProducts.test(product));

        System.out.println(product);

    }

    private static void validateAdmin () {

        Admin admin = new Admin("Paul", "", "721", TypeUser.ADMIN);

        CheckAdmin checkAdmin = new CheckAdmin();

        boolean hmm = checkAdmin.test(admin);

        System.out.println("ValidateAdmin function: ");

        System.out.println(admin);

        System.out.println(hmm);

    }

    private static void validateCustomer () {

        Customer customer = new Customer("Mr.Duck", "mrduck@gmail.com",
                "y78xz8", TypeUser.CUSTOMER);

        CheckCustomers checkCustomers = new CheckCustomers();

        boolean cup = checkCustomers.test(customer);

        System.out.println("ValidateCustomer function: ");

        System.out.println(cup);

        System.out.println(customer);

    }

    private static void isEmailValid () {

        boolean trueOrFalse = MailServ.checkMail("paulwalker@gmail.com");

        System.out.println("IsEmailValid function: ");

        System.out.println(trueOrFalse);

    }

}
