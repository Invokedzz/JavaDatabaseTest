package src.validation.util;

import src.model.entities.UserEntities.Address;

import java.util.function.Predicate;

@FunctionalInterface
public interface AddressPredicate extends Predicate <Address> {

    @Override
    boolean test(Address address);

}
