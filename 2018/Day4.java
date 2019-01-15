package com.aoc.aoc2018;

import com.aoc.util.InputReader;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

  private static final Pattern p = Pattern.compile("\\[\\d+-\\d+-\\d+ \\d+:(\\d+)] .\\w+ .(\\d*)");

  private static void part1and2(List<String> input) {
    Map<Integer, int[]> map = new HashMap<>();
    int minFalls = 0;

    int id = -1;
    for (String s : input) {
      Matcher m = p.matcher(s);
      if (m.find()) {
        if (s.contains("Guard"))
          id = Integer.parseInt(m.group(2));
        else if (s.contains("falls"))
          minFalls = Integer.parseInt(m.group(1));
        else if (s.contains("wakes")) {
          int minWakes = Integer.parseInt(m.group(1));
          int[] minutesAsleep = map.computeIfAbsent(id, k -> new int[60]);
          for (int i = minFalls; i < minWakes; i++)
            minutesAsleep[i]++;
        }
      }
    }

    int maxAsleepOnMinute = 0, maxTotaTimeAsleep = 0;
    int maxAOMGuard = -1, maxAOMMin = -1;
    int maxTTAGuard = -1, maxTTAMin = -1;
    for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
      int[] minutesAsleep = entry.getValue();
      int sum = 0;
      int maxTTAminValue = -1;
      int maxTTAminKey = -1;
      for (int i = 0; i < minutesAsleep.length; i++) {
        if (minutesAsleep[i] > maxAsleepOnMinute) {
          maxAsleepOnMinute = minutesAsleep[i];
          maxAOMMin = i;
          maxAOMGuard = entry.getKey();
        }
        sum += minutesAsleep[i];
        if(minutesAsleep[i] > maxTTAminValue) {
          maxTTAminValue = minutesAsleep[i];
          maxTTAminKey = i;
        }
      }
      if (sum > maxTotaTimeAsleep) {
        maxTotaTimeAsleep = sum;
        maxTTAMin = maxTTAminKey;
        maxTTAGuard = entry.getKey();
      }
    }

    System.out.println("Part 1 : " + maxTTAGuard * maxTTAMin); // 36898
    System.out.println("Part 2 : " + maxAOMGuard * maxAOMMin); // 80711
  }

  public static void main(String[] args) {

     List<String> input = InputReader.readInputAsStrings("2018_day4.input");
//    List<String> input = Arrays.asList(
//        "[1518-11-01 00:00] Guard #10 begins shift",
//        "[1518-11-01 00:05] falls asleep",
//        "[1518-11-01 00:25] wakes up",
//        "[1518-11-01 00:30] falls asleep",
//        "[1518-11-01 00:55] wakes up",
//        "[1518-11-01 23:58] Guard #99 begins shift",
//        "[1518-11-02 00:40] falls asleep",
//        "[1518-11-02 00:50] wakes up",
//        "[1518-11-03 00:05] Guard #10 begins shift",
//        "[1518-11-03 00:24] falls asleep",
//        "[1518-11-03 00:29] wakes up",
//        "[1518-11-04 00:02] Guard #99 begins shift",
//        "[1518-11-04 00:36] falls asleep",
//        "[1518-11-04 00:46] wakes up",
//        "[1518-11-05 00:03] Guard #99 begins shift",
//        "[1518-11-05 00:45] falls asleep",
//        "[1518-11-05 00:55] wakes up");

    Collections.sort(input);
    part1and2(input);
  }
}
