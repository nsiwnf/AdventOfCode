package com.aoc.aoc2018;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aoc.util.InputReader;

public class Day10 {

  private static int part1(List<String[]> input) {

    List<Point> points = new ArrayList<>(input.size());
    List<Point> velocities = new ArrayList<>(input.size());

    for (String[] s : input) {
      int startX = Integer.parseInt(s[0].trim());
      int startY = Integer.parseInt(s[1].trim());
      points.add(new Point(startX, startY));

      int velX = Integer.parseInt(s[2].trim());
      int velY = Integer.parseInt(s[3].trim());
      velocities.add(new Point(velX, velY));

    }

    int maxX = 0;
    int maxY = 0;
    int minX = 0;
    int minY = 0;
    boolean withinRange = false;
    int range = 170;
    int seconds = 0;
    while (!withinRange) {
      seconds++;
      maxX = 0;
      maxY = 0;
      minX = 0;
      minY = 0;
      for (int i = 0; i < points.size(); i++) {
        Point p = points.get(i);
        Point v = velocities.get(i);

        p.x += v.x;
        p.y += v.y;

        maxX = Math.max(maxX, p.x);
        maxY = Math.max(maxY, p.y);

        minX = Math.min(minX, p.x);
        minY = Math.min(minY, p.y);
      }

      withinRange = (maxX - minX <= range) && (maxY - minY <= range);
    }

    int offsetX = maxX - minX;
    int offsetY = maxY - minY;
    char[][] grid = new char[maxY + offsetY + 1][maxX + offsetX + 1];
    for(int x =0; x < grid.length; x++) {
      Arrays.fill(grid[x], ' ');
    }

    for(Point p : points) {
      grid[p.y-minY][p.x-minX] = '#';
    }

    for(int y = minY; y <= maxY; y++) {
    for(int x = minX; x <= maxX; x++) {

        System.out.print(grid[y][x]);
      }
      System.out.println();
    }

    System.out.println(seconds);
    return -1;
  }

  private static int part2(List<Integer> input) {
    Set<Integer> freq = new HashSet<>();
    int f = 0, i = 0;
    while (freq.add(f)) {
      f += input.get(i++ % input.size());
    }
    return f;
  }

  public static void main(String[] args) {
    // position=< 52543, 42020> velocity=<-5, -4>
    List<String[]> input = InputReader.readInputAsRegex("2018_day10.input",
        "position=<([\\- ]\\d+), ([- ]\\d+)> velocity=<([- ]\\d+), ([- ]\\d+)");

    System.out.println("Part 1 : " + part1(input));

  }
}
