package com.lind.core.httpException;

import feign.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DownStreamException extends RuntimeException {
  private String methodKey;

  private Response response;

  private Object body;
}
