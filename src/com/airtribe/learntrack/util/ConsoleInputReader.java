package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.Scanner;

public class ConsoleInputReader {
    public static int readInt(Scanner scanner) throws InvalidInputException {
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new InvalidInputException("Input cannot be empty.");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new InvalidInputException("'" + input + "' is not a valid numerical value.");
        }
    }
}
