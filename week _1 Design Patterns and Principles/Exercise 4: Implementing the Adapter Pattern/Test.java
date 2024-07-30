package AdapterPatternExample;

public class Test {
    public static void main(String[] args) {
        // Create instances of gateways
        PayPalGateway payPalGateway = new PayPalGateway();
        StripGateway stripeGateway = new StripGateway();

        // Create adapters for each gateway
        PaymentProcessor payPalAdapter = new PayPalAdaptor(payPalGateway);
        PaymentProcessor stripeAdapter = new StripeAdaptor(stripeGateway);

        // Process payments through the adapters
        payPalAdapter.processPayment(100.0);
        stripeAdapter.processPayment(200.0);
    }
}
