package AdapterPatternExample;

public class PayPalAdaptor implements PaymentProcessor {
    private PayPalGateway payPalGateway;

    public PayPalAdaptor(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}
