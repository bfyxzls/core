package com.lind.core.util;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class BigDecimalUtilsTest {
  @Test
  public void BigDecimal() {
    BigDecimal bigDecimal = BigDecimal.valueOf(1.0);
    Assert.assertEquals(BigDecimalUtils.getOrZero(1.0), bigDecimal);
  }
}
