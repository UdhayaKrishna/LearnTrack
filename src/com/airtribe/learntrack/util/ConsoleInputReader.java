package com.airtribe.learntrack.util;

import java.util.Scanner;

public class ConsoleInputReader {
    public static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
