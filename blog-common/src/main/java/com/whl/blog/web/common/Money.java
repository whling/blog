package com.whl.blog.web.common;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by whling on 2017/6/25.
 */
public class Money implements Comparable<Money>, Serializable {

    private static final long serialVersionUID = -3178121776163258055L;

    private static final RoundingMode DEFAULT_ROUNDING;
    private static final RoundingMode ROUNDING_MODE_DOWN;
    private static final MathContext DEF_MC;
    private static final Money MONEY_ZERO;
    private final BigDecimal amount;

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }

    public static Money of(String amount) {
        return StringUtils.isEmpty(amount) ? MONEY_ZERO : new Money(new BigDecimal(amount));
    }

    public static Money of(double amount) {
        return of(BigDecimal.valueOf(amount));
    }

    public static Money ofMajor(long amountMajor) {
        return of(BigDecimal.valueOf(amountMajor));
    }

    public static Money parseStoreValue(long amount) {
        return of(BigDecimal.valueOf(amount, 6));
    }

    public static Money of(String amount, String defaultValue) {
        return amount != null && amount.trim().length() != 0 ? new Money(new BigDecimal(amount)) : new Money(new BigDecimal(defaultValue));
    }

    public static Money total(Money... monies) {
        if (monies.length == 0) {
            throw new IllegalArgumentException("Money array must not be empty");
        } else {
            Money total = monies[0];

            for (int i = 1; i < monies.length; ++i) {
                total = total.plus(new Money[]{monies[i]});
            }

            return total;
        }
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    private Money with(BigDecimal newAmount) {
        return newAmount.equals(this.amount) ? this : new Money(newAmount);
    }

    public boolean isZero() {
        return this.amount.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isPositive() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isPositiveOrZero() {
        return this.amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isNegative() {
        return this.amount.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isNegativeOrZero() {
        return this.amount.compareTo(BigDecimal.ZERO) <= 0;
    }

    public Money plus(Money[] toAdds) {
        BigDecimal total = this.amount;
        Money[] arr$ = toAdds;
        int len$ = toAdds.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            Money money = arr$[i$];
            total = total.add(money.amount, DEF_MC);
        }

        return this.with(total);
    }

    public Money plus(BigDecimal amountToAdd) {
        if (amountToAdd.compareTo(BigDecimal.ZERO) == 0) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.add(amountToAdd, DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money plus(Money amountToAdd) {
        return total(new Money[]{this, amountToAdd});
    }

    public Money plus(double amountToAdd) {
        if (amountToAdd == 0.0D) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.add(BigDecimal.valueOf(amountToAdd), DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money minus(Money[] accountMoneys) {
        BigDecimal total = this.amount;
        Money[] arr$ = accountMoneys;
        int len$ = accountMoneys.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            Money money = arr$[i$];
            total = total.subtract(money.amount, DEF_MC);
        }

        return this.with(total);
    }

    public Money minus(Money moneyToSubtract) {
        return this.minus(moneyToSubtract.amount);
    }

    public Money minus(BigDecimal amountToSubtract) {
        if (amountToSubtract.compareTo(BigDecimal.ZERO) == 0) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.subtract(amountToSubtract, DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money minus(double amountToSubtract) {
        if (amountToSubtract == 0.0D) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.subtract(BigDecimal.valueOf(amountToSubtract), DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money minusMajor(long amountToSubtract) {
        if (amountToSubtract == 0L) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.subtract(BigDecimal.valueOf(amountToSubtract), DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money minusMinor(long amountToSubtract, int scale) {
        if (amountToSubtract == 0L) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.subtract(BigDecimal.valueOf(amountToSubtract, scale), DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money multiply(Money valueToMultiplyBy) {
        return this.multiply(valueToMultiplyBy.amount);
    }

    public Money multiply(BigDecimal valueToMultiplyBy) {
        if (valueToMultiplyBy.compareTo(BigDecimal.ONE) == 0) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.multiply(valueToMultiplyBy, DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money multiply(double valueToMultiplyBy) {
        if (valueToMultiplyBy == 1.0D) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.multiply(BigDecimal.valueOf(valueToMultiplyBy), DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money multiply(long valueToMultiplyBy) {
        if (valueToMultiplyBy == 1L) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.multiply(BigDecimal.valueOf(valueToMultiplyBy), DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money divide(Money value) {
        return this.divide(value.amount);
    }

    public Money divide(BigDecimal value) {
        if (value.compareTo(BigDecimal.ONE) == 0) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.divide(value, DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money divide(double value) {
        if (value == 1.0D) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.divide(BigDecimal.valueOf(value), DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money divide(long value) {
        if (value == 1L) {
            return this;
        } else {
            BigDecimal newAmount = this.amount.divide(BigDecimal.valueOf(value), DEF_MC);
            return this.with(newAmount);
        }
    }

    public Money negate() {
        return this.isZero() ? this : this.with(this.amount.negate());
    }

    public Money abs() {
        return this.isNegative() ? this.negate() : this;
    }

    public boolean isGreaterThan(Money other) {
        return this.compareTo(other) > 0;
    }

    public boolean isLessThan(Money other) {
        return this.compareTo(other) < 0;
    }

    public long toStoreValue() {
        return this.amount.movePointRight(6).setScale(0, DEFAULT_ROUNDING).longValue();
    }

    public BigDecimal toStoreDecimal() {
        return this.amount.setScale(6, DEFAULT_ROUNDING);
    }

    public int compareTo(Money o) {
        return this.amount.compareTo(o.amount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Money) {
            Money otherMoney = (Money) other;
            return this.amount.equals(otherMoney.amount);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 7 * this.amount.hashCode();
    }

    public String toString() {
        return this.amount.toString();
    }

    public String toMoneyString() {
        return this.amount.setScale(2, DEFAULT_ROUNDING).toString();
    }

    public String toMoneyString(int scale) {
        return this.amount.setScale(scale, ROUNDING_MODE_DOWN).toString();
    }

    public static Money zero() {
        return of(0.00D);
    }

    static {
        DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
        ROUNDING_MODE_DOWN = RoundingMode.DOWN;
        DEF_MC = MathContext.DECIMAL64;
        MONEY_ZERO = of(0.0D);
    }
}
