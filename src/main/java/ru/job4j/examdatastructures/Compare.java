package ru.job4j.examdatastructures;

import java.util.List;
import java.util.TreeSet;

import static java.util.Arrays.asList;

public class Compare {

    public static boolean compareByList(String firstWord, String secondWord) {
        List<String> first = List.of(firstWord.split(""));
        List<String> second = List.of(secondWord.split(""));
        if (first.size() != second.size()) {
            return false;
        }
        for (int i = 0; i != first.size(); i++) {
            if (!first.contains(second.get(i)) || !second.contains(first.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareByTree(String firstWord, String secondWord) {
        TreeSet<String> first = new TreeSet<>(asList(firstWord.split("")));
        TreeSet<String> second = new TreeSet<>(asList(secondWord.split("")));
        return first.equals(second);
    }
}
