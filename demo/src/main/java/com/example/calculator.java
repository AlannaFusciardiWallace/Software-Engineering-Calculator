package com.example;

import java.util.Scanner;

public class calculator {

    private stack<Character> operatorStack;
    private stack<Double> valueStack;
    private boolean error;

    public calculator() {
        operatorStack = new stack<Character>();
        valueStack = new stack<Double>();
        error = false;
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*';
    }

    private int getOrder(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        }
        if (ch == '*') {
            return 2;
        }
        return 0;
    }

    private void processOperator(char t) {
        double a, b;
        if (valueStack.empty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            b = valueStack.peek();
            valueStack.pop();
        }
        if (valueStack.empty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            a = valueStack.peek();
            valueStack.pop();
        }
        double r;
        if (t == '+') {
            r = a + b;
        } else if (t == '-') {
            r = a - b;
        } else if (t == '*') {
            r = a * b;
        } else {
            System.out.println("Operator error.");
            error = true;
            return;
        }
        valueStack.push(r);
    }

    public void processInput(String input) {

        String[] tokens = input.split(" ");

        // Process all input tokens
        for (int n = 0; n < tokens.length; n++) {
            String nextToken = tokens[n];
            char ch = nextToken.charAt(0);
            if (ch >= '0' && ch <= '9') {
                double value = Double.parseDouble(nextToken);
                valueStack.push(value);
            } else if (isOperator(ch)) {
                if (operatorStack.empty() || getOrder(ch) > getOrder(operatorStack.peek())) {
                    operatorStack.push(ch);
                } else {
                    while (!operatorStack.empty() && getOrder(ch) <= getOrder(operatorStack.peek())) {
                        char toProcess = operatorStack.peek();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    operatorStack.push(ch);
                }
            } else if (ch == '(') {
                operatorStack.push(ch);
            } else if (ch == ')') {
                while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
                    char toProcess = operatorStack.peek();
                    operatorStack.pop();
                    processOperator(toProcess);
                }
                if (!operatorStack.empty() && operatorStack.peek() == '(') {
                    operatorStack.pop();
                } else {
                    System.out.println("Error: unbalanced parenthesis.");
                    error = true;
                }
            }

        }
        // Empty out the operator stack at the end of the input
        while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
            char toProcess = operatorStack.peek();
            operatorStack.pop();
            processOperator(toProcess);
        }
        // Print the results
        if (error == false) {
            double result = valueStack.peek();
            valueStack.pop();
            if (!operatorStack.empty() || !valueStack.empty()) {
                System.out.println("Expression error.");
            } else {
                System.out.println("The result is " + result);
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // The original input
        System.out.print("Input sum to calculate: ");
        String userInput = input.nextLine();

        calculator calc = new calculator();
        calc.processInput(userInput);
    }
}
