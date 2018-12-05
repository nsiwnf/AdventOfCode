public class Day5 {

  private static int part1(String input) {
    return getString(input, '\0').length();
  }

  private static int part2(String input) {
    int min = input.length();
    for (int i = 0; i < 26; i++) {
      String s = getString(input, (char) (i + 'a'));
      if (s.length() < min) {
        min = s.length();
      }
    }
    return min;
  }

  private static String getString(String input, char skipChar) {
    int delta = Math.abs('A' - 'a');
    byte[] s = input.getBytes();
    byte[] r = new byte[s.length];

    int index = 0;
    for (byte c : s) {
      if (c != skipChar && c != skipChar - delta) { // skip
        if (index > 0 && Math.abs(c - r[index - 1]) == delta) {
          index--;
        }
        else {
          r[index] = c;
          index++;
        }
      }
    }

    return new String(r, 0, index);
  }
}
