package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money{
    private final BigDecimal amount;

    // Financial standard: Round to 2 decimal places (cents)
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    // Private constructor forces usage of factory methods
    private Money(BigDecimal amount){
        this.amount = amount.setScale(2, DEFAULT_ROUNDING);
    }

    // Factory methods to create Money instances cleanly
    public static Money of(double amount){
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money of(String amount){
        return new Money(new BigDecimal(amount));
    }

    public static Money zero(){
        return new Money(BigDecimal.ZERO);
    }

    // Mathematical Operations (Returns a NEW Money instance every time)
    public Money add(Money other){
        Objects.requireNonNull(other, "Cannot add null money.");
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other){
        Objects.requireNonNull(other, "Cannot subtract null money.");
        return new Money(this.amount.subtract(other.amount));
    }

    public boolean isLessThan(Money other){
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isLessThanOrEqualZero(){
        return this.amount.compareTo(BigDecimal.ZERO) <= 0;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    @Override 
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Money other = (Money) obj;
        return amount.compareTo(other.amount) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(amount.stripTrailingZeros());
    }

    @Override 
    public String toString(){
        return "$" + amount;
    }
}