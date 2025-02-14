package src.application;

import src.api.usages.MercadoPagoComponents;
import src.api.usages.PaymentSession;

import java.awt.*;

public class Main {

    private static Component parent;

    public static void main (String[] args) {

        MercadoPagoComponents.getPaymentParams("samuelnobrega902@gmail.com", "0.01");

        System.out.println(PaymentSession.paymentId);

    }

}
