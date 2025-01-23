package src.validation;

import src.model.entities.UserEntities.User;

import src.util.UserPredicate;

public class CheckCustomers implements UserPredicate {

    @Override
    public boolean test (User user) {

        return false;

    }

}
