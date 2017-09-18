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

  public static void generateFile(final long count) {
    try {
      final File file = new File("data.txt");
      final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
      final PrintWriter printWriter = new PrintWriter(bufferedWriter);
      for (int i = 0; i < count; i++) {
        if (Math.abs(new Random().nextInt() % 2) == 0) {
          int value = Math.abs(new Random().nextInt() % (90 - 65)) + 65;
          printWriter.print((char) value);
        } else {
          int value = Math.abs(new Random().nextInt() % (122 - 97)) + 97;
          printWriter.print((char) value);
        }
        if (i % 100 == 0) {
          printWriter.flush();
        }
      }
      printWriter.flush();
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("File generated");
  }

  public static ConcurrentHashMap<String, AtomicInteger> initMap() {
    final ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
    map.put(CONST, new AtomicInteger(0));
    map.put(VOWELS, new AtomicInteger(0));
    return map;
  }
}
