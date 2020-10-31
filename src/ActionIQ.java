package src;
public class ActionIQ {
  public static void main(String[] args) {
    String firstNumb = "9";
    String secondNumb = "-123";
    if (secondNumb.charAt(0) == '-' && firstNumb.charAt(0) != '-') {
      String tmp = firstNumb;
      firstNumb = secondNumb;
      secondNumb = tmp;
    }
    System.out.println(subtractNumb(firstNumb, secondNumb));
  }

  public static boolean bothNegative(String firstNumb, String secondNumb) {
    return firstNumb.charAt(0) == '-' && secondNumb.charAt(0) == '-';
  }

  public static String subtractNumb(String firstNumb, String secondNumb) {
    // assert firstNumb > secondNumb;
    StringBuilder output = new StringBuilder();
    int firstIndex = firstNumb.length() - 1;
    int secondIndex = secondNumb.length() - 1;
    int carry = 0;
    char start = '0';
    while (firstIndex >= 0 || secondIndex >= 0) {
      int firstValue = 0;
      int secondValue = 0;
      if (firstIndex >= 0 && firstNumb.charAt(firstIndex) != '-') {
        firstValue = firstNumb.charAt(firstIndex) - start;
      }
      if (secondIndex >= 0 && secondNumb.charAt(secondIndex) != '-') {
        secondValue = secondNumb.charAt(secondIndex) - start;
      }
      int diff = firstValue - secondValue - carry;
      if (diff < 0) {
        diff += 10;
        carry = 1;
      } else {
        carry = 0;
      }
      if (diff == 0 && (firstIndex == 0 || secondIndex == 0)) {
        firstIndex--;
        secondIndex--;
        continue;
      }
      output.append(diff);
      firstIndex--;
      secondIndex--;
    }

    if (carry < 0) {
      output.append(carry);
    }

    if (firstNumb.charAt(0) == '-' || secondNumb.charAt(0) == '-') {
      output.append("-");
    }
    return output.reverse().toString();
  }

  public static String addNumb(String firstNumb, String secondNumb) {
    StringBuilder output = new StringBuilder();
    int firstIndex = firstNumb.length() - 1;
    int secondIndex = secondNumb.length() - 1;
    int carry = 0;
    char start = '0';
    while (firstIndex >= 0 || secondIndex >= 0) {
      int firstValue = 0;
      int secondValue = 0;
      if (firstIndex >= 0) {
        firstValue = firstNumb.charAt(firstIndex) - start;
      }
      if (secondIndex >= 0) {
        secondValue = secondNumb.charAt(secondIndex) - start;
      }
      int sum = firstValue + secondValue + carry;
      if (sum >= 10) {
        carry = sum / 10;
        sum = sum % 10;
      } else {
        carry = 0;
      }
      output.append(sum);
      firstIndex--;
      secondIndex--;
    }
    if (carry > 0) {
      output.append(carry);
    }
    if (bothNegative(firstNumb, secondNumb)) {
      output.append('-');
    }
    return output.reverse().toString();
  }
}