package com.aoc.aoc2018;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.util.ArrayMath;
import com.aoc.util.InputReader;

public class Day6 {

  private static final Pattern p = Pattern.compile("(\\d+), (\\d+)");

  private static int part1(List<String> input) {
    int maxX = -1, maxY = -1;
    int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    List<Point> distances = new ArrayList<>();
    for (String s : input) {
      Matcher m = p.matcher(s);
      if (m.find()) {
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(2));

        maxX = Math.max(maxX, x);
        minX = Math.min(minX, x);

        maxY = Math.max(maxY, y);
        minY = Math.min(minY, y);

        distances.add(new Point(x, y));
      }
    }

    int[] map = new int[distances.size()];
    for (int i = minX; i < maxX; i++) {
      for (int j = minY; j < maxY; j++) {
        int c = findClosest(i, j, distances);
        if (c != -1) {
          map[c]++;
        }
      }
    }

    // Check unchanged
    int[] map2 = new int[distances.size()];
    for (int i = 0; i < maxX * 2; i++) {
      for (int j = 0; j < maxY * 2; j++) {
        int c = findClosest(i, j, distances);
        if (c != -1) {
          map2[c]++;
          if (map2[c] > map[c])
            map[c] = 0;
        }
      }
    }

    System.out.println(Arrays.toString(map));
    return ArrayMath.max(map);
  }

  private static int findClosest(int x, int y, List<Point> distances) {
    int distance = Integer.MAX_VALUE;
    int c = -1;
    for (int i = 0; i < distances.size(); i++) {
      Point d = distances.get(i);
      int dC = Math.abs(x - d.x) + Math.abs(y - d.y);
      if (dC < distance) {
        c = i;
        distance = dC;
      }
      else if (dC == distance) {
        c = -1;
      }
    }
    return c;
  }

  private static int part2(List<String> input) {
    int maxX = -1, maxY = -1;
    int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    List<Point> distances = new ArrayList<>();

    for (String s : input) {
      Matcher m = p.matcher(s);
      if (m.find()) {
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(2));

        maxX = Math.max(maxX, x);
        minX = Math.min(minX, x);

        maxY = Math.max(maxY, y);
        minY = Math.min(minY, y);

        distances.add(new Point(x, y));

      }
    }

    int total = 0;
    for (int i = minX; i < maxX; i++) {
      for (int j = minY; j < maxY; j++) {
        int x = i;
        int y = j;
        int t = distances.stream().mapToInt((xy) -> Math.abs(xy.x - x) + Math.abs(xy.y - y)).sum();
        if (t < 10000) {
          total++;
        }
      }
    }

    return total;
  }

  public static void main(String[] args) {
    List<String> input = InputReader.readInputAsStrings("2018_day6.input");
    // List<String> input = Arrays.asList("1, 1", "1, 6", "8, 3", "3, 4", "5,
    // 5", "8, 9");

    System.out.println(part1(input)); // 4284
    System.out.println(part2(input)); // 35490

  }
}
