package src.validation;

import src.model.entities.UserEntities.Address;
import src.util.AddressPredicate;

public class CheckAddress implements AddressPredicate {

    @Override
    public boolean test(Address address) {
        return false;
    }

}
