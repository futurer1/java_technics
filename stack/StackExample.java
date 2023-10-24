package advanced1;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<>();

        myStack.push(1);
        myStack.push(3);
        myStack.push(5);
        myStack.push(7);

        System.out.println(myStack.peek()); // 7
        System.out.println(myStack); // [1, 3, 5, 7]
        System.out.println(myStack.pop()); // 7
        System.out.println(myStack); // [1, 3, 5]
        System.out.println(myStack.pop()); // 5
        System.out.println(myStack); // [1, 3]

        while (!myStack.empty()) { // выполнится 2 раза
            System.out.println(myStack.pop());
        }
    }
}
