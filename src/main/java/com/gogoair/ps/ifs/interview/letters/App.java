package com.gogoair.ps.ifs.interview.letters;

import com.gogoair.ps.ifs.interview.letters.thread.CounterWorker;
import com.gogoair.ps.ifs.interview.letters.util.Utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
  public static void main(String[] args) throws IOException, InterruptedException {
    final int count = 1026;
    Utility.generateFile(count);
    final CountDownLatch countDownLatch = new CountDownLatch(count);
    final ConcurrentHashMap<String, AtomicInteger> map = Utility.initMap();
    final ExecutorService executor = Executors.newFixedThreadPool(50);
    try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream
          (new File("data.txt")))) {
      int value;
      while ((value = bufferedInputStream.read()) != -1) {
        executor.execute(new CounterWorker(map, countDownLatch, value));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    countDownLatch.await();
    executor.shutdown();
    System.out.println(map);
  }
}
