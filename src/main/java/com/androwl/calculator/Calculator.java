package com.androwl.calculator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Calculator {
  @Cacheable("sum")
  public int sum(int a, int b) {
    try {
      Thread.sleep(3000);
    }
    catch (InterruptedException e) {
      log.info("Error: ", e);
    }
    return a + b;
  }
}
