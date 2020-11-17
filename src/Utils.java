package src;

import java.util.Iterator;

public class Utils {
    public static String prettyPrint(Object[] arrOfObjs) {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < arrOfObjs.length; i++) {
            Object curr = arrOfObjs[i];
            if (i == arrOfObjs.length - 1) {
                output.append(curr.toString() +  "]");
            } else if(i == 0) {
                output.append("[" + curr.toString() + ", ");
            } else {
                output.append(curr.toString() + ", ");
            }
        }
        return output.toString();
    }

    public static String prettyPrint(Iterable<Object> iterable, int size) {
        StringBuilder output = new StringBuilder();
        Iterator<Object> iterator = iterable.iterator();
        int idx = 0;
        while(iterator.hasNext()) {
            Object curr = iterator.next();
            if (idx == size) {
                output.append(curr.toString() +  "]");
            } else if(idx == 0) {
                output.append("[" + curr.toString() + ", ");
            } else {
                output.append(curr.toString() + ", ");
            }

        }
        return output.toString();
    }
}