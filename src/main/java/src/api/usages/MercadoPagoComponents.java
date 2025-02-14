package src.api.usages;


import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import src.api.keys.MercadoPagoKey;
import src.exceptions.ApiException;
import src.exceptions.PaymentException;
import src.util.ConvertCentsToReal;
import java.math.BigDecimal;


public class MercadoPagoComponents {

    public static void getPaymentParams (String email, String price) {

        MercadoPagoKey.configureMercadoPagoKey();

        PaymentClient paymentClient = new PaymentClient();

        Double convertPrice = Double.parseDouble(price);

        BigDecimal productPrice = ConvertCentsToReal.moneyConverter(convertPrice);

        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal(String.valueOf(productPrice)))
                        .paymentMethodId("pix")
                        .payer(PaymentPayerRequest.builder().email(email).build())
                        .description("Product from Porky Store!")
                        .build();

        try {

            Payment paymentRequest = paymentClient.create(createRequest);

            PaymentSession.paymentLink = paymentRequest.getPointOfInteraction().getTransactionData().getTicketUrl();

            PaymentSession.qrCode = paymentRequest.getPointOfInteraction().getTransactionData().getQrCode();

            PaymentSession.paymentId = String.valueOf(paymentRequest.getId());

        } catch (MPApiException exception) {

            String createExceptionText = String.format("API response: %s, status code: %s",
                    exception.getApiResponse(), exception.getStatusCode());

            throw new ApiException(createExceptionText);

        } catch (MPException exception) {

            throw new PaymentException(exception.getMessage());

        }

    }

}
