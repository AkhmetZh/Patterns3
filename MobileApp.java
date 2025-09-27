import java.math.BigDecimal;

public class MobileApp {
    private final PaymentProcessor paymentProcessor;

    public MobileApp(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(BigDecimal amount) {
        System.out.println("Starting payment: " + amount);
        try {
            paymentProcessor.processPayment(amount);
            System.out.println("Payment completed!\n");
        } catch (Exception e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ThirdPartyStripe stripe = new ThirdPartyStripe();
        PaymentProcessor adapter = new StripeAdapter(stripe);
        MobileApp app = new MobileApp(adapter);

        app.checkout(new BigDecimal("19.99"));
        app.checkout(new BigDecimal("-5.00"));
    }
}