package ch2.discount.policy;

import ch2.Money;
import ch2.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {
    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.Zero;
    }
}
