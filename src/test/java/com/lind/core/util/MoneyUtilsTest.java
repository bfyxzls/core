package com.lind.core.util;

import javax.money.NumberValue;
import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.Test;

public class MoneyUtilsTest {
  @Test
  public void add() {
    Assert.assertEquals(
        Money.of(3, "CNY"),
        MoneyUtils.add(Money.of(1, "CNY"),
            Money.of(2, "CNY")));
  }

  @Test
  public void subtract() {
    Assert.assertEquals(
        Money.of(1, "CNY"),
        MoneyUtils.subtract(Money.of(2, "CNY"),
            Money.of(1, "CNY")));
  }

  @Test
  public void getNumber() {
    NumberValue num = Money.of(3, "USD").getNumber();

    Assert.assertEquals(num.intValue(),
        MoneyUtils.getNumber(Money.of(3, "CNY")).intValue());
  }
}
