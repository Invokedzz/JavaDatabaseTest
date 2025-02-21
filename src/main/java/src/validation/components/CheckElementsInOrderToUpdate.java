package src.validation.components;

// name, email, newPassword

import src.model.entities.UserEntities.User;
import src.security.MailServ;
import src.security.PassInput;
import src.validation.util.UserPredicate;

public class CheckElementsInOrderToUpdate implements UserPredicate {

    @Override
    public boolean test (User user) {
        return PassInput.verifyPasswordText(user.getPassword())
                && user.getPassword().length() > 5 &&
                user.getPassword().length() <= 15 &&
                MailServ.checkMail(user.getEmail()) &&
                user.getName().length() >= 3 &&
                user.getName().length() <= 20;
    }

}
