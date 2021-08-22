package ru.job4j.serialization;

public class Engine {

    private final String vin;

    public Engine(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "vin='" + vin + '\''
                + '}';
    }
}
