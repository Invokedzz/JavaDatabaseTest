package src.application;

import src.api.stripe.StripeServ;

public class Main {

    public static void main (String[] args) {

        StripeServ.stripePayment(1.0);

    }

}
