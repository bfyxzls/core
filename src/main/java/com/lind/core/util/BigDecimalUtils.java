package com.lind.core.util;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BigDecimalUtils {

  /**
   * 从Object获取 BigDecimal.
   */
  public static BigDecimal getOrZero(Object obj) {
    try {
      return BigDecimal.valueOf(Double.valueOf(String.valueOf(obj)));
    } catch (Exception e) {
      log.error("从Object获取Bigdecimal失败", e);
    }
    return BigDecimal.ZERO;
  }

}
