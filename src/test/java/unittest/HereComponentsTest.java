package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import settings.ValidCpf;
import src.api.usages.HereComponents;
import src.model.entities.UserEntities.Address;
import src.validation.components.CheckAddress;

import static org.junit.jupiter.api.Assertions.*;

class HereComponentsTest {

    private Address validAddress, invalidAddress;

    private CheckAddress checkAddress;

    @BeforeEach
    void setUp() {

        validAddress = new Address(ValidCpf.VALID_CPF, "636", "12P", "Avenida General Pedro Pinho", "Osasco");

        invalidAddress = new Address(ValidCpf.VALID_CPF, "636", "", "Vasco", "Washington");

        checkAddress = new CheckAddress();

    }

    @Test
    @DisplayName("Valid address test")
    void setValidAddress () {

        if (checkAddress.test(validAddress)) System.out.println(HereComponents.obtainAddressThroughApi(validAddress));

    }

    @Test
    @DisplayName("Invalid address test")
    void setInvalidAddress () {

        assertFalse(checkAddress.test(invalidAddress));

    }

}