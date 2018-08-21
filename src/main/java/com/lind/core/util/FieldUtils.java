package com.lind.core.util;

import java.util.Objects;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldUtils {

  /**
   * 尝试执行 function，否则返回默认值.
   *
   * @param t            function的入参
   * @param function     lambda.
   * @param defaultValue body的默认值，当lambda执行出现异常时使用.
   * @param <T>          泛型实体.
   * @return .
   */
  public static <T, R> R getOrDefault(T t, Function<T, R> function, R defaultValue) {
    try {
      R value = function.apply(t);
      if (Objects.isNull(value)) {
        return defaultValue;
      }
      return value;
    } catch (Exception e) {
      log.error("getOrDefault", e);
    }
    return defaultValue;
  }

  /**
   * 尝试执行 function，否则抛出特定的异常.
   *
   * @param t                function的入参
   * @param function         lambda.
   * @param specialException 特定的异常.
   * @param <T>              泛型实体.
   * @return .
   */
  public static <T, R> R getOrThrow(
      T t,
      Function<T, R> function,
      RuntimeException specialException) {
    try {
      return function.apply(t);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    throw specialException;
  }
}
