package com.aoc.aoc2018;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aoc.util.InputReader;

public class Day1 {

  private static int part1(List<Integer> input) {
    return input.stream().reduce((a, b) -> a + b).get();
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
    List<Integer> input = InputReader.readInputAsIntegers("2018_day1.input");

    System.out.println("Part 1 : " + part1(input));
    System.out.println("Part 2 : " + part2(input));
  }
}
