package com.lind.core.util;

import java.time.YearMonth;
import org.junit.Assert;
import org.junit.Test;

public class YearMonthUtilsTest {
  @Test
  public void isAfter() {
    Assert.assertEquals(true, YearMonthUtils.isAfter(YearMonth.of(2018, 2),
        YearMonth.of(2018, 1)));
  }

  @Test
  public void isLastMonthOfCurrentQuarter() {
    Assert.assertEquals(true,
        YearMonthUtils.isLastMonthOfCurrentQuarter(YearMonth.of(2018, 3)));
  }

  @Test
  public void isFirstMonthOfCurrentQuarter() {
    Assert.assertEquals(true,
        YearMonthUtils.isFirstMonthOfCurrentQuarter(YearMonth.of(2018, 1)));
  }
}
