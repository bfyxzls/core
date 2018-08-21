package com.lind.core.httpException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 状态码异常.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class HttpStatusException extends RuntimeException {

  /**
   * 状态码.
   */
  private final HttpStatus httpStatus;

  /**
   * code.
   */
  private final String code;


  /**
   * code的附加数据.
   */
  private final Object value;

  /**
   * 错误消息.
   */
  private final String message;

  /**
   * 创建RuntimeException.
   *
   * @param httpStatus httpStatus
   * @param code       code
   * @param value      code
   * @param message    message
   * @return {@link RuntimeException}
   */
  public static RuntimeException of(
      final HttpStatus httpStatus,
      final String code,
      final Object value,
      final String message) {
    return new HttpStatusException(httpStatus, code, value, message);
  }

  /**
   * notFound.
   */
  public static RuntimeException notFound(String code, Object value) {
    return of(HttpStatus.NOT_FOUND, code, value, null);
  }

  /**
   * notAcceptable.
   */
  public static RuntimeException notAcceptable(String code, Object value) {
    return of(HttpStatus.NOT_ACCEPTABLE, code, value, null);
  }

  /**
   * preconditionFailed.
   */
  public static RuntimeException preconditionFailed(String code, Object value) {
    return of(HttpStatus.PRECONDITION_FAILED, code, value, null);
  }

  /**
   * badRequest.
   */
  public static RuntimeException badRequest(String code, Object value) {
    return of(HttpStatus.BAD_REQUEST, code, value, null);
  }

  /**
   * badRequest.
   */
  public static RuntimeException badRequest(String code, String message) {
    return of(HttpStatus.BAD_REQUEST, code, null, message);
  }

  /**
   * internalServerError.
   */
  public static RuntimeException internalServerError(String code, String message) {
    return of(HttpStatus.INTERNAL_SERVER_ERROR, code, null, message);
  }
}
