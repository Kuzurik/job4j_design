package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {

    private final boolean go;
    private final double volume;
    private final String name;
    private final Engine engine;
    private final String[] places;

    public Car(boolean go, double volume, String name, Engine engine, String[] places) {
        this.go = go;
        this.volume = volume;
        this.name = name;
        this.engine = engine;
        this.places = places;
    }

    @Override
    public String toString() {
        return "Car{"
                + "go=" + go
                + ", volume=" + volume
                + ", name='" + name + '\''
                + ", engine=" + engine
                + ", places=" + Arrays.toString(places)
                + '}';
    }

    public static void main(String[] args) {
        Car car = new Car(false, 2.3, "mazda",
                new Engine("ST1343412312"), new String[]{"driver","passenger next to the driver"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{\"go\":false,"
                        + "\"volume\":2.3,"
                        + "\"name\":\"mazda\","
                        + "\"engine\":{\"vin\":\"ST1343412312\"},"
                        + "\"places\":[\"driver\",\"passenger next to the driver\"]"
                        + "}";

        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
