package com.lind.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ExceptionController {
  @GetMapping("index")
  public void index() {
    throw new IllegalArgumentException();
  }
}
