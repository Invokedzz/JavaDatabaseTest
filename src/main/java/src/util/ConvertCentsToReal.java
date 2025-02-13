package src.util;

import java.math.BigDecimal;

public class ConvertCentsToReal {

    public static BigDecimal moneyConverter (Double price) {

        BigDecimal convertProductPrice = BigDecimal.valueOf(price);

        BigDecimal convertTheConverter = BigDecimal.valueOf(100.00);

        return convertTheConverter.multiply(convertProductPrice);

    }

}
