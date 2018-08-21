package com.lind.core.util;

import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class MapUtilsTest {
  @Test
  public void ofEmpty() {
    Map<String, String> map = null;
    Assert.assertNotEquals(null, MapUtils.ofEmpty(map));
  }
}
