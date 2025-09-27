import java.math.BigDecimal;

class StripeAdapter implements PaymentProcessor {
    private final ThirdPartyStripe stripe;

    public StripeAdapter(ThirdPartyStripe stripe) {
        this.stripe = stripe;
    }

    @Override
    public void processPayment(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        int cents = amount.movePointRight(2).intValue();
        stripe.makeCharge(cents);
    }
}
