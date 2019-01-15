package com.aoc.aoc2018;

import java.util.ArrayList;
import java.util.List;

public class Day21 {

  private static int part1() {

    List<Long> duplicates = new ArrayList<>();

    int minVal = 0;

    long a = 0; // 0
    long b = 0; // 1
    long c = 0; // 2
    long d = 0; // 3
    int e = 0; // 4
    long f = 0; // 5

    boolean halted = false;
    while (!halted) {
//      System.out.printf("[%d, %d, %d, %d, %d, %d]\n", a, b, c, d, e, f);
      switch (e) {
      case 0:
        f = 0b1111011;
      case 1:
        f = f & 0b1_11001000;
      case 2:
        f = f == 0b1001000 ? 1 : 0;
      case 3:
        e = (int) (f) + 4;
        break;
      case 4:
        e = 1;
        break;
      case 5:
        f = 0;
      case 6:
        d = f | 0b1_00000000_00000000;
      case 7:
        f = 0b1011_00110010_10111100;
      case 8:
        b = d & 0b11111111;
      case 9:
        f = f + b;
      case 10:
        f = f & 0b11111111_11111111_11111111;
      case 11:
        f = f * 65899;
      case 12:
        f = f & 16777215;
      case 13:
        b = 256 > d ? 1 : 0;
      case 14:
        e = (int) (b) + 15;
        break;
      case 15:
        e = 17;
        break;
      case 16:
        e = 28;
        break;
      case 17:
        b = 0;
      case 18:
        c = b + 1;
      case 19:
        c = c * 256;
      case 20:
        c = c > d ? 1 : 0;
      case 21:
        e = (int) (c) + 22;
        break;
      case 22:
        e = 24;
        break;
      case 23:
        e = 26;
        break;
      case 24:
        b = b + 1;
      case 25:
        e = 18;
        break;
      case 26:
        d = b;
      case 27:
        e = 8;
        break;
      case 28:
        b = f == a ? 1 : 0;
        if (duplicates.contains(f)) {
          System.out.println("Part 2: " + duplicates.get(duplicates.size() - 1));
          halted = true;
        }
        else if (duplicates.isEmpty()) {
          System.out.println("Part 1: " + f);
        }
        duplicates.add(f);
      case 29:
        e = (int) (b) + 30;
        break;
      case 30:
        e = 6;
        break;
      default:
        halted = true;
      }
    }

    return minVal;
  }

  public static void main(String[] args) {
    // Part 1: 2884703
    // Part 2: 15400966
    part1();
  }
}
