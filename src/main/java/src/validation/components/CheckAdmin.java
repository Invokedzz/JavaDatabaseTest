package src.validation.components;

import src.model.entities.UserEntities.User;
import src.model.enums.TypeUser;
import src.security.MailServ;
import src.validation.util.UserPredicate;

public class CheckAdmin implements UserPredicate {

    @Override
    public boolean test (User user) {

        // I need to review this shit
        // this shit was reviewed

        return user.getTypeUser() == TypeUser.ADMIN &&
                user.getPassword().length() > 5 &&
                user.getPassword().length() <= 15 &&
                MailServ.checkMail(user.getEmail());

    }


}
