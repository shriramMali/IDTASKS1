
package com.Alex;
        import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Ask the user for the first number
        System.out.print("Enter the first number: ");
        double num1 = input.nextDouble();

        // Ask the user for the second number
        System.out.print("Enter the second number: ");
        double num2 = input.nextDouble();

        // Ask the user for the operation they want to perform
        System.out.print("Enter the operation (+, -, *, /): ");
        char operator = input.next().charAt(0);

        // Perform the calculation based on the operation
        double result = 0.0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("Error: Division by zero.");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                System.out.println("Error: Invalid operation.");
                return;
        }

        // Print the result
        System.out.println("The result is: " + result);
    }

}