package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import settings.ValidCpf;
import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.validation.CheckAddress;
import src.validation.CheckCustomers;
import src.view.validations.user.register.RegisterGeneralInfoValidation;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RegisterGeneralInfoValidationTest {

    private Component parent;

    private CheckCustomers checkCustomers;

    private CheckAddress checkAddress;

    private static Customer customer;

    private static Address address;

    // String name, String email, String password, TypeUser typeUser

    @BeforeEach
    void setUp() {

        checkCustomers = new CheckCustomers();

        checkAddress = new CheckAddress();

    }

    @Test
    void validCustomerMessage () {

        customer = new Customer("Mr Duck", "mrduck@gmail.com", "castlevania123", TypeUser.CUSTOMER);

        assertTrue(RegisterGeneralInfoValidation.invalidCustomerMessage(parent, checkCustomers, customer));

    }

    @Test
    void invalidCustomerMessage () {

        customer = new Customer("", "", "12345castlevania", TypeUser.CUSTOMER);

        assertFalse(RegisterGeneralInfoValidation.invalidCustomerMessage(parent, checkCustomers, customer));

    }

    @Test
    void validAddressMessage () {

        address = new Address(ValidCpf.VALID_CPF, "215", "none", "Av Presidente Altino", "Sao Paolo");

        assertTrue(RegisterGeneralInfoValidation.invalidAddressMessage(parent, checkAddress, address));

    }

    // String CEP, String number, String complement, String label, String city

    @Test
    void invalidAddressMessage () {

        address = new Address("randomHaha", "837", "none", "hm", "Joao Pessoa");

        assertFalse(RegisterGeneralInfoValidation.invalidAddressMessage(parent, checkAddress, address));

    }

}