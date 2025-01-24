package src.validation;

import src.model.entities.UserEntities.User;

import src.model.enums.TypeUser;

import src.util.UserPredicate;

public class CheckCustomers implements UserPredicate {

    @Override
    public boolean test (User user) {

        return user.getTypeUser() == TypeUser.CUSTOMER
                && !user.getName().isEmpty() &&
                !user.getLastName().isEmpty() &&
                user.getPassword().length() > 3 &&
                user.getPassword().length() <= 8;

    }

}
