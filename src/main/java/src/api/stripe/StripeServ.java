package src.api.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentLinkCreateParams;
import src.api.keys.StripeKey;
import src.exceptions.PaymentException;
import src.util.ConvertCentsToReal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class StripeServ {

    public static void stripePayment (Double productPrice) {

        StripeKey.configure();

        long price = ConvertCentsToReal.moneyConverter(productPrice);

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(price)
                .setCurrency("brl")
                .addAllPaymentMethodType(List.of("PIX"))
                .build();

        try {

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            String pixUrl = paymentIntent.getNextAction().getPixDisplayQrCode().getHostedInstructionsUrl();

            System.out.println(pixUrl);

        } catch (StripeException exception) {

            throw new PaymentException(exception.getMessage());

        }

    }

}
