package com.gogoair.ps.ifs.interview.letters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
  private final String CONST = "Constants";
  private final String VOWELS = "Vowels";

  public static void main(String[] args) {
    // how many vowels/consonants
    System.out.println(new App().run("Gogo is a wonderful place to be."));
  }

  private Map<String, AtomicInteger> run(final String example) {
    return count(example);
  }

  private Map<String, AtomicInteger> count(final String example) {
    final Map<String, AtomicInteger> map = initMap();
    Arrays.stream(example.split(""))
          .map(String::toCharArray)
          .map(chars -> chars[0])
          .filter(this::isAlpha).forEach(c -> {
      if (isVowel(c)) {
        map.get(VOWELS).incrementAndGet();
      } else {
        map.get(CONST).incrementAndGet();
      }
    });
    return map;
  }

  private boolean isVowel(final char c) {
    int[] vowels = {65, 69, 73, 79, 85, 97, 101, 105, 111, 117};
    for (final int vowel : vowels) {
      if (((int) c) == vowel) return true;
    }
    return false;
  }

  private boolean isAlpha(final char c) {
    return ((int) c) >= 65 && ((int) c) <= 122;
  }

  private Map<String, AtomicInteger> initMap() {
    final Map<String, AtomicInteger> map = new HashMap<>();
    map.put(CONST, new AtomicInteger());
    map.put(VOWELS, new AtomicInteger());
    return map;
  }
}
