package com.androwl.calculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class CalculatorController {

  private final Calculator calculator;

  @RequestMapping("/sum")
  String sum(@RequestParam("a") Integer a,
      @RequestParam("b") Integer b) {
    return String.valueOf(calculator.sum(a, b));
  }

}
