package src.security;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class CpfServ {

    public static boolean validateUserCpf (String cpf) {

        CPFValidator cpfValidator = new CPFValidator();

        if (cpfValidator.isEligible(cpf)) {

            try {

                cpfValidator.assertValid(cpf);

                return true;

            } catch (InvalidStateException exception) {

                throw new InvalidStateException(exception.getInvalidMessages());

            }

        }

        return false;

    }

}
