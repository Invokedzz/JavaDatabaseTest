package src.validation;

import src.model.entities.UserEntities.Address;
import src.security.CpfServ;
import src.util.AddressPredicate;

public class CheckAddress implements AddressPredicate {

    @Override
    public boolean test (Address address) {

        return CpfServ.validateUserCpf(address.getCEP()) &&
                address.getNumber() != null &&
                address.getCity() != null &&
                address.getNeighbourhood() != null &&
                !address.getComplement().isEmpty();

    }

}
