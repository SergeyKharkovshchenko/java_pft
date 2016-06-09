package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sergey on 02.05.2016.
 */
public class PrimeTests {

  @Test
  public void testPrimes () {
    Assert.assertTrue(Primes.isPrimeFast (Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimes () {
    Assert.assertFalse(Primes.isPrime (Integer.MAX_VALUE - 2));
  }

  @Test (enabled = false)
  public void testPrimeLong () {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime (n));
  }

}
