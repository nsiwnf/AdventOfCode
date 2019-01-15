package com.aoc.aoc2018;

public class Day19 {

  private static int part1(int initA) {
    int a = 0, b = 0, c = 0, e = 17;

    a = initA;

    while (true) {
      switch (e) {
      case 1:
        int d = 1;
        while (d <= c) {
          if (c % d == 0)
            a += d;
          d += 1;
        }
        return a;
      case 17:
        c += 2;
        c *= c;
        c *= 19 * 11;
        b += 2;
        b *= 22;
        b += 7;
        c += b;
        e = 26 + a;
        break;
      case 26:
        e = 1;
        break;
      case 27:
        b = 27;
        b *= 28;
        b += 29;
        b *= 30;
        b *= 14;
        b *= 32;
        c += b;
        a = 0;
        e = 1;
        break;
      default:
        return a;
      }
    }

  }

  public static void main(String[] args) {
    // 888
    System.out.println("Part 1 : " + part1(0));
    // 10708992
    System.out.println("Part 2 : " + part1(1));
  }
}
