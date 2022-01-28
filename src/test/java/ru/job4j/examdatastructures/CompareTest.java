package ru.job4j.examdatastructures;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static ru.job4j.examdatastructures.Compare.compareByList;
import static ru.job4j.examdatastructures.Compare.compareByTree;

public class CompareTest {

    @Test
    public void whenCompareByListSameWordsThanTrue() {
        boolean result = compareByList("seven", "seven");
        assertThat(result, is(true));
    }

    @Test
    public void whenCompareByListTwoWordsThanTrue() {
        boolean result = compareByList("seven", "senev");
        assertThat(result, is(true));
    }

    @Test
    public void whenCompareByListDifferentWordsThanTrue() {
        boolean result = compareByList("seven", "senet");
        assertThat(result, is(false));
    }

    @Test
    public void whenCompareByTreeSameWordsThanTrue() {
        boolean result = compareByTree("seven", "seven");
        assertThat(result, is(true));
    }

    @Test
    public void whenCompareByTreeTwoWordsThanTrue() {
        boolean result = compareByTree("seven", "senev");
        assertThat(result, is(true));
    }

    @Test
    public void whenCompareByTreeDifferentWordsThanTrue() {
        boolean result = compareByTree("seven", "sevet");
        assertThat(result, is(false));
    }


}
