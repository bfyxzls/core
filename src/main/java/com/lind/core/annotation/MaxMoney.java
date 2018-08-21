package com.lind.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 最大值约束.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxMoneyHandle.class)
public @interface MaxMoney {
  /**
   * message.
   *
   * @return
   */
  String message() default "max error.";

  /**
   * max value.
   *
   * @return
   */
  double value() default 0;

  /**
   * group.
   *
   * @return
   */
  Class<?>[] groups() default {};

  /**
   * payload.
   *
   * @return
   */
  Class<? extends Payload>[] payload() default {};
}
