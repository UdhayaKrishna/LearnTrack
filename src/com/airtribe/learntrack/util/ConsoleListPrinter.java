package com.airtribe.learntrack.util;

import java.util.List;

public class ConsoleListPrinter {
    public static <T> void printList(List<T> items, String emptyMessage) {
        if (items.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (T item : items) {
            System.out.println(item);
        }
    }
}
