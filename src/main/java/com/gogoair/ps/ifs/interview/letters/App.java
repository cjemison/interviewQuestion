package com.gogoair.ps.ifs.interview.letters;

import com.gogoair.ps.ifs.interview.letters.thread.CounterWorker;
import com.gogoair.ps.ifs.interview.letters.util.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
  private static final int BUFFER_SIZE = 1024;

  public static void main(String[] args) throws IOException {
    final ConcurrentHashMap<String, AtomicInteger> map = Utility.initMap();
    final ExecutorService executor = Executors.newFixedThreadPool(100);
    try (FileInputStream fileInputStream = new FileInputStream(new File("data.txt"))) {
      final byte[] buffer = new byte[BUFFER_SIZE];
      int read;
      while ((read = fileInputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
        executor.execute(new CounterWorker(map, Arrays.copyOf(buffer, BUFFER_SIZE)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    executor.shutdown();
    System.out.println(map);
  }
}
