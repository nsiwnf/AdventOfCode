package com.aoc.aoc2018;

import com.aoc.util.InputReader;

public class Day5 {

  private static int part1(String input) {
    return getString(input.getBytes(), '\0').length();
  }

  private static int part2(String input) {
    int min = input.length();
    byte[] inputAsBytes = input.getBytes();
    for (int i = 0; i < 26; i++) {
      String s = getString(inputAsBytes, (char) (i + 'a'));
      if (s.length() < min) min = s.length();
    }
    return min;
  }

  private static String getString(byte[] s, char skipChar) {
    int delta = Math.abs('A' - 'a');
    byte[] r = new byte[s.length];
    int index = 0;
    for (byte c : s)
      if (c != skipChar && c != skipChar - delta)
        if (index > 0 && ((r[index - 1] - c) == delta || (c - r[index - 1]) == delta)) index--;
        else r[index++] = c;
    return new String(r, 0, index);
  }

  public static void main(String[] args) {
    String input = InputReader.readInputAsOneString("2018_day5.input");

    long start = System.currentTimeMillis();
    // 9704
    System.out.println(part1(input));
    // 6942
    System.out.println(part2(input));
    long end = System.currentTimeMillis();
    System.out.println((end - start));
  }
}
