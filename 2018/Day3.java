package com.aoc.aoc2018;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.util.InputReader;

public class Day3 {
  private static final String regex = "#\\d+ @ (\\d+),(\\d+): (\\d+)x(\\d+)";

  private static final Pattern p = Pattern.compile(regex);

  private static int part1(List<String> input) {
    int sum = 0;
    int[][] grid = new int[1000][1000];
    updateGrid(input, grid);

    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        if (grid[i][j] > 1) {
          sum++;
        }
      }
    }
    return sum;
  }

  private static void updateGrid(List<String> input, int[][] grid) {
    for (String s : input) {
      Matcher m = p.matcher(s);
      if (m.find()) {
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(2));

        int w = Integer.parseInt(m.group(3));
        int h = Integer.parseInt(m.group(4));

        for (int i = 0; i < w; i++) {
          for (int j = 0; j < h; j++) {
            grid[x + i][y + j]++;
          }
        }
      }
    }
  }

  private static int part2(List<String> input) {
    int[][] grid = new int[1000][1000];
    updateGrid(input, grid);

    for (int id = 0; id < input.size(); id++) {
      String s = input.get(id);
      Matcher m = p.matcher(s);
      if (m.find()) {
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(2));

        int w = Integer.parseInt(m.group(3));
        int h = Integer.parseInt(m.group(4));

        boolean isSingle = true;
        for (int i = 0; i < w; i++) {
          for (int j = 0; j < h; j++) {
            isSingle &= (grid[x + i][y + j] == 1);
          }
        }

        if (isSingle) {
          return id + 1;
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    List<String> input = InputReader.readInputAsStrings("2018_day3.input");

    // 98005
    System.out.println("Part 1 : " + part1(input));
    // 331
    System.out.println("Part 2 : " + part2(input));
  }
}
