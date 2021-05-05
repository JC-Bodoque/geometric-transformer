import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotationConverter {
    public static Stack operators = new Stack();

    public static void main(String argv[]) throws IOException {
        String infix;
        // create an input stream object
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        // get input from user
        System.out.print("\nEnter the infix expression you want to convert: ");
        infix = keyboard.readLine();
        // output as postfix
        System.out.println("Postfix expression for the given infix expression is:" + toPostfix(infix));
    }

    // converts an infix expression to postfix
    private static String toPostfix(String infix) {
        char symbol;
        String postfix = "";
        // while there is input to be read
        for (int i = 0; i < infix.length(); ++i) {
            symbol = infix.charAt(i);
            // if it's an operand, add it to the string
            if (Character.isLetter(symbol))
                postfix = postfix + symbol;
            // push (
            else if (symbol == '(') {
                operators.push(symbol);
            } else if (symbol == ')') { // push everything back to (
                while (operators.peek() != '(') {
                    postfix = postfix + operators.pop();
                }
                operators.pop(); // remove '('
            } else {
                // print operators occurring before it that have greater precedence
                while (!operators.isEmpty() && !(operators.peek() == '(') && prec(symbol) <= prec(operators.peek()))
                    postfix = postfix + operators.pop();
                operators.push(symbol);
            }
        }
        while (!operators.isEmpty())
            postfix = postfix + operators.pop();
        return postfix;
    }

    private static String toPrefix(String infix) {
        char symbol;
        String pretfix = "";
        // while there is input to be read
        for (int i = 0; i < infix.length(); ++i) {
            symbol = infix.charAt(i);
            // if it's an operand, add it to the string
            if (Character.isLetter(symbol))
                pretfix = pretfix + symbol;
            // push (
            else if (symbol == '(') {
                operators.push(symbol);
            } else if (symbol == ')') { // push everything back to (
                while (operators.peek() != '(') {
                    pretfix = pretfix + operators.pop();
                }
                operators.pop(); // remove '('
            } else {
                // print operators occurring before it that have greater precedence
                while (!operators.isEmpty() && !(operators.peek() == '(') && prec(symbol) <= prec(operators.peek()))
                    pretfix = pretfix + operators.pop();
                operators.push(symbol);
            }
        }
        while (!operators.isEmpty())
            pretfix = pretfix + operators.pop();
        return pretfix;
    }

    static int prec(char x) {
        if (x == '+' || x == '-')
            return 1;
        if (x == '*' || x == '/' || x == '%')
            return 2;
        return 0;
    }

    private static class Stack {
        private char a[] = new char[100];
        private int top = -1;

        void push(char c) {
            try {
                a[++top] = c;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Stack full, no room to push, size = 100");
                System.exit(0);
            }
        }

        private char pop() {
            return a[top--];
        }

        private boolean isEmpty() {
            return (top == -1) ? true : false;
        }

        private char peek() {
            return a[top];
        }
    }
}
