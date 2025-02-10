package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.view.validations.user.register.CheckIfCpfAlreadyExists;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfCpfAlreadyExistsTest {

    private static String cpf;

    Component parent;

    @Test
    @DisplayName("Testing a case where the cpf is on the DB")
    void validCase () {

        cpf = "12345";

        List <String> cpfList = new ArrayList<>();

        cpfList.add("12345");

        assertFalse(CheckIfCpfAlreadyExists.verifyCpfExistence(parent, cpf, cpfList));

    }

    @Test
    @DisplayName("Testing a case where the cpf is on the DB")
    void invalidCase () {

        cpf = "12345";

        List <String> cpfList = new ArrayList<>();

        cpfList.add("123456");

        assertTrue(CheckIfCpfAlreadyExists.verifyCpfExistence(parent, cpf, cpfList));

    }

}