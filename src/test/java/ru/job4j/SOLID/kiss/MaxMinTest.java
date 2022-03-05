package ru.job4j.SOLID.kiss;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxMinTest {

    @Test
    public void testMax() {
        MaxMin max = new MaxMin();
        List<String> list = List.of("b", "c", "a", "u");
        String result = max.max(list, String::compareTo);
        assertThat(result, is("a"));
    }

    @Test
    public void testMin() {
        MaxMin min = new MaxMin();
        List<String> list = List.of("b", "c", "a", "u");
        String result = min.min(list, String::compareTo);
        assertThat(result, is("u"));
    }
}