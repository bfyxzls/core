package com.lind.core.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.javamoney.moneta.Money;

/**
 * max handle.
 */
public class MaxMoneyHandle implements ConstraintValidator<MaxMoney, Money> {
  MaxMoney constraint;

  public void initialize(MaxMoney constraint) {
    this.constraint = constraint;
  }

  /**
   * valid.
   *
   * @param value   .
   * @param context .
   * @return
   */
  public boolean isValid(Money value, ConstraintValidatorContext context) {
    return value != null && value.getNumber().doubleValue() <= constraint.value();
  }

}
