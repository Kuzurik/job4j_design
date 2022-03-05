package ru.job4j.solid.kiss;


import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    /**
     * Method receives list of objects, comparator and condition for compare.
     * cycle sort out our list and by dint of predicate we set condition for compare.
     * @param value list of values
     * @param comparator comparator for our objects
     * @param predicate condition for compare
     * @param <T> our object type
     * @return object T
     */

    public <T> T compare(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T o = value.get(0);
        for (T v : value) {
            if (predicate.test(comparator.compare(o, v))) {
                o = v;
            }
        }
        return o;
    }

    /**
     * Set List of values and comparator.
     * The compare method compares each item in our list
     * and returns max value
     * @param value list
     * @param comparator comparator
     * @param <T> our object type
     * @return max value
     */

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator, p -> p > 0);
    }

    /**
     * Set List of values and comparator.
     * The compare method compares each item in our list
     * and returns min value
     * @param value list
     * @param comparator comparator
     * @param <T> our object type
     * @return min value
     */

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator, p -> p < 0);
    }
}
