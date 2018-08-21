package com.lind.core.controller;

import org.junit.Test;

public class ExceptionControllerTest extends BaseControllerTest {
  @Test
  public void index() {
    this.http.get()
        .uri("/test/index")
        .exchange()
        .expectStatus().isEqualTo(500);
  }
}
