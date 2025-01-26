package src.validation;

import src.model.entities.UserEntities.User;

import src.model.enums.TypeUser;

import src.util.UserPredicate;

public class CheckCustomers implements UserPredicate {

    @Override
    public boolean test (User user) {

        return user.getTypeUser() == TypeUser.CUSTOMER &&
                user.getName().length() >= 2 &&
                user.getName().length() <= 10 &&
                user.getLastName().length() >= 2 &&
                user.getLastName().length() <= 12 &&
                user.getPassword().length() > 5 &&
                user.getPassword().length() <= 15;

    }

}
