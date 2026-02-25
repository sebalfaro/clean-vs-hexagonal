package ar.gov.arba.domain.valueobject;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        if (amount == null) throw new IllegalArgumentException("Amount no puede ser nulo");
        this.amount = amount;
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public BigDecimal getAmount() { return amount; }

    @Override
    public String toString() { return amount.toString(); }
}
