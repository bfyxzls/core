package com.lind.core.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FieldUtilsTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  List<Integer> result = Arrays.asList(new Integer[] {1, 2, 3});

  @Test
  public void getOrDefault() {
    assertEquals(new Integer[] {2, 3},
        FieldUtils.getOrDefault(result,
            o -> result.stream().filter(i -> i > 1).toArray(),
            null));

  }

  @Test
  public void getOrThrow() {
    FieldUtils.getOrThrow(result,
        o -> result.stream().filter(i -> i > 3).toArray(),
        new RuntimeException());
  }

}