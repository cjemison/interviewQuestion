package com.gogoair.ps.ifs.interview.letters.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Utility {
  public static final String CONST = "Constants";
  public static final String VOWELS = "Vowels";

  public static boolean isVowel(final char c) {
    int[] vowels = {65, 69, 73, 79, 85, 97, 101, 105, 111, 117};
    for (final int vowel : vowels) {
      if (((int) c) == vowel) return true;
    }
    return false;
  }

  public static boolean isAlpha(final char c) {
    return ((int) c) >= 65 && ((int) c) <= 122;
  }

  public static void generateFile(final long count) {
    try {
      final File file = new File("data.txt");
      final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
      final PrintWriter printWriter = new PrintWriter(bufferedWriter);
      for (int i = 0; i < count; i++) {
        printWriter.print((char) Math.abs(new Random().nextInt() % 128));
        if (i % 88 == 0 && i > 0) {
          printWriter.println();
          printWriter.flush();
        }
      }
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ConcurrentHashMap<String, AtomicInteger> initMap() {
    final ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
    map.put(CONST, new AtomicInteger());
    map.put(VOWELS, new AtomicInteger());
    return map;
  }
}
