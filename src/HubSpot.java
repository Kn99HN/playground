package src;

import src.Utils;

public class HubSpot {
  public static void main(String[] agrs) {
    int[] first = new int[] {1, 2, 3};
    int[] second = new int[] {2, 3, 4};
    Integer[] output = mergeSort(first, second, 2);
    System.out.println(Utils.prettyPrint(output));

    int[] left = new int[] {1, 2};
    int[] right = new int[] {2, 3, 4};
    Integer[] secondOutput = mergeSort(left, right, 5);
    System.out.println(Utils.prettyPrint(secondOutput));
  }

  /**
   * Merge 2 sorted array up to k values
   */
  public static Integer[] mergeSort(int[] left, int[] right, int k) {
    Integer[] output;
    if (k <= left.length + right.length) {
      output = new Integer[k];
    } else {
      output = new Integer[left.length + right.length];
      k = left.length + right.length;
    }
    int leftIdx = 0;
    int rightIdx = 0;
    int outputIdx = 0;
    while (k > 0) {
      if (leftIdx >= left.length) {
        leftIdx = -1;
      }
      if (rightIdx >= right.length) {
        rightIdx = -1;
      }
      if (leftIdx != -1 && rightIdx != -1) {
        int leftVal = left[leftIdx];
        int rightVal = right[rightIdx];
        if (left[leftIdx] == right[rightIdx]) {
          if (k >= 2) {
            output[outputIdx++] = leftVal;
            output[outputIdx++] = rightVal;
            leftIdx++;
            rightIdx++;
            k -= 2;
          } else {
            output[outputIdx++] = leftVal;
            leftIdx++;
            k--;
          }
        } else if (leftVal < rightVal) {
          output[outputIdx++] = leftVal;
          leftIdx++;
          k--;
        } else {
          output[outputIdx++] = rightVal;
          rightIdx++;
          k--;
        }
      } else if (leftIdx != -1) {
        output[outputIdx++] = left[leftIdx++];
        k--;
      } else if (rightIdx != -1) {
        output[outputIdx++] = right[rightIdx++];
        k--;
      }
    }
    return output;
  }
}