package com.androwl.calculator;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
  private Calculator calculator = new Calculator();

  public void testSum() {
    assertEquals(5, calculator.sum(2, 3));
  }
}
