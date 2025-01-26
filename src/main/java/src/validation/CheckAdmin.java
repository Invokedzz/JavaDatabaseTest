package src.validation;

import src.model.entities.UserEntities.User;

import src.model.enums.TypeUser;

import src.util.UserPredicate;

public class CheckAdmin implements UserPredicate {

    @Override
    public boolean test (User user) {

        // I need to review this shit
        // this shit was reviewed

        return user.getTypeUser() == TypeUser.ADMIN
                && user.getName().length() >= 2 &&
                user.getName().length() <= 10 &&
                user.getLastName().length() >= 2 &&
                user.getLastName().length() <= 12 &&
                user.getPassword().length() > 5 &&
                user.getPassword().length() <= 15;

    }

}
