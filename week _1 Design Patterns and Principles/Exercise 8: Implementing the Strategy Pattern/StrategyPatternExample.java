package StrategyPatternExample;



interface PaymentStrategy {
 void pay(double amount);
}


class CreditCardPayment implements PaymentStrategy {
 private String cardNumber;
 private String cardHolder;
 private String cvv;
 private String expiryDate;

 public CreditCardPayment(String cardNumber, String cardHolder, String cvv, String expiryDate) {
     this.cardNumber = cardNumber;
     this.cardHolder = cardHolder;
     this.cvv = cvv;
     this.expiryDate = expiryDate;
 }

 @Override
 public void pay(double amount) {
     System.out.println("Paid " + amount + " using Credit Card.");
 }
}

class PayPalPayment implements PaymentStrategy {
 private String email;
 private String password;

 public PayPalPayment(String email, String password) {
     this.email = email;
     this.password = password;
 }

 @Override
 public void pay(double amount) {
     System.out.println("Paid " + amount + " using PayPal.");
 }
}


class PaymentContext {
 private PaymentStrategy paymentStrategy;

 public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
     this.paymentStrategy = paymentStrategy;
 }

 public void pay(double amount) {
     paymentStrategy.pay(amount);
 }
}

public class StrategyPatternExample {
 public static void main(String[] args) {
     PaymentContext context = new PaymentContext();

     PaymentStrategy creditCardPayment = new CreditCardPayment("1234 5678 9012 3456", "John Doe", "123", "12/24");
     context.setPaymentStrategy(creditCardPayment);
     context.pay(100.0);

     
     PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com", "password123");
     context.setPaymentStrategy(payPalPayment);
     context.pay(200.0);
 }
}
