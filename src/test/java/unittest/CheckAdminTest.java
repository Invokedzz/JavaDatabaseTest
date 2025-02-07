package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.UserEntities.Admin;
import src.model.enums.TypeUser;
import src.validation.CheckAdmin;

import static org.junit.jupiter.api.Assertions.*;

class CheckAdminTest {

    /*user.getTypeUser() == TypeUser.ADMIN
                && user.getName().length() >= 2 &&
                user.getName().length() <= 10 &&
                user.getLastName().length() >= 2 &&
                user.getLastName().length() <= 12 &&
                user.getPassword().length() > 5 &&
                user.getPassword().length() <= 15 &&
                MailServ.checkMail(user.getEmail());*/

    // super(name, lastName, email, password, typeUser);

    private static Admin admin;

    private static Admin adminWithInvalidName;

    private static Admin adminWithInvalidPassword;

    private static Admin adminWithInvalidEmail;

    private static Admin adminWithInvalidType;

    private static CheckAdmin checkAdmin;

    @BeforeEach
    void setUp() {

        admin = new Admin("mrduck@gmail.com",
                "superduck123", "",TypeUser.ADMIN);

        adminWithInvalidName = new Admin("mrduck@gmail.com",
                "superduck123", "",TypeUser.ADMIN);

        adminWithInvalidPassword = new Admin("mrduck@gmail.com",
                "123", "",TypeUser.ADMIN);

        adminWithInvalidEmail = new Admin("mrduck@gmail",
                "superduck123","", TypeUser.ADMIN);

        adminWithInvalidType = new Admin("mrduck@gmail.com",
                "superduck123",  "", TypeUser.CUSTOMER);

        checkAdmin = new CheckAdmin();

    }

    @Test
    @DisplayName("Testing a valid admin")
    void validAdminTest () {

        assertTrue(checkAdmin.test(admin));

    }

    @Test
    @DisplayName("Testing an invalid password length")
    void invalidPasswordLengthTest () {

        assertFalse(checkAdmin.test(adminWithInvalidPassword));

    }

    @Test
    @DisplayName("Testing an invalid type for admin")
    void invalidTypeUserForAdmin () {

        assertFalse(checkAdmin.test(adminWithInvalidType));

    }

    @Test
    @DisplayName("Testing an invalid admin email")
    void invalidAdminEmail () {

        assertFalse(checkAdmin.test(adminWithInvalidEmail));

    }

}