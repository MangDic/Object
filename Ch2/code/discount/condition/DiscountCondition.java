package ch2.discount.condition;

import ch2.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
