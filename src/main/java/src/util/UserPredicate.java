package src.util;

import src.model.entities.UserEntities.User;

import java.util.function.Predicate;

@FunctionalInterface
public interface UserPredicate extends Predicate <User> {

    boolean test (User user);

}
