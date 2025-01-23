package src.validation;

import src.model.entities.UserEntities.Admin;

import src.model.entities.UserEntities.User;

import src.model.enums.TypeUser;

import src.util.UserPredicate;

import java.util.function.Predicate;

public class CheckAdmin implements UserPredicate {

    @Override
    public boolean test (User user) {

        return false;

    }

}
