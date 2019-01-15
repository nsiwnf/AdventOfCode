package com.aoc.aoc2018;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.util.InputReader;

public class Day12 {

  private static final Pattern p = Pattern.compile("(.....) => (.)");

  private static long countPots(String s, long zero) {
    long sum = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '#') {
        sum += i - zero;
      }
    }
    return sum;
  }

  private static long part2(String initial, List<String> input, long generations) {
    Map<String, Character> map = new HashMap<>(input.size());
    for (String s : input) {
      Matcher m = p.matcher(s);
      if (m.find()) {
        char value = m.group(2).charAt(0);
        if (value == '#')
          map.put(m.group(1), value);
      }
    }

    long zero = 5;
    StringBuilder state = new StringBuilder("....." + initial + ".....");
    StringBuilder newState = new StringBuilder();
    for (int i = 0; i < generations; i++) {
      newState.setLength(0);

      for (int s = 2; s < state.length() - 2; s++) {
        String section = state.substring(s - 2, s + 3);
        newState.append(map.getOrDefault(section, '.'));
      }
      zero -= 2;

      if (state.substring(1).startsWith(newState.toString())) {
        return 42 * (generations - i - 1) + countPots(newState.toString(), zero);
      }

      state.setLength(0);
      if (!newState.substring(0, 5).equals(".....")) {
        state.append(".....");
        zero += 5;
      }
      state.append(newState);

      while (state.substring(state.length() - 5, state.length()).equals(".....")) {
        state.setLength(state.length() - 5);

      }
      state.append(".....");

      // System.out.println(state.toString());
    }
    // System.out.println(state.substring(zero));
    return countPots(state.toString(), zero);
  }

  public static void main(String[] args) {
    List<String> input = InputReader.readInputAsStrings("2018_day12.input");
    String initialState = "##....#.#.#...#.#..#.#####.#.#.##.#.#.#######...#.##....#..##....#.#..##.####.#..........#..#...#";

    // 325
    // input = InputReader.readInputAsStrings("2018_day12_test.input");
    // initialState = "#..#.#..##......###...###";

    // 2100000001168
     System.out.println("Part 1 : " + part2(initialState, input, 20));
    System.out.println("Part 2 : " + part2(initialState, input, 50000000000L));
  }
}
