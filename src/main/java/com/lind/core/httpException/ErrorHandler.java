package com.lind.core.httpException;

import feign.Response;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 错误处理.
 */
@Slf4j
@ControllerAdvice
public class ErrorHandler {

  /**
   * 处理Exception异常.
   *
   * @param exception 异常.
   * @return 错误消息.
   */
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorResponse> exception(
      Exception exception,
      HttpServletRequest request
  ) {

    ErrorResponse errorResponse = buildErrorResponse(request, HttpStatus.INTERNAL_SERVER_ERROR);
    errorResponse.setExtra(getStackTrace(exception));
    errorResponse.addError(
        "INTERNAL_SERVER_ERROR",
        getExceptionMessage(exception)
    );

    log.info("未知的错误 = {}", errorResponse);

    return buildResponseEntity(errorResponse);
  }

  private Object getStackTrace(Exception exception) {
    return Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray();
  }

  /**
   * HttpMessageNotReadableException.
   *
   * @param exception .
   * @return .
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> httpMessageNotReadableException(
      HttpMessageNotReadableException exception,
      HttpServletRequest request
  ) {

    ErrorResponse errorResponse = buildErrorResponse(request, HttpStatus.BAD_REQUEST);

    errorResponse.addError("HTTP_MESSAGE_NOT_READABLE", exception.getMessage());

    log.info("无法读取客户端请求 = {}", errorResponse);

    return buildResponseEntity(errorResponse);
  }

  /**
   * MethodArgumentNotValidException.
   *
   * @param exception .
   * @return .
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> methodArgumentNotValidException(
      MethodArgumentNotValidException exception,
      HttpServletRequest request
  ) {
    ErrorResponse errorResponse = buildErrorResponse(request, HttpStatus.BAD_REQUEST);

    BindingResult bindingResult = exception.getBindingResult();

    for (ObjectError bindError : bindingResult.getAllErrors()) {
      String name = bindError.getObjectName();
      String message = bindError.getDefaultMessage();
      if (bindError instanceof FieldError) {
        name = ((FieldError) bindError).getField();
      }
      errorResponse.addError(name, message);
    }

    log.info("客户端请求参数错误 = {}", errorResponse);

    return buildResponseEntity(errorResponse);
  }

  /**
   * MethodArgumentTypeMismatchException.
   *
   * @param exception .
   * @return .
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException exception,
      HttpServletRequest request
  ) {

    ErrorResponse errorResponse = buildErrorResponse(request, HttpStatus.BAD_REQUEST);
    Object value = exception.getValue();
    Class<?> requiredType = exception.getRequiredType();
    if (value != null && requiredType != null) {
      String message = String.format(
          "'%s'不是有效的'%s'类型",
          value.toString(),
          requiredType.getName()
      );
      errorResponse.addError(exception.getName(), message);
    }

    log.info("客户端请求参数错误 = {}", errorResponse);

    return buildResponseEntity(errorResponse);
  }

  /**
   * MissingServletRequestParameterException.
   *
   * @param exception .
   * @return .
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> missingServletRequestParameterException(
      MissingServletRequestParameterException exception,
      HttpServletRequest request
  ) {

    ErrorResponse errorResponse = buildErrorResponse(request, HttpStatus.BAD_REQUEST);
    String parameterName = exception.getParameterName();
    String parameterType = exception.getParameterType();
    String message = String.format(
        "'%s'类型的参数'%s'不能为空。",
        parameterType,
        parameterName
    );
    errorResponse.addError(parameterName, message);

    log.info("客户端请求参数错误 = {}", errorResponse);

    return buildResponseEntity(errorResponse);
  }


  /**
   * HttpStatusException.
   *
   * @param exception .
   * @return .
   */
  @ExceptionHandler(HttpStatusException.class)
  public ResponseEntity<ErrorResponse> httpStatusException(
      HttpStatusException exception,
      HttpServletRequest request
  ) {

    ErrorResponse errorResponse = buildErrorResponse(request, exception.getHttpStatus());

    errorResponse.addError(ErrorItem.builder()
        .code(exception.getCode())
        .value(exception.getValue())
        .message(exception.getMessage())
        .build()
    );

    log.info("处理的请求异常 = {}", errorResponse);
    return buildResponseEntity(errorResponse);
  }

  /**
   * ConstraintViolationException.
   *
   * @param exception .
   * @return .
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> constraintViolationException(
      ConstraintViolationException exception,
      HttpServletRequest request
  ) {

    ErrorResponse errorResponse = buildErrorResponse(request, HttpStatus.BAD_REQUEST);

    for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
      String name = violation.getPropertyPath().toString();
      if (StringUtils.isNotBlank(name)) {
        int index = name.indexOf(".");
        if (index >= 0 && name.length() >= index + 1) {
          name = name.substring(index + 1);
        }
      }
      String message = violation.getMessageTemplate();
      errorResponse.addError(name, message);
    }

    log.info("客户端请求参数错误 = {}", errorResponse);
    return buildResponseEntity(errorResponse);
  }

  /**
   * DownStreamException.
   *
   * @param exception .
   * @return .
   */
  @ExceptionHandler(DownStreamException.class)
  public ResponseEntity<?> downStreamException(
      DownStreamException exception
  ) {
    Response downStreamResponse = exception.getResponse();

    int downStreamResponseStatus = downStreamResponse.status();
    Object downStreamResponseBody = exception.getBody();
    log.error("下游服务错误 = {}", downStreamResponseBody);

    return ResponseEntity
        .status(downStreamResponseStatus)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(downStreamResponseBody);
  }

  private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse errorResponse) {
    return ResponseEntity
        .status(errorResponse.getStatus())
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(errorResponse);
  }

  private ErrorResponse buildErrorResponse(HttpServletRequest request, HttpStatus httpStatus) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(httpStatus.value());
    errorResponse.setMethod(request.getMethod());
    errorResponse.setPath(getFullRequestUrl(request));
    return errorResponse;
  }

  private String getFullRequestUrl(HttpServletRequest request) {
    String fullRequestUrl = request.getRequestURI();

    String queryString = request.getQueryString();
    if (StringUtils.isNotBlank(queryString)) {
      fullRequestUrl = fullRequestUrl + "?" + queryString;
    }

    return fullRequestUrl;
  }

  private String getExceptionMessage(Exception exception) {
    String message = exception.getMessage();
    if (message != null) {
      return message;
    }
    return exception.toString();
  }
}
