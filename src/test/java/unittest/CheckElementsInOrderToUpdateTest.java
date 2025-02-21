package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.UserEntities.Customer;
import src.validation.components.CheckElementsInOrderToUpdate;

import static org.junit.jupiter.api.Assertions.*;

class CheckElementsInOrderToUpdateTest {

    private static Customer customer;

    @Test
    @DisplayName("Testing a valid case")
    void validUserCase () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("Mr.Salmon", "salmon@gmail.com", "salmon12345");

        assertTrue(elements.test(customer));

    }

    @Test
    @DisplayName("Testing an invalid case where the email input is empty")
    void invalidEmailEmptyCase () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("Mr.Salmon", "", "salmon12345");

        assertFalse(elements.test(customer));

    }

    @Test
    @DisplayName("Testing an invalid case where the name input is empty")
    void invalidNameEmptyCase () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("", "salmon@gmail.com", "salmon12345");

        assertFalse(elements.test(customer));

    }

    @Test
    @DisplayName("Testing an invalid case where the password input is empty")
    void invalidPasswordEmptyCase () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("Mr.Salmon", "salmon@gmail.com", "");

        assertFalse(elements.test(customer));

    }

    @Test
    @DisplayName("Testing an invalid case where the email format is not valid")
    void invalidEmailFormatCase () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("Mr.Salmon", "salmon@", "salmon12345");

        assertFalse(elements.test(customer));

    }

    @Test
    @DisplayName("Testing an invalid case where the password length is lower than 6")
    void passwordLengthLowerThanExpected () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("Mr.Salmon", "salmon@gmail.com", "salt");

        assertFalse(elements.test(customer));

    }

    @Test
    @DisplayName("Testing an invalid case where the name length is lower than 3")
    void nameLengthLowerThanExpected () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("Mr", "salmon@gmail.com", "");

        assertFalse(elements.test(customer));

    }

    @Test
    @DisplayName("Testing an invalid case where the password length is higher than 15")
    void passwordLengthHigherThanExpected () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("Mr.Salmon", "salmon@gmail.com", "123saltsaltsaltsaltsaltsaltsalt");

        assertFalse(elements.test(customer));

    }

    @Test
    @DisplayName("Testing an invalid case where the name length is lower than 20")
    void nameLengthHigherThanExpected () {

        CheckElementsInOrderToUpdate elements = new CheckElementsInOrderToUpdate();

        customer = new Customer("DonaldDuckDonaldDuckDonaldDuckDonaldDuck", "salmon@gmail.com", "DonaldDuck123");

        assertFalse(elements.test(customer));

    }

}