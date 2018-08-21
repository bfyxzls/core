package com.lind.core.util;

import java.time.LocalDate;
import org.springframework.util.Assert;

public class DateUtils {

  /**
   * 获取闭区间月份数.
   */
  public static int getClosedRangeMonthCount(
      LocalDate first,
      LocalDate second) {
    Assert.notNull(first, "first can't be null");
    Assert.notNull(second, "second can't be null;");
    int firstYear = first.getYear();
    int firstMonth = first.getMonthValue();
    int secondYear = second.getYear();
    int secondMonth = second.getMonthValue();

    return (secondYear - firstYear) * 12 + secondMonth - firstMonth + 1;
  }

  /**
   * localDate1 before or equal to localDate2.
   */
  public static boolean isBeforeOrEqual(LocalDate localDate1, LocalDate localDate2) {
    if (localDate1 == null || localDate2 == null) {
      return false;
    }
    return localDate1.isEqual(localDate2) || localDate1.isBefore(localDate2);
  }

  /**
   * localDate1 after or equal to localDate2.
   */
  public static boolean isAfterOrEqual(LocalDate localDate1, LocalDate localDate2) {
    if (localDate1 == null || localDate2 == null) {
      return false;
    }
    return localDate1.isEqual(localDate2) || localDate1.isAfter(localDate2);
  }

}
