package com.aoc.aoc2018;

import java.util.Arrays;

import com.aoc.util.IntNode;

public class Day9 {

  private static long part1(int players, int marble) {
    long[] playerScore = new long[players];
    IntNode current = new IntNode(0);
    current.next = new IntNode(1);
    current.prev = current.next;

    // circle
    current.next.prev = current;
    current.next.next = current;

    for (int m = 2; m < marble + 1; m++) {
      if (m % 23 == 0) {
        int playerIndex = (m - 1) % players;
        playerScore[playerIndex] += m;

        for (int i = 0; i < 6; i++)
          current = current.prev;
        IntNode newCurrent = current.prev;
        playerScore[playerIndex] += current.remove();
        current = newCurrent;
      }
      else {
        current = current.next;
        current = current.next;
        current.addAfter(new IntNode(m));
      }

      if (m % 100000 == 0) {
        System.out.println(m);
      }
    }

    while (current.value != 0) {
      current = current.next;
    }
    return Arrays.stream(playerScore).max().getAsLong();
  }

  private static long part2(int players, int marble) {
    long[] playerScore = new long[players];
    int[] marbles = new int[marble - marble / 23];

    marbles[0] = 0;
    marbles[1] = 1;
    int marbleLen = 2;

    int currentIndex = 1;
    for (int m = 2; m <= marble; m++) {
      if (m % 23 == 0) {
        int playerIndex = (m - 1) % players;
        playerScore[playerIndex] += m;
        currentIndex = (currentIndex + marbleLen - 7) % marbleLen;
        playerScore[playerIndex] += marbles[currentIndex];
        marbleLen--;
        System.arraycopy(marbles, currentIndex + 1, marbles, currentIndex, marbleLen - currentIndex);
      }
      else {
        currentIndex += 2;
        if (currentIndex == marbleLen) {
          marbles[marbleLen] = m;
        }
        else {
          currentIndex = currentIndex % marbleLen;
          System.arraycopy(marbles, currentIndex, marbles, currentIndex + 1, marbleLen - currentIndex);
          marbles[currentIndex] = m;
        }
        marbleLen++;
      }

      if (m % 100000 == 0) {
        System.out.println(m);
      }

    }
    return Arrays.stream(playerScore).max().getAsLong();
  }

  public static void main(String[] args) {
    int players = 464;
    int marble = 71730;

    // 32
    // players = 9;
    // marble = 25;

    // 380705
    // System.out.println("Part 1 : " + part1(players, marble));
    // System.out.println("Part 1 : " + part2(players, marble));
    long start = System.currentTimeMillis();
    // 3171801582
    System.out.println("Part 2 : " + part1(players, marble * 100)); // 3624s
    // System.out.println("Part 2 : " + part2(players, marble * 100)); //3459s
    long end = System.currentTimeMillis();
    System.out.println("Time : " + (end - start) / 1000);

  }
}
