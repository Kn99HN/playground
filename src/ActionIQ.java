package src;
public class ActionIQ {
  public static void main(String[] args) {
    String firstNumb = "123";
    String secondNumb = "9";
    System.out.println(addNumb(firstNumb, secondNumb));
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
    if(carry > 0) {
        output.append(carry);
    }
    return output.reverse().toString();
  }
}