package AdapterPatternExample;

public class StripeAdaptor implements PaymentProcessor {
    private StripGateway stripeGateway;

    public StripeAdaptor(StripGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(double amount) {
        stripeGateway.charge(amount);
    }
}
