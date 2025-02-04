package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.security.PassHash;
import src.view.validations.user.page.ComparePasswordsInOrderToUpdate;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ComparePasswordsInOrderToUpdateTest {

    // public static boolean comparePasswords (Component parent, String oldPassword, Customer)

    private Component parent;

    private static String oldPassword;

    private static Customer customer;

    @Test
    @DisplayName("Testing an invalid case")
    void invalidPasswordMatching () {

        customer = new Customer("Mr.Fish", "mrfish@gmail.com", "12345castle", TypeUser.CUSTOMER);

        oldPassword = "castle12345";

        String addSaltToPassword = PassHash.generateHash(customer.getPassword());

        customer.setPassword(addSaltToPassword);

        assertFalse(ComparePasswordsInOrderToUpdate.comparePasswords(parent, oldPassword, customer));

    }

    @Test
    @DisplayName("Testing a valid case")
    void validPasswordMatching () {

        customer = new Customer("Mr.Alligator", "mralligator@gmail.com", "castlevania12345", TypeUser.CUSTOMER);

        oldPassword = "castlevania12345";

        String addSalt = PassHash.generateHash(customer.getPassword());

        customer.setPassword(addSalt);

        assertTrue(ComparePasswordsInOrderToUpdate.comparePasswords(parent, oldPassword, customer));

    }

}