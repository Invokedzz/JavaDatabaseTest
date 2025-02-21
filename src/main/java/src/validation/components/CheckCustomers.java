package src.validation.components;

import src.model.entities.UserEntities.User;
import src.model.enums.TypeUser;
import src.security.MailServ;
import src.validation.util.UserPredicate;

public class CheckCustomers implements UserPredicate {

    @Override
    public boolean test (User user) {

        return user.getTypeUser() == TypeUser.CUSTOMER &&
                user.getName().length() >= 3 &&
                user.getName().length() <= 20 &&
                user.getPassword().length() > 5 &&
                user.getPassword().length() <= 15 &&
                MailServ.checkMail(user.getEmail());

    }

}
