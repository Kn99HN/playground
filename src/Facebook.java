package src;

import java.util.ArrayList;
import java.util.List;

public class Facebook {
  public static void main(String[] args) {
    int[] numbers = {1, 2, 3};
    printSubsetSum(numbers);
  }

  public static void printSubsetSum(int[] numbers) {
    backtrack(numbers, new ArrayList<Integer>(), 0);
  }

  /**
   * backtrack and append in DFS approach
   *
   * @param numbers
   * @param currentNumbers
   * @param index
   */
  public static void backtrack(int[] numbers, List<Integer> currentNumbers,
                               int index) {
    if (index >= numbers.length) {
      return;
    } else {
      for (int i = index; i < numbers.length; i++) {
        currentNumbers.add(numbers[i]);
        System.out.println(currentNumbers.toString());
        backtrack(numbers, currentNumbers, i + 1);
        currentNumbers.remove(currentNumbers.size() - 1);
      }
    }
  }
}