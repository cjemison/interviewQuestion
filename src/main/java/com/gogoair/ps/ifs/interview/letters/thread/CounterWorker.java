package com.gogoair.ps.ifs.interview.letters.thread;

import com.gogoair.ps.ifs.interview.letters.util.Utility;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterWorker implements Runnable {
  private final ConcurrentHashMap<String, AtomicInteger> map;
  private final CountDownLatch countDownLatch;
  private final int value;

  public CounterWorker(final ConcurrentHashMap<String, AtomicInteger> map,
                       final CountDownLatch countDownLatch,
                       final int value) {
    this.map = map;
    this.countDownLatch = countDownLatch;
    this.value = value;
  }

  @Override
  public void run() {
    char c = (char) value;
    if (this.isAlpha(c)) {
      if (this.isVowel(c)) {
        map.get(Utility.VOWELS).incrementAndGet();
      } else {
        map.get(Utility.CONST).incrementAndGet();
      }
    }
    countDownLatch.countDown();
  }

  private boolean isVowel(final char c) {
    int[] vowels = {65, 69, 73, 79, 85, 97, 101, 105, 111, 117};
    for (final int vowel : vowels) {
      if (((int) c) == vowel) return true;
    }
    return false;
  }

  private boolean isAlpha(final char c) {
    return ((int) c) >= 65 && ((int) c) <= 90
          || ((int) c) >= 97 && ((int) c) <= 122;
  }
}
