package src.validation;

import src.model.entities.UserEntities.Address;
import src.security.CpfServ;
import src.util.AddressPredicate;

public class CheckAddress implements AddressPredicate {

    @Override
    public boolean test (Address address) {

        return CpfServ.validateUserCpf(address.getCEP()) &&
                !address.getNumber().isEmpty() &&
                address.getNumber().length() <= 4 &&
                address.getCity().length() > 3 &&
                address.getCity().length() < 25 &&
                address.getLabel().length() >= 10
                && address.getLabel().length() < 100 &&
                !address.getComplement().isEmpty();

    }

}
