package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(-1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isExistMissing() {
        Box box = new Box(-1, 10);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void isExistPresent() {
        Box box = new Box(4, 10);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(true);
    }

    @Test
    void NumberOfVerticesNull() {
        Box box = new Box(0, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void NumberOfVerticesEight() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8);
    }

    @Test
    void getAreaVertexNull() {
        Box box = new Box(0, 8);
        double result = box.getArea();
        assertThat(result).isEqualTo(804.247719318987);
    }

    @Test
    void getAreaVertexFour() {
        Box box = new Box(4, 8);
        double result = box.getArea();
        assertThat(result).isEqualTo(110.85125168440814);
    }

    @Test
    void getAreaVertexEight() {
        Box box = new Box(8, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(600);
    }
}