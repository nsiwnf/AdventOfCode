package com.aoc.aoc2018;

import java.util.List;

import com.aoc.util.ArrayMath;
import com.aoc.util.CharMap;
import com.aoc.util.InputReader;

public class Day2 {

  private static int part1(List<String> input) {
    int twoCt = 0, thrCt = 0;
    CharMap charCount = new CharMap();
    for (String s : input) {
      charCount.clear();
      for (int i = 0; i < s.length(); i++) {
        char c = (char) (s.charAt(i) - 'a');
        charCount.add(c);
      }

      if (charCount.containsValue(2))
        twoCt++;

      if (charCount.containsValue(3))
        thrCt++;
    }

    return thrCt * twoCt;
  }

  private static String part2(List<String> input) {
    int n = input.size();
    StringBuilder common = new StringBuilder();
    for (int i = 0; i < n - 1; i++) {
      byte[] s = input.get(i).getBytes();
      for (int j = i + 1; j < n; j++) {
        byte[] t = input.get(j).getBytes();

        ArrayMath.minus(t, s, false);

        int count = ArrayMath.countEqual(t, (byte) 0);
        if (count == t.length - 1) {
          common.setLength(0);
          for (int k = 0; k < t.length; k++) {
            if (t[k] == 0) {
              common.append((char)s[k]);
            }
          }
          return common.toString();
        }

      }
    }
    return null;
  }

  public static void main(String[] args) {
    List<String> input = InputReader.readInputAsStrings("2018_day2.input");
    System.out.println(part1(input) == 4980);
    System.out.println("qysdtrkloagnfozuwujmhrbvx".equals(part2(input)));

  }
}
