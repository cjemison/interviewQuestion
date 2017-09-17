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
    System.out.println("File generated");
  }

  public static ConcurrentHashMap<String, AtomicInteger> initMap() {
    final ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
    map.put(CONST, new AtomicInteger());
    map.put(VOWELS, new AtomicInteger());
    return map;
  }
}
