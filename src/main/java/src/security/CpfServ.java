package src.security;

import br.com.caelum.stella.validation.CPFValidator;

public class CpfServ {

    public static boolean validateUserCpf (String cpf) {

        CPFValidator cpfValidator = new CPFValidator();

        return cpfValidator.isEligible(cpf);

    }

}
