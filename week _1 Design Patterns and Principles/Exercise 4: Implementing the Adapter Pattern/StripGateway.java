package AdapterPatternExample;

public class StripGateway {
    public void charge(double amount) {
        System.out.println("Charging $" + amount + " using Stripe.");
    }
}
