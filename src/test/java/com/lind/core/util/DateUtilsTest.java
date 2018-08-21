package com.lind.core.util;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

  @Test
  public void getClosedRangeMonthCount() {
    Assert.assertEquals(3, DateUtils.getClosedRangeMonthCount(
        LocalDate.of(2018, 1, 1),
        LocalDate.of(2018, 3, 1)));
  }

  @Test
  public void isBeforeOrEqual() {
    Assert.assertEquals(true, DateUtils.isBeforeOrEqual(
        LocalDate.of(2018, 1, 1),
        LocalDate.of(2018, 1, 2)));
  }

  @Test
  public void isAfterOrEqual() {
    Assert.assertNotEquals(true, DateUtils.isAfterOrEqual(
        LocalDate.of(2018, 1, 1),
        LocalDate.of(2018, 1, 2)));
  }
}
