package com.aoc.aoc2018;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

import com.aoc.util.InputReader;

public class Day8 {

  private static int part1(int[] input) {
    return getMetadata(input, 0).x;
  }

  // x = sum, y = nextIndex
  private static Point getMetadata(int[] data, int index) {
    int chldCt = data[index];
    int metaCt = data[index + 1];

    if (chldCt == 0) {
      return getMetaForChildless(data, index, metaCt);
    }

    Point result = new Point(0, index);
    int nextIndex = index + 2;
    for (int i = 0; i < chldCt; i++) {
      Point c = getMetadata(data, nextIndex);
      result.x += c.x;
      nextIndex = c.y;
    }

    for (int i = 0; i < metaCt; i++) {
      result.x += data[nextIndex + i];
    }

    result.y = nextIndex + metaCt;
    return result;
  }


//  private static int part12(int[] data) {
//    int total = 0;
//    Stack<Integer> points = new Stack<>();
//    points.push(0);
//    while(!points.isEmpty()) {
//      int nextIndex = points.pop();
//
//      int chldCt = data[nextIndex];
//      int metaCt = data[nextIndex + 1];
//
//      if (chldCt == 0) {
//        nextIndex += 2;
//        for (int i = 0; i < metaCt; i++) {
//          total += data[nextIndex++];
//        }
//        points.push(nextIndex);
//      } else {
//
//      }
//
//
//
//    }
//  }

  private static Point getMetaForChildless(int[] data, int index, int metaCt) {
    int sum = IntStream.range(0, metaCt).map(i -> data[index + 2 + i]).sum();
    return new Point(sum, index + 2 + metaCt);
  }

  private static Point getMetadata2(int[] data, int index) {
    int chldCt = data[index];
    int metaCt = data[index + 1];

    if (chldCt == 0) {
      return getMetaForChildless(data, index, metaCt);
    }

    Point result = new Point(0, index);
    int nextIndex = index + 2;
    int[] childValues = new int[chldCt];
    for (int i = 0; i < chldCt; i++) {
      Point c = getMetadata2(data, nextIndex);
      childValues[i] = c.x;
      nextIndex = c.y;
    }

    for (int i = 0; i < metaCt; i++) {
      int childIndex = data[nextIndex + i] - 1;
      if (childIndex < chldCt)
        result.x += childValues[childIndex];
    }
    result.y = nextIndex + metaCt;
    return result;
  }

  private static int part2(int[] input) {
    return getMetadata2(input, 0).x;
  }

  public static void main(String[] args) {
    int[] test = Arrays.stream("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2".split(" ")).mapToInt(Integer::parseInt).toArray();
    // System.out.println(part1(test));
    // System.out.println(part2(test));

    String input = InputReader.readInputAsOneString("2018_day8.input");
    int[] input1 = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    System.out.println(part1(input1));
    System.out.println(part2(input1));
  }
}
